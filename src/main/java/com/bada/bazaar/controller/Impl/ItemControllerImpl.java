package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.responseDto.ItemResponseDto;
import com.bada.bazaar.service.Impl.ItemServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
public class ItemControllerImpl {

    private final ItemServiceImpl itemService;

    @GetMapping("{productId}")
    public ResponseEntity<ItemResponseDto> viewItem(@PathVariable("productId") int productId ) throws Exception {
        log.info("[GET]: Request to view item: {}", productId);
        try{
            return ResponseEntity.ok().body(itemService.viewItem(productId));
        }catch (Exception e){
            log.error("Error while fetching item by ID: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }





}
