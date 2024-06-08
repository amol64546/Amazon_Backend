package com.BadaBazaar.BadaBazaar.controller;

import com.BadaBazaar.BadaBazaar.exception.SellerNotFoundException;
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
        return ResponseEntity.ok().body(sellerService.getAllSellers());
    }

    @GetMapping("{sellerId}")
    public ResponseEntity<SellerResponseDto> getSellerById(@PathVariable Integer sellerId){
        log.info("[GET]: Request to get seller by ID: {}", sellerId);
        SellerResponseDto sellerResponseDto;
        try{
            sellerResponseDto = sellerService.getSellerById(sellerId);
        } catch (SellerNotFoundException e){
            log.error("Seller not found for ID: {}", sellerId);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(sellerResponseDto);
    }

    @DeleteMapping("{sellerId}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Integer sellerId) {
        log.info("[DELETE]: Request to delete seller by ID: {}", sellerId);
        try{
            sellerService.deleteSeller(sellerId);
        } catch (SellerNotFoundException e){
            log.error("Seller not found for ID: {}", sellerId);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
