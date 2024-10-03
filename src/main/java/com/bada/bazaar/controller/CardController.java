package com.bada.bazaar.controller;

import com.bada.bazaar.dto.request.CardRequestDto;
import com.bada.bazaar.dto.response.CardResponseDto;
import com.bada.bazaar.entity.Card;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Card Controller")
@RequestMapping("/v1/cards")
public interface CardController {

  //Add card to customer
  @PostMapping
  ResponseEntity<CardResponseDto> addCardToCustomer(
    @RequestBody CardRequestDto cardRequestDto,
    HttpServletRequest request
  );

  //Remove card from customer
  @DeleteMapping("{cardId}/customer/{customerId}")
  ResponseEntity<ModelMap> removeCardFromCustomer(
    @PathVariable Integer cardId,
    @PathVariable Integer customerId,
    HttpServletRequest request
  );

  //Get all cards of customer
  @GetMapping("{customerId}")
  ResponseEntity<List<Card>> getAllCardsOfCustomer(
    @PathVariable Integer customerId,
    HttpServletRequest request
  );


}
