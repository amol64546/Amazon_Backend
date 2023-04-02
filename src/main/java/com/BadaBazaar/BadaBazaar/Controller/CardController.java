package com.BadaBazaar.BadaBazaar.Controller;

import com.BadaBazaar.BadaBazaar.RequestDto.CardRequestDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.CardResponseDto;
import com.BadaBazaar.BadaBazaar.Service.CardService;
import com.BadaBazaar.BadaBazaar.Service.Imp.CardServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardServiceImp cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto) {
        CardResponseDto cardResponseDto;
        try{
            cardResponseDto = cardService.addCard(cardRequestDto);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(cardResponseDto,HttpStatus.CREATED);

    }

}
