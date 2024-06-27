package com.bada.bazaar.service.Impl;

import com.bada.bazaar.enums.ProductStatus;
import com.BadaBazaar.BadaBazaar.model.*;
import com.bada.bazaar.model.Card;
import com.bada.bazaar.model.Item;
import com.bada.bazaar.model.Ordered;
import com.bada.bazaar.model.Product;
import com.bada.bazaar.repository.CustomerRepository;
import com.bada.bazaar.repository.ProductRepository;
import com.bada.bazaar.requestDto.OrderedRequestDto;
import com.bada.bazaar.responseDto.OrderedResponseDto;
import com.bada.bazaar.service.OrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderedServiceImpl implements OrderedService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public OrderedResponseDto placeOrder(OrderedRequestDto orderedRequestDto) throws Exception {
        // check for customer
        Customer customer;
        try{
            customer = customerRepository.findById(orderedRequestDto.getCustomerId()).get();
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

        // order
        Ordered ordered = new Ordered();
        ordered.setTotalCost(product.getPrice() * orderedRequestDto.getRequiredQuantity());
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
        item.setRequiredQuantity(orderedRequestDto.getRequiredQuantity());
        item.setOrdered(ordered);
        item.setProduct(product);

        ordered.getItemList().add(item);
        ordered.setCustomer(customer);

        // product quantity
        int leftQuantity = product.getQuantity()- orderedRequestDto.getRequiredQuantity();
        if(leftQuantity<=0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }
        product.setQuantity(leftQuantity);


        // customer
        customer.getOrderList().add(ordered);

        Customer savedCustomer = customerRepository.save(customer);

        Ordered savedOrdered = savedCustomer.getOrderList().get(savedCustomer.getOrderList().size()-1);

        OrderedResponseDto orderedResponseDto = OrderedResponseDto.builder()
                .productName(product.getName())
                .itemPrice(product.getPrice())
                .totalCost(ordered.getTotalCost())
                .deliveryCharge(40)
                .cardUsedForPayment(cardNo)
                .quantityOrdered(orderedRequestDto.getRequiredQuantity())
                .orderDate(savedOrdered.getOrderDate())
                .build();


        // send an email
        String text = "Congrats your order with total value "+savedOrdered.getTotalCost()+" has been placed";


        return orderedResponseDto;
    }
}
