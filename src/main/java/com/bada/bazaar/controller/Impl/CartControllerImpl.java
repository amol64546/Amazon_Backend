package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.requestDto.OrderedRequestDto;
import com.bada.bazaar.responseDto.ItemResponseDto;
import com.bada.bazaar.responseDto.OrderedResponseDto;
import com.bada.bazaar.service.CartService;
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
public class CartControllerImpl {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(@RequestBody OrderedRequestDto orderedRequestDto) {
        log.info("[POST]: Request to add to cart: {}", orderedRequestDto);
        String response = "";
        try{
            response = cartService.addToCart(orderedRequestDto);
        }catch (Exception e){
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PostMapping("{customerId}")
    public ResponseEntity<List<OrderedResponseDto>> checkout(@PathVariable int customerId) {
        log.info("[POST]: Request to checkout: {}", customerId);
        List<OrderedResponseDto> orderedResponseDtoList;
        try{
            orderedResponseDtoList = cartService.checkout(customerId);
        }catch (Exception e){
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderedResponseDtoList);

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
