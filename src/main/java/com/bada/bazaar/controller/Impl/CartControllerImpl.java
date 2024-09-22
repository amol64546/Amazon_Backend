package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.controller.CartController;
import com.bada.bazaar.dto.request.OrderRequestDto;
import com.bada.bazaar.entity.Item;
import com.bada.bazaar.entity.OrderEntity;
import com.bada.bazaar.service.CartService;
import com.bada.bazaar.util.CommonServices;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CartControllerImpl implements CartController {

  private final CommonServices commonServices;
  private final CartService cartService;


  @Override
  public ResponseEntity<ModelMap> addToCart(OrderRequestDto orderRequestDto,
                                            HttpServletRequest request) {
    log.info("[POST]: Request to add item to cart: {}",
      commonServices.prettyPrint(orderRequestDto));
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(cartService.addToCart(orderRequestDto, request));
  }

  @Override
  public ResponseEntity<Page<OrderEntity>> checkout(Integer customerId,
                                                    HttpServletRequest request) {
    log.info("[POST]: Request to checkout cart, customerId: {}", customerId);
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(cartService.checkout(customerId, request));
  }

  @Override
  public ResponseEntity<Page<Item>> viewItems(Integer customerId,
                                              Pageable pageable,
                                              HttpServletRequest request) {
    log.info("[GET]: Request to view items in cart, customerId: {}", customerId);
    return ResponseEntity.status(HttpStatus.OK)
      .body(cartService.viewItems(customerId, pageable, request));
  }
}
