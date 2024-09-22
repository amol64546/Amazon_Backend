package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.controller.ItemController;
import com.bada.bazaar.dto.response.ItemResponseDto;
import com.bada.bazaar.service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ItemControllerImpl implements ItemController {


  private final ItemService itemService;

  @Override
  public ResponseEntity<ItemResponseDto> viewItem(int productId,
                                                  HttpServletRequest request) {
    log.info("[GET]: Request to view item: {}", productId);
    return ResponseEntity.ok().body(itemService.viewItem(productId, request));

  }


}
