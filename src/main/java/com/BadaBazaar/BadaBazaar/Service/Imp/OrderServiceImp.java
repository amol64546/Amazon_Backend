package com.BadaBazaar.BadaBazaar.Service.Imp;

import com.BadaBazaar.BadaBazaar.Enum.ProductStatus;
import com.BadaBazaar.BadaBazaar.Model.*;
import com.BadaBazaar.BadaBazaar.Repository.CustomerRepository;
import com.BadaBazaar.BadaBazaar.Repository.ProductRepository;
import com.BadaBazaar.BadaBazaar.RequestDto.OrderRequestDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.OrderResponseDto;
import com.BadaBazaar.BadaBazaar.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception {
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

        // order
        Ordered ordered = new Ordered();
        ordered.setTotalCost(product.getPrice() * orderRequestDto.getRequiredQuantity());
        ordered.setDeliveryCharge(40);

        // Card
        Card card = customer.getCardList().get(0);
        String cardNo = "";
        int len = card.getCardNo().length();
        for(int i=0;i<len-4;i++)
            cardNo += 'X';
        cardNo += card.getCardNo().substring(len-4);
        ordered.setCardUsedForPayment(cardNo);


        // Item
        Item item = new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setOrdered(ordered);
        item.setProduct(product);
        ordered.getItemList().add(item);
        ordered.setCustomer(customer);

        // product quantity
        int leftQuantity = product.getQuantity()- orderRequestDto.getRequiredQuantity();
        if(leftQuantity<=0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }
        product.setQuantity(leftQuantity);


        // customer

        customer.getOrderList().add(ordered);

        Customer savedCustomer = customerRepository.save(customer);
        Ordered savedOrdered = savedCustomer.getOrderList().get(savedCustomer.getOrderList().size()-1);

        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .productName(product.getName())
                .itemPrice(product.getPrice())
                .totalCost(ordered.getTotalCost())
                .deliveryCharge(40)
                .cardUsedForPayment(cardNo)
                .quantityOrdered(orderRequestDto.getRequiredQuantity())
                .orderDate(savedOrdered.getOrderDate())
                .build();

        return orderResponseDto;
    }
}
