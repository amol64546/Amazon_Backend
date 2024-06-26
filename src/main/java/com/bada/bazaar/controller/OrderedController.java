package com.bada.bazaar.controller;

import com.bada.bazaar.requestDto.OrderedRequestDto;
import com.bada.bazaar.responseDto.OrderedResponseDto;
import com.bada.bazaar.service.Imp.OrderedServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderedController {

    private final OrderedServiceImp orderService;

    @PostMapping
    public ResponseEntity<OrderedResponseDto> placeOrder(@RequestBody OrderedRequestDto orderedRequestDto){
        log.info("[POST]: Request to place order: {}", orderedRequestDto);
        OrderedResponseDto orderedResponseDto;
        try{
            orderedResponseDto = orderService.placeOrder(orderedRequestDto);
        }catch (Exception e){
            log.error("Error while placing order: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderedResponseDto);
    }


}
