package com.bada.bazaar.service.Imp;

import com.BadaBazaar.BadaBazaar.converter.CardConverter;
import com.bada.bazaar.model.Card;
import com.bada.bazaar.repository.CardRepository;
import com.bada.bazaar.repository.BuyerRepository;
import com.bada.bazaar.requestDto.CardRequestDto;
import com.bada.bazaar.responseDto.CardResponseDto;
import com.bada.bazaar.responseDto.BuyerCardResponseDto;
import com.bada.bazaar.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImp implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BuyerRepository buyerRepository;


    public BuyerCardResponseDto add(CardRequestDto cardRequestDto) throws Exception {
        Customer customer;
        try{
            customer = buyerRepository.findById(cardRequestDto.getCustomerId()).get();
        }catch (Exception e){
            throw new Exception("customer does not present");
        }

        Card card = CardConverter.cardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);

        customer.getCardList().add(card);
        buyerRepository.save(customer);

        BuyerCardResponseDto buyerCardResponseDto = new BuyerCardResponseDto();
        buyerCardResponseDto.setCustomerName(customer.getName());

        List<CardResponseDto> cardResponseDtoList = new ArrayList<>();

        for(Card c: customer.getCardList()){
            CardResponseDto cardResponseDto = CardConverter.cardToCardDto(c);
            cardResponseDtoList.add(cardResponseDto);
        }
        buyerCardResponseDto.setCardResponseDtoList(cardResponseDtoList);

        return buyerCardResponseDto;
    }

    @Override
    public void remove(int customerId, int cardId) throws Exception{

        Customer customer = buyerRepository.findById(customerId).get();
        Card card = cardRepository.findById(cardId).get();

        // card is not corresponding to customer
        if(!customer.getCardList().contains(card)){
            throw new Exception();
        }

        customer.getCardList().remove(card);
        cardRepository.delete(card);
    }

    @Override
    public BuyerCardResponseDto getAllCardsByCustomerId(int customerId) throws Exception{
        Customer customer;
        try{
            customer = buyerRepository.findById(customerId).get();
        }catch (Exception e){
            throw new Exception("customer does not present");
        }

        BuyerCardResponseDto buyerCardResponseDto = new BuyerCardResponseDto();
        buyerCardResponseDto.setCustomerName(customer.getName());
        List<CardResponseDto> cardResponseDtoList = new ArrayList<>();

        for(Card c: customer.getCardList()){
            CardResponseDto cardResponseDto = CardConverter.cardToCardDto(c);
            cardResponseDtoList.add(cardResponseDto);
        }
        buyerCardResponseDto.setCardResponseDtoList(cardResponseDtoList);

        return buyerCardResponseDto;
    }


}
