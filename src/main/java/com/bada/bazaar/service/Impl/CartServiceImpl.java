package com.bada.bazaar.service.Impl;

import com.bada.bazaar.converter.CardConverter;
import com.bada.bazaar.converter.OrderConverter;
import com.bada.bazaar.dto.request.OrderRequestDto;
import com.bada.bazaar.dto.response.CardResponseDto;
import com.bada.bazaar.dto.response.OrderResponseDto;
import com.bada.bazaar.entity.Card;
import com.bada.bazaar.entity.Cart;
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
import com.bada.bazaar.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;
  private final CustomerRepository customerRepository;
  private final ProductRepository productRepository;
  private final ItemRepository itemRepository;
  private final CardRepository cardRepository;
  private final OrderRepository orderRepository;
  private final OrderConverter orderConverter;
  private final CardConverter cardConverter;

  @Override
  public ModelMap addToCart(OrderRequestDto orderRequestDto, HttpServletRequest request) {
    Optional<Customer> customer = customerRepository.findById(orderRequestDto.getCustomerId());
    if(customer.isEmpty()) {
      throw new ApiException(ErrorConstants.CUSTOMER_NOT_FOUND);
    }
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

    Item item = Item.builder()
      .dateAdded(new Date())
      .price(product.get().getPrice())
      .quantity(orderRequestDto.getQuantity())
      .productId(product.get().getId())
      .build();

    Cart cart = cartRepository.findById(customer.get().getId()).get();
    cart.setCustomerId(customer.get().getId());
    cart.getItemIds().add(item.getId());
    cart.setTotalAmount(product.get().getPrice() * orderRequestDto.getQuantity());
    cartRepository.save(cart);

    item.setCartId(cart.getId());
    itemRepository.save(item);

    customer.get().getCartHistoryIds().add(product.get().getId());
    customerRepository.save(customer.get());
    return new ModelMap()
      .addAttribute("msg",
        String.format("Product %s has been added to the cart", product.get().getId()));
  }

  @Override
  public ModelMap checkout(Integer customerId, Integer cardId, HttpServletRequest request) {
    Optional<Customer> customer = customerRepository.findById(customerId);
    if(customer.isEmpty()) {
      throw new ApiException(ErrorConstants.CUSTOMER_NOT_FOUND);
    }
    Cart cart = cartRepository.findById(customer.get().getCartId()).get();
    List<Item> items = itemRepository.findAllById(cart.getItemIds());

    List<OrderResponseDto> orderResponseDtos = new ArrayList<>();
    items.forEach(item -> {
      Optional<Product> product = productRepository.findById(item.getProductId());
      Date currentDate = new Date();

      Calendar calendar = Calendar.getInstance();
      calendar.setTime(currentDate);
      calendar.add(Calendar.DATE, 4);
      Date deliveryDate = calendar.getTime();

      OrderEntity order = OrderEntity.builder()
        .orderDate(currentDate)
        .totalCost(product.get().getPrice() * item.getQuantity())
        .deliveryCharge(40.0)
        .deliveryDate(deliveryDate)
        .customerId(customer.get().getId())
        .itemIds(List.of(item.getId()))
        .paymentStatus(PaymentStatus.SUCCESS)
        .build();

      // update product stock
      int leftQuantity = product.get().getStock() - item.getQuantity();
      if(leftQuantity <= 0) {
        product.get().setProductStatus(ProductStatus.OUT_OF_STOCK);
      }
      product.get().setStock(leftQuantity);
      productRepository.save(product.get());

      customer.get().getPurchaseHistoryIds().add(order.getId());

      order = orderRepository.save(order);
      OrderResponseDto orderResponseDto = orderConverter.orderToOrderResponseDto(order);
      orderResponseDtos.add(orderResponseDto);
    });

    Optional<Card> card = cardRepository.findById(cardId);
    if(card.isEmpty()){
      throw new ApiException(ErrorConstants.CARD_NOT_FOUND);
    }

    CardResponseDto cardResponseDto = cardConverter.cardToCardResponseDto(card.get());

    double totalCost = cart.getTotalAmount();
    cart.setItemIds(new ArrayList<>());
    cart.setTotalAmount(0.0);
    cartRepository.save(cart);

    customerRepository.save(customer.get());

    return new ModelMap()
      .addAttribute("orders", orderResponseDtos)
      .addAttribute("totalCost", totalCost)
      .addAttribute("shippingAddress", customer.get().getShippingAddress())
      .addAttribute("cardUsed" , cardResponseDto);
  }

  @Override
  public Page<Item> viewItems(Integer customerId, Pageable pageable, HttpServletRequest request) {
    if(!customerRepository.existsById(customerId)) {
      throw new ApiException(ErrorConstants.CUSTOMER_NOT_FOUND);
    }
    Item item = Item.builder()
      .cartId(cartRepository.findById(customerId).get().getId())
      .build();
    Example<Item> example = Example.of(item);
    return itemRepository.findAll(example, pageable);
  }
}
