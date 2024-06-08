package com.BadaBazaar.BadaBazaar.service;

import com.BadaBazaar.BadaBazaar.requestDto.OrderRequestDto;
import com.BadaBazaar.BadaBazaar.responseDto.OrderResponseDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface OrderService {

    public OrderResponseDto placeOrder(@RequestBody OrderRequestDto orderRequestDto) throws Exception;


}
