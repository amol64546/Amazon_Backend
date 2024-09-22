package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.controller.CardController;
import com.bada.bazaar.converter.CardConverter;
import com.bada.bazaar.dto.request.CardRequestDto;
import com.bada.bazaar.dto.response.CardResponseDto;
import com.bada.bazaar.entity.Card;
import com.bada.bazaar.repository.CardRepository;
import com.bada.bazaar.service.CardService;
import com.bada.bazaar.util.CommonServices;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CardControllerImpl implements CardController {

  private final CardService cardService;
  private final CommonServices commonServices;

  @Override
  public ResponseEntity<CardResponseDto> addCardToCustomer(CardRequestDto cardRequestDto,
    HttpServletRequest request) {
    log.info("[POST]: Request to add card: {}",
      commonServices.prettyPrint(cardRequestDto));
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(cardService.addCardToCustomer(cardRequestDto, request));
  }

  @Override
  public ResponseEntity<ModelMap> removeCardFromCustomer(Integer cardId, Integer customerId,
    HttpServletRequest request) {
    log.info("[DELETE]: Request to remove card, cardId: {}, customerId: {}",
      cardId, customerId);
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(cardService.removeCardFromCustomer(cardId, customerId, request));
  }

  @Override
  public ResponseEntity<List<Card>> getAllCardsOfCustomer(Integer customerId,
    HttpServletRequest request) {
    log.info("[GET]: Request to get all cards, customerId: {}", customerId);
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(cardService.getAllCardsOfCustomer(customerId, request));
  }
}
