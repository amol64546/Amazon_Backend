package com.bada.bazaar.service;

import com.bada.bazaar.requestDto.OrderedRequestDto;
import com.bada.bazaar.responseDto.ItemResponseDto;
import com.bada.bazaar.responseDto.OrderedResponseDto;

import java.util.List;

public interface CartService {
    String addToCart(OrderedRequestDto orderedRequestDto)throws Exception;

    List<OrderedResponseDto> checkout(int customerId)throws Exception;

    List<ItemResponseDto> viewItems(int customerId);
}
