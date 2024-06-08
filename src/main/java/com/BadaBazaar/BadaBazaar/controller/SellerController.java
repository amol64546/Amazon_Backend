package com.BadaBazaar.BadaBazaar.controller;

import com.BadaBazaar.BadaBazaar.requestDto.SellerRequestDto;
import com.BadaBazaar.BadaBazaar.responseDto.SellerResponseDto;
import com.BadaBazaar.BadaBazaar.service.Imp.SellerServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sellers")
@Slf4j
@RequiredArgsConstructor
public class SellerController {

    private final SellerServiceImp sellerService;


    @PostMapping
    public ResponseEntity<Object> addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        log.info("[POST]: Request to add seller: {}", sellerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(sellerService.addSeller(sellerRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<SellerResponseDto>> getAllSellers(){
        log.info("[GET]: Request to get all sellers");
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.getAllSellers());
    }

    @GetMapping("pan/{panNo}")
    public ResponseEntity<SellerResponseDto> getSellerByPan(@PathVariable String panNo){
        log.info("[GET]: Request to get seller by PAN: {}", panNo);
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.getSellerByPan(panNo));
    }

    @GetMapping("{sellerId}")
    public ResponseEntity<SellerResponseDto> getSellerById(@PathVariable int sellerId){
        log.info("[GET]: Request to get seller by ID: {}", sellerId);
        return ResponseEntity.status(HttpStatus.OK).body(sellerService.getSellerById(sellerId));
    }

}
