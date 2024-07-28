package com.BadaBazaar.BadaBazaar.Service.Imp;

import com.BadaBazaar.BadaBazaar.Converter.ProductConverter;
import com.BadaBazaar.BadaBazaar.Enum.ProductStatus;
import com.BadaBazaar.BadaBazaar.Exception.CustomerNotFoundException;
import com.BadaBazaar.BadaBazaar.Model.*;
import com.BadaBazaar.BadaBazaar.Repository.CustomerRepository;
import com.BadaBazaar.BadaBazaar.Repository.ProductRepository;
import com.BadaBazaar.BadaBazaar.RequestDto.OrderRequestDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.ItemResponseDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.OrderResponseDto;
import com.BadaBazaar.BadaBazaar.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;



    @Override
    public String addToCart(OrderRequestDto orderRequestDto) throws Exception{

        // check for customer
        Customer customer;
        try{
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }catch (Exception e){
            throw new Exception("Customer is not available");
        }

        // check for product
        Product product;
        try{
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }catch (Exception e){
            throw new Exception("Product is not available");
        }

        // check for product quantity
        if(product.getQuantity() == 0 ){
            throw new Exception("Product is out of stock");
        }
        if(product.getQuantity() < orderRequestDto.getRequiredQuantity()){
            throw new Exception("Product quantity is less");
        }

        Cart cart = customer.getCart();
        cart.setCartTotal(product.getPrice() * orderRequestDto.getRequiredQuantity());
        cart.setCustomer(customer);

        // Item
        Item item = new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);
        item.setCart(cart);

        cart.getItemList().add(item);

        customerRepository.save(customer);

        return "Item has been added to your Cart!!";
    }

    @Override
    public List<OrderResponseDto> checkout(int customerId) throws Exception{
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch(Exception e){
            throw new CustomerNotFoundException("Invalid Customer id !!!");
        }

        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();

        Cart cart = customer.getCart();
        int totalCost = cart.getCartTotal();

        for(Item item: cart.getItemList()){

            Product product = item.getProduct();

            // check for product quantity
            if(product.getQuantity() == 0 ){
                throw new Exception("Product is out of stock");
            }
            if(product.getQuantity() < item.getRequiredQuantity()){
                throw new Exception("Product quantity is less");
            }

            // order
            Ordered ordered = new Ordered();
            ordered.setTotalCost(product.getPrice() * item.getRequiredQuantity());
            ordered.setDeliveryCharge(40);
            ordered.getItemList().add(item);
            ordered.setCustomer(customer);

            // Card
            Card card = customer.getCardList().get(0);
            String cardNo = "";
            int len = card.getCardNo().length();
            for(int i=0;i<len-4;i++)
                cardNo += 'X';
            cardNo += card.getCardNo().substring(len-4);

            ordered.setCardUsedForPayment(cardNo);

            item.setOrdered(ordered);
            customer.getOrderList().add(ordered);

            // product quantity
            int leftQuantity = product.getQuantity()- item.getRequiredQuantity();
            if(leftQuantity<=0){
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }
            product.setQuantity(leftQuantity);

            OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                    .productName(product.getName())
                    .itemPrice(product.getPrice())
                    .totalCost(ordered.getTotalCost())
                    .deliveryCharge(40)
                    .cardUsedForPayment(cardNo)
                    .quantityOrdered(item.getRequiredQuantity())
                    .orderDate(ordered.getOrderDate())
                    .build();

            orderResponseDtoList.add(orderResponseDto);

        }

        // send an email
        String text = "Congrats your order with total value "+totalCost+" has been placed";

        cart.setItemList(new ArrayList<>());
        cart.setCartTotal(0);

        customerRepository.save(customer);

        return  orderResponseDtoList;
    }

    @Override
    public List<ItemResponseDto> viewItems(int customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        for(Item item:  customer.getCart().getItemList()){
            itemResponseDtos.add(ProductConverter.productToItemResponseDto(item.getProduct()));
        }
        return itemResponseDtos;
    }


}
