package com.bada.bazaar.service.Impl;

import com.bada.bazaar.dto.request.OrderRequestDto;
import com.bada.bazaar.dto.response.OrderResponseDto;
import com.bada.bazaar.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

  @Override
  public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto, HttpServletRequest request) {
    return null;
  }
}