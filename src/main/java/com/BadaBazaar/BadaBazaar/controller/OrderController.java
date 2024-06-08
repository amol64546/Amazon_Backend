package com.BadaBazaar.BadaBazaar.controller;

import com.BadaBazaar.BadaBazaar.requestDto.OrderRequestDto;
import com.BadaBazaar.BadaBazaar.responseDto.OrderResponseDto;
import com.BadaBazaar.BadaBazaar.service.Imp.OrderServiceImp;
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
public class OrderController {

    private final OrderServiceImp orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> placeOrder(@RequestBody OrderRequestDto orderRequestDto){
        log.info("[POST]: Request to place order: {}", orderRequestDto);
        OrderResponseDto orderResponseDto;
        try{
            orderResponseDto = orderService.placeOrder(orderRequestDto);
        }catch (Exception e){
            log.error("Error while placing order: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderResponseDto);
    }


}
