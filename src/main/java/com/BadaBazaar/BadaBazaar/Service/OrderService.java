package com.BadaBazaar.BadaBazaar.Service;

import com.BadaBazaar.BadaBazaar.RequestDto.OrderRequestDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

public interface OrderService {

    public OrderResponseDto placeOrder(@RequestBody OrderRequestDto orderRequestDto) throws Exception;


}
