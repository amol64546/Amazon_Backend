package com.bada.bazaar.service.Impl;

import com.bada.bazaar.converter.CardConverter;
import com.bada.bazaar.converter.OrderConverter;
import com.bada.bazaar.dto.request.OrderRequestDto;
import com.bada.bazaar.dto.response.CardResponseDto;
import com.bada.bazaar.dto.response.OrderResponseDto;
import com.bada.bazaar.entity.Customer;
import com.bada.bazaar.entity.Item;
import com.bada.bazaar.entity.OrderEntity;
import com.bada.bazaar.entity.Product;
import com.bada.bazaar.enums.PaymentStatus;
import com.bada.bazaar.enums.ProductStatus;
import com.bada.bazaar.error.ApiException;
import com.bada.bazaar.error.ErrorConstants;
import com.bada.bazaar.repository.CardRepository;
import com.bada.bazaar.repository.CartRepository;
import com.bada.bazaar.repository.CustomerRepository;
import com.bada.bazaar.repository.ItemRepository;
import com.bada.bazaar.repository.OrderRepository;
import com.bada.bazaar.repository.ProductRepository;
import com.bada.bazaar.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

  private final CustomerRepository customerRepository;
  private final ProductRepository productRepository;
  private final ItemRepository itemRepository;
  private final OrderRepository orderRepository;
  private final OrderConverter orderConverter;
  private final CardConverter cardConverter;
  private final CardRepository cardRepository;

  @Override
  public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto, HttpServletRequest request) {
    // customer validation
    Optional<Customer> customer = customerRepository.findById(orderRequestDto.getCustomerId());
    if(customer.isEmpty()) {
      throw new ApiException(ErrorConstants.CUSTOMER_NOT_FOUND);
    }
    // product validation
    Optional<Product> product = productRepository.findById(orderRequestDto.getProductId());
    if(product.isEmpty()) {
      throw new ApiException(ErrorConstants.PRODUCT_NOT_FOUND);
    }
    // check for product quantity
    if(product.get().getStock() == 0) {
      throw new ApiException(ErrorConstants.OUT_OF_STOCK);
    }
    if(product.get().getStock() < orderRequestDto.getQuantity()) {
      throw new ApiException(ErrorConstants.INSUFFICIENT_QTY,
        String.format("Available quantity: %s", product.get().getStock()));
    }
    // check for card
    if(!customer.get().getCardIds().contains(orderRequestDto.getCardId())) {
      throw new ApiException(ErrorConstants.CARD_NOT_FOUND);
    }

    Date currentDate = new Date();

    Item item = Item.builder()
      .dateAdded(currentDate)
      .price(product.get().getPrice())
      .quantity(orderRequestDto.getQuantity())
      .productId(product.get().getId())
      .build();
    item = itemRepository.save(item);

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(currentDate);
    calendar.add(Calendar.DATE, 4);
    Date deliveryDate = calendar.getTime();

    OrderEntity order = OrderEntity.builder()
      .orderDate(currentDate)
      .totalCost(product.get().getPrice() * orderRequestDto.getQuantity())
      .deliveryCharge(40.0)
      .deliveryDate(deliveryDate)
      .customerId(customer.get().getId())
      .itemIds(List.of(item.getId()))
      .shippingAddress(orderRequestDto.getShippingAddress())
      .paymentStatus(PaymentStatus.SUCCESS)
      .build();

    // update product stock
    int leftQuantity = product.get().getStock() - orderRequestDto.getQuantity();
    if(leftQuantity <= 0) {
      product.get().setProductStatus(ProductStatus.OUT_OF_STOCK);
    }
    product.get().setStock(leftQuantity);
    productRepository.save(product.get());

    customer.get().getPurchaseHistoryIds().add(order.getId());
    customerRepository.save(customer.get());

    order = orderRepository.save(order);
    OrderResponseDto orderResponseDto = orderConverter.orderToOrderResponseDto(order);
    CardResponseDto cardResponseDto = cardConverter.cardToCardResponseDto(
      cardRepository.findById(orderRequestDto.getCardId()).get());
    orderResponseDto.setCardResponseDto(cardResponseDto);
    return orderResponseDto;
  }
}