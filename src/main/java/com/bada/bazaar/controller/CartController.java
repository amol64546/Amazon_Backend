package com.bada.bazaar.controller;

import com.bada.bazaar.dto.request.OrderRequestDto;
import com.bada.bazaar.dto.response.ItemResponseDto;
import com.bada.bazaar.dto.response.OrderResponseDto;
import com.bada.bazaar.service.CartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Cart Controller")
@RequestMapping("/v1/carts")
public interface CartController {

  @PostMapping
  ResponseEntity<String> addToCart(@RequestBody OrderRequestDto orderRequestDto);

  @PostMapping("{customerId}")
  ResponseEntity<List<OrderResponseDto>> checkout(@PathVariable Integer customerId);

  @GetMapping("{customerId}")
  ResponseEntity<List<ItemResponseDto>> viewItems(@PathVariable Integer customerId) ;

}
