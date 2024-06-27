package com.bada.bazaar.service.Impl;

import com.BadaBazaar.BadaBazaar.converter.CardConverter;
import com.bada.bazaar.model.Card;
import com.bada.bazaar.repository.CardRepository;
import com.bada.bazaar.repository.CustomerRepository;
import com.bada.bazaar.requestDto.CardRequestDto;
import com.bada.bazaar.responseDto.CardResponseDto;
import com.bada.bazaar.responseDto.CustomerCardResponseDto;
import com.bada.bazaar.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CustomerRepository customerRepository;


    public CustomerCardResponseDto add(CardRequestDto cardRequestDto) throws Exception {
        Customer customer;
        try{
            customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
        }catch (Exception e){
            throw new Exception("customer does not present");
        }

        Card card = CardConverter.cardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);

        customer.getCardList().add(card);
        customerRepository.save(customer);

        CustomerCardResponseDto customerCardResponseDto = new CustomerCardResponseDto();
        customerCardResponseDto.setCustomerName(customer.getName());

        List<CardResponseDto> cardResponseDtoList = new ArrayList<>();

        for(Card c: customer.getCardList()){
            CardResponseDto cardResponseDto = CardConverter.cardToCardDto(c);
            cardResponseDtoList.add(cardResponseDto);
        }
        customerCardResponseDto.setCardResponseDtoList(cardResponseDtoList);

        return customerCardResponseDto;
    }

    @Override
    public void remove(int customerId, int cardId) throws Exception{

        Customer customer = customerRepository.findById(customerId).get();
        Card card = cardRepository.findById(cardId).get();

        // card is not corresponding to customer
        if(!customer.getCardList().contains(card)){
            throw new Exception();
        }

        customer.getCardList().remove(card);
        cardRepository.delete(card);
    }

    @Override
    public CustomerCardResponseDto getAllCardsByCustomerId(int customerId) throws Exception{
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }catch (Exception e){
            throw new Exception("customer does not present");
        }

        CustomerCardResponseDto customerCardResponseDto = new CustomerCardResponseDto();
        customerCardResponseDto.setCustomerName(customer.getName());
        List<CardResponseDto> cardResponseDtoList = new ArrayList<>();

        for(Card c: customer.getCardList()){
            CardResponseDto cardResponseDto = CardConverter.cardToCardDto(c);
            cardResponseDtoList.add(cardResponseDto);
        }
        customerCardResponseDto.setCardResponseDtoList(cardResponseDtoList);

        return customerCardResponseDto;
    }


}
