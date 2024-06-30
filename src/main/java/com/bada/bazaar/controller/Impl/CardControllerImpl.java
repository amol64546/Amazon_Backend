package com.bada.bazaar.controller.Impl;

import com.bada.bazaar.controller.CardController;
import com.bada.bazaar.requestDto.CardRequestDto;
import com.bada.bazaar.responseDto.CardResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasRole('CUSTOMER')")
public class CardControllerImpl implements CardController {


  @Override
  public ResponseEntity<CardResponseDto> addCardToCustomer(CardRequestDto cardRequestDto,
    HttpServletRequest request) {
    return null;
  }

  @Override
  public ResponseEntity<ModelMap> removeCardFromCustomer(Integer cardId,
    HttpServletRequest request) {
    return null;
  }

  @Override
  public ResponseEntity<List<CardResponseDto>> getAllCardsOfCustomer(HttpServletRequest request) {
    return null;
  }
}
