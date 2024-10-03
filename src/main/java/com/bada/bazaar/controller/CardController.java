package com.bada.bazaar.controller;

import com.bada.bazaar.dto.request.CardRequestDto;
import com.bada.bazaar.dto.response.CardResponseDto;
import com.bada.bazaar.entity.Card;
import io.swagger.v3.oas.annotations.Operation;
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

  @Operation(summary = "Add card")
  @PostMapping
  ResponseEntity<CardResponseDto> addCardToCustomer(
    @RequestBody CardRequestDto cardRequestDto,
    HttpServletRequest request
  );

  @Operation(summary = "Remove card")
  @DeleteMapping("{cardId}/customer/{customerId}")
  ResponseEntity<ModelMap> removeCardFromCustomer(
    @PathVariable Integer cardId,
    @PathVariable Integer customerId,
    HttpServletRequest request
  );

  @Operation(summary = "Get all cards")
  @GetMapping("{customerId}")
  ResponseEntity<List<Card>> getAllCardsOfCustomer(
    @PathVariable Integer customerId,
    HttpServletRequest request
  );


}
