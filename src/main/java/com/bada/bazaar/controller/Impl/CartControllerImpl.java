package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.controller.CartController;
import com.bada.bazaar.dto.request.OrderRequestDto;
import com.bada.bazaar.dto.response.ItemResponseDto;
import com.bada.bazaar.dto.response.OrderResponseDto;
import com.bada.bazaar.service.CartService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CartControllerImpl implements CartController {


  private final CartService cartService;


  @Override
  public ResponseEntity<String> addToCart(OrderRequestDto orderRequestDto) {
    return null;
  }

  @Override
  public ResponseEntity<List<OrderResponseDto>> checkout(Integer customerId) {
    return null;
  }

  @Override
  public ResponseEntity<List<ItemResponseDto>> viewItems(Integer customerId) {
    return null;
  }
}
