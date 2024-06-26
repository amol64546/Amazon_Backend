package com.bada.bazaar.service;

import com.bada.bazaar.requestDto.OrderedRequestDto;
import com.bada.bazaar.responseDto.OrderedResponseDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface OrderedService {

    public OrderedResponseDto placeOrder(@RequestBody OrderedRequestDto orderedRequestDto) throws Exception;


}
