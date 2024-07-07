package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.controller.OrderController;
import com.bada.bazaar.dto.request.OrderRequestDto;
import com.bada.bazaar.dto.response.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderControllerImpl implements OrderController {


  @Override
  public ResponseEntity<OrderResponseDto> placeOrder(OrderRequestDto orderRequestDto) {
    return null;
  }
}
