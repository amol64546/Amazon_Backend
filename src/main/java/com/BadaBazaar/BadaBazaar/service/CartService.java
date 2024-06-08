package com.BadaBazaar.BadaBazaar.service;

import com.BadaBazaar.BadaBazaar.requestDto.OrderRequestDto;
import com.BadaBazaar.BadaBazaar.responseDto.ItemResponseDto;
import com.BadaBazaar.BadaBazaar.responseDto.OrderResponseDto;

import java.util.List;

public interface CartService {
    String addToCart(OrderRequestDto orderRequestDto)throws Exception;

    List<OrderResponseDto> checkout(int customerId)throws Exception;

    List<ItemResponseDto> viewItems(int customerId);
}
