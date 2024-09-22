package com.bada.bazaar.service;

import com.bada.bazaar.dto.request.CardRequestDto;
import com.bada.bazaar.dto.response.CardResponseDto;
import com.bada.bazaar.entity.Card;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.ModelMap;

import java.util.List;

public interface CardService {


  CardResponseDto addCardToCustomer(CardRequestDto cardRequestDto, HttpServletRequest request);

  ModelMap removeCardFromCustomer(Integer cardId, Integer customerId, HttpServletRequest request);

  List<Card> getAllCardsOfCustomer(Integer customerId, HttpServletRequest request);
}
