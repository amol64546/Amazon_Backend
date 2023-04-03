package com.BadaBazaar.BadaBazaar.Controller;

import com.BadaBazaar.BadaBazaar.RequestDto.SellerRequestDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.SellerResponseDto;
import com.BadaBazaar.BadaBazaar.Service.Imp.SellerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerServiceImp sellerService;

    @PostMapping("/add")
    public String addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        return sellerService.addSeller(sellerRequestDto);

    }

    @GetMapping("/get/all")
    public List<SellerResponseDto> getAllSellers(){
        return sellerService.getAllSellers();
    }

    @GetMapping("/get/pan")
    public SellerResponseDto getSellerByPan(@RequestParam String panNo){
        return sellerService.getSellerByPan(panNo);
    }

}
