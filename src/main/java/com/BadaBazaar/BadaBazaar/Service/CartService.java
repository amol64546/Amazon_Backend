package com.BadaBazaar.BadaBazaar.Service;

import com.BadaBazaar.BadaBazaar.Model.Cart;
import com.BadaBazaar.BadaBazaar.Model.Item;
import com.BadaBazaar.BadaBazaar.RequestDto.OrderRequestDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.ItemResponseDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.OrderResponseDto;

import java.util.List;

public interface CartService {
    String addToCart(OrderRequestDto orderRequestDto)throws Exception;

    List<OrderResponseDto> checkout(int customerId)throws Exception;

    List<ItemResponseDto> viewItems(int customerId);
}
