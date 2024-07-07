package com.bada.bazaar.controller;

import com.bada.bazaar.dto.response.ItemResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Item")
@RequestMapping("/v1/items")
public interface ItemController {


  @GetMapping("/{productId}")
  ResponseEntity<ItemResponseDto> viewItem(
    @PathVariable int productId );

}
