package com.bada.bazaar.controller;

import com.bada.bazaar.dto.request.OrderRequestDto;
import com.bada.bazaar.entity.Item;
import com.bada.bazaar.entity.OrderEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
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
  ResponseEntity<Page<OrderEntity>> checkout(@PathVariable Integer customerId);

  @GetMapping("{customerId}")
  ResponseEntity<Page<Item>> viewItems(@PathVariable Integer customerId);

}
