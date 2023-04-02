package com.BadaBazaar.BadaBazaar.Controller;

import com.BadaBazaar.BadaBazaar.ResponseDto.ItemResponseDto;
import com.BadaBazaar.BadaBazaar.Service.Imp.ItemServiceImp;
import com.BadaBazaar.BadaBazaar.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemServiceImp itemService;

    @GetMapping("/view/{productId}")
    public ItemResponseDto viewItem(@PathVariable("productId") int productId ) throws Exception {
        return itemService.viewItem(productId);
    }



}
