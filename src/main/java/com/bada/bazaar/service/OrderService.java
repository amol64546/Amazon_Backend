package com.bada.bazaar.service;

import com.bada.bazaar.dto.request.OrderRequestDto;
import com.bada.bazaar.dto.response.OrderResponseDto;
import jakarta.servlet.http.HttpServletRequest;

public interface OrderService {


  OrderResponseDto placeOrder(OrderRequestDto orderRequestDto, HttpServletRequest request);
}
