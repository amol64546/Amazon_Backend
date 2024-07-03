package com.bada.bazaar.controller;

import com.bada.bazaar.requestDto.CardRequestDto;
import com.bada.bazaar.responseDto.CardResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/cards")
public interface CardController {

  //ROLE - CUSTOMER

  //Add card to customer
  @PostMapping
  ResponseEntity<CardResponseDto> addCardToCustomer(
    @RequestBody CardRequestDto cardRequestDto,
    HttpServletRequest request
  );

  //Remove card from customer
  @DeleteMapping("{cardId}")
  ResponseEntity<ModelMap> removeCardFromCustomer(
    @PathVariable Integer cardId,
    HttpServletRequest request
  );

  //Get all cards of customer
  @GetMapping
  ResponseEntity<List<CardResponseDto>> getAllCardsOfCustomer(
    HttpServletRequest request
  );

}
