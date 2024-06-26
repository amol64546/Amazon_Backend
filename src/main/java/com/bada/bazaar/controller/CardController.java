package com.bada.bazaar.controller;

import com.bada.bazaar.requestDto.CardRequestDto;
import com.bada.bazaar.responseDto.BuyerCardResponseDto;
import com.bada.bazaar.service.Imp.CardServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
@Slf4j
public class CardController {

    private final CardServiceImp cardService;

    @PostMapping
    public ResponseEntity<BuyerCardResponseDto> addCard(@RequestBody CardRequestDto cardRequestDto) {
        log.info("[POST]: Request to add card: {}", cardRequestDto);
        BuyerCardResponseDto buyerCardResponseDto;
        try{
            buyerCardResponseDto = cardService.add(cardRequestDto);
        }catch (Exception e){
            log.error("Invalid customerId");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(buyerCardResponseDto);

    }

    @DeleteMapping("{cardId}/{customerId}")
    public ResponseEntity<String> removeCard(@PathVariable int customerId , @PathVariable int cardId){
        log.info("[DELETE]: Request to remove card: {}", cardId);
        try{
            cardService.remove(customerId, cardId);
        }catch (Exception e){
            log.error("Invalid cardId or customerId");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid cardId or customerId");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Card removed successfully");
    }


    @GetMapping("{customerId}")
    public ResponseEntity<BuyerCardResponseDto> getAllCardsByCustomerId(@PathVariable int customerId) {
        log.info("[GET]: Request to get all cards by customerId: {}", customerId);
        BuyerCardResponseDto buyerCardResponseDto;
        try{
            buyerCardResponseDto = cardService.getAllCardsByCustomerId(customerId);
        }catch (Exception e){
            log.error("Invalid customerId");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return new ResponseEntity<>(buyerCardResponseDto,HttpStatus.OK);
    }



}
