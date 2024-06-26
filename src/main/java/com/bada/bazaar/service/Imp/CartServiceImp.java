package com.bada.bazaar.service.Imp;

import com.BadaBazaar.BadaBazaar.converter.ProductConverter;
import com.bada.bazaar.enums.ProductStatus;
import com.BadaBazaar.BadaBazaar.model.*;
import com.bada.bazaar.model.Card;
import com.bada.bazaar.model.Cart;
import com.bada.bazaar.model.Item;
import com.bada.bazaar.model.Ordered;
import com.bada.bazaar.model.Product;
import com.bada.bazaar.repository.BuyerRepository;
import com.bada.bazaar.repository.ProductRepository;
import com.bada.bazaar.requestDto.OrderedRequestDto;
import com.bada.bazaar.responseDto.ItemResponseDto;
import com.bada.bazaar.responseDto.OrderedResponseDto;
import com.bada.bazaar.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    ProductRepository productRepository;



    @Override
    public String addToCart(OrderedRequestDto orderedRequestDto) throws Exception{

        // check for customer
        Customer customer;
        try{
            customer = buyerRepository.findById(orderedRequestDto.getCustomerId()).get();
        }catch (Exception e){
            throw new Exception("Customer is not available");
        }

        // check for product
        Product product;
        try{
            product = productRepository.findById(orderedRequestDto.getProductId()).get();
        }catch (Exception e){
            throw new Exception("Product is not available");
        }

        // check for product quantity
        if(product.getQuantity() == 0 ){
            throw new Exception("Product is out of stock");
        }
        if(product.getQuantity() < orderedRequestDto.getRequiredQuantity()){
            throw new Exception("Product quantity is less");
        }

        Cart cart = customer.getCart();
        cart.setCartTotal(product.getPrice() * orderedRequestDto.getRequiredQuantity());
        cart.setCustomer(customer);

        // Item
        Item item = new Item();
        item.setRequiredQuantity(orderedRequestDto.getRequiredQuantity());
        item.setProduct(product);
        item.setCart(cart);

        cart.getItemList().add(item);

        buyerRepository.save(customer);

        return "Item has been added to your Cart!!";
    }

    @Override
    public List<OrderedResponseDto> checkout(int customerId) throws Exception{
        Customer customer;
        try{
            customer = buyerRepository.findById(customerId).get();
        }
        catch(Exception e){
            throw new CustomerNotFoundException("Invalid Customer id !!!");
        }

        List<OrderedResponseDto> orderedResponseDtoList = new ArrayList<>();

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

            OrderedResponseDto orderedResponseDto = OrderedResponseDto.builder()
                    .productName(product.getName())
                    .itemPrice(product.getPrice())
                    .totalCost(ordered.getTotalCost())
                    .deliveryCharge(40)
                    .cardUsedForPayment(cardNo)
                    .quantityOrdered(item.getRequiredQuantity())
                    .orderDate(ordered.getOrderDate())
                    .build();

            orderedResponseDtoList.add(orderedResponseDto);

        }

        // send an email
        String text = "Congrats your order with total value "+totalCost+" has been placed";


        cart.setItemList(new ArrayList<>());
        cart.setCartTotal(0);

        buyerRepository.save(customer);

        return orderedResponseDtoList;
    }

    @Override
    public List<ItemResponseDto> viewItems(int customerId) {
        Customer customer = buyerRepository.findById(customerId).get();
        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        for(Item item:  customer.getCart().getItemList()){
            itemResponseDtos.add(ProductConverter.productToItemResponseDto(item.getProduct()));
        }
        return itemResponseDtos;
    }


}
