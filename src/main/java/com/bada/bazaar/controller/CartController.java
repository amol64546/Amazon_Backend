package com.bada.bazaar.controller;

import com.bada.bazaar.dto.request.OrderRequestDto;
import com.bada.bazaar.entity.Item;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Cart Controller")
@RequestMapping("/v1/carts")
public interface CartController {

  @PostMapping
  ResponseEntity<ModelMap> addToCart(@RequestBody OrderRequestDto orderRequestDto,
                                     HttpServletRequest request);

  @PostMapping("{customerId}")
  ResponseEntity<ModelMap> checkout(@PathVariable Integer customerId,
                                    Integer cardId,
                                    HttpServletRequest request);

  @GetMapping("{customerId}")
  ResponseEntity<Page<Item>> viewItems(@PathVariable Integer customerId,
                                       @Parameter @PageableDefault(sort = "dateAdded",
                                         direction = Sort.Direction.DESC) Pageable pageable,
                                       HttpServletRequest request);

}
