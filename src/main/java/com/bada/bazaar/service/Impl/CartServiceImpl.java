package com.bada.bazaar.service.Impl;

import com.bada.bazaar.dto.request.OrderRequestDto;
import com.bada.bazaar.entity.Cart;
import com.bada.bazaar.entity.Customer;
import com.bada.bazaar.entity.Item;
import com.bada.bazaar.entity.OrderEntity;
import com.bada.bazaar.entity.Product;
import com.bada.bazaar.error.ApiException;
import com.bada.bazaar.error.ErrorConstants;
import com.bada.bazaar.repository.CartRepository;
import com.bada.bazaar.repository.CustomerRepository;
import com.bada.bazaar.repository.ItemRepository;
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

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;
  private final CustomerRepository customerRepository;
  private final ProductRepository productRepository;
  private final ItemRepository itemRepository;

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
  public Page<OrderEntity> checkout(Integer customerId, HttpServletRequest request) {
    Optional<Customer> customer = customerRepository.findById(customerId);
    if(customer.isEmpty()) {
      throw new ApiException(ErrorConstants.CUSTOMER_NOT_FOUND);
    }
    return null;
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
