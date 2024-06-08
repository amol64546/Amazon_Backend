package com.BadaBazaar.BadaBazaar.controller;

import com.BadaBazaar.BadaBazaar.requestDto.OrderRequestDto;
import com.BadaBazaar.BadaBazaar.responseDto.ItemResponseDto;
import com.BadaBazaar.BadaBazaar.responseDto.OrderResponseDto;
import com.BadaBazaar.BadaBazaar.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(@RequestBody OrderRequestDto orderRequestDto) {
        log.info("[POST]: Request to add to cart: {}", orderRequestDto);
        String response = "";
        try{
            response = cartService.addToCart(orderRequestDto);
        }catch (Exception e){
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PostMapping("{customerId}")
    public ResponseEntity<List<OrderResponseDto>> checkout(@PathVariable int customerId) {
        log.info("[POST]: Request to checkout: {}", customerId);
        List<OrderResponseDto> orderResponseDtoList;
        try{
            orderResponseDtoList = cartService.checkout(customerId);
        }catch (Exception e){
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderResponseDtoList);

    }

    @GetMapping("{customerId}")
    public ResponseEntity<List<ItemResponseDto>> viewItems(@PathVariable int customerId) {
        log.info("[GET]: Request to view items in cart: {}", customerId);
        List<ItemResponseDto> itemResponseDtoList;
        try{
            itemResponseDtoList = cartService.viewItems(customerId);
        }catch (Exception e){
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(itemResponseDtoList);
    }




}
