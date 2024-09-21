package com.bada.bazaar.controller;

import com.bada.bazaar.dto.request.OrderRequestDto;
import com.bada.bazaar.dto.response.OrderResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Order Controller")
@RequestMapping("/v1/orders")
public interface OrderController {

  @PostMapping
  ResponseEntity<OrderResponseDto> placeOrder(@RequestBody OrderRequestDto orderRequestDto);

}