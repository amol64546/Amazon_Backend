package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.controller.OrderController;
import com.bada.bazaar.dto.request.OrderRequestDto;
import com.bada.bazaar.dto.response.OrderResponseDto;
import com.bada.bazaar.service.OrderService;
import com.bada.bazaar.util.CommonServices;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderControllerImpl implements OrderController {

  private final OrderService orderService;
  private final CommonServices commonServices;


  @Override
  public ResponseEntity<OrderResponseDto> placeOrder(OrderRequestDto orderRequestDto,
                                                     HttpServletRequest request) {
    log.info("[POST]: Request to place order: {}",
      commonServices.prettyPrint(orderRequestDto));
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(orderService.placeOrder(orderRequestDto, request));
  }
}
