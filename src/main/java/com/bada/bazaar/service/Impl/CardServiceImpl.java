package com.bada.bazaar.service.Impl;

import com.bada.bazaar.converter.CardConverter;
import com.bada.bazaar.dto.request.CardRequestDto;
import com.bada.bazaar.dto.response.CardResponseDto;
import com.bada.bazaar.entity.Card;
import com.bada.bazaar.error.ApiException;
import com.bada.bazaar.error.ErrorConstants;
import com.bada.bazaar.repository.CardRepository;
import com.bada.bazaar.service.CardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardServiceImpl implements CardService {

  private final CardConverter cardConverter;
  private final CardRepository cardRepository;

  @Override
  public CardResponseDto addCardToCustomer(CardRequestDto cardRequestDto, HttpServletRequest request) {
    Card card = cardConverter.cardRequestDtoToCard(cardRequestDto);
    cardRepository.save(card);
    return cardConverter.cardToCardResponseDto(card);
  }

  @Override
  public ModelMap removeCardFromCustomer(Integer cardId, Integer customerId, HttpServletRequest request) {
    if(!cardRepository.existsById(cardId)){
      throw new ApiException(ErrorConstants.CARD_NOT_FOUND);
    }
    cardRepository.deleteById(cardId);
    return new ModelMap()
      .addAttribute("msg", "Card deleted successfully");
  }

  @Override
  public List<Card> getAllCardsOfCustomer(Integer customerId, HttpServletRequest request) {
    Card card = Card.builder()
      .customerId(customerId)
      .build();
    Example<Card> example = Example.of(card);
    return cardRepository.findAll(example);
  }
}
