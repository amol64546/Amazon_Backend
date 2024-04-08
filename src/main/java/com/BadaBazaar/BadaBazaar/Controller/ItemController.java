package com.BadaBazaar.BadaBazaar.Controller;

import com.BadaBazaar.BadaBazaar.Model.Item;
import com.BadaBazaar.BadaBazaar.ResponseDto.ItemResponseDto;
import com.BadaBazaar.BadaBazaar.Service.Imp.ItemServiceImp;
import com.BadaBazaar.BadaBazaar.Service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemServiceImp itemService;

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
