package com.BadaBazaar.BadaBazaar.Service.Imp;

import com.BadaBazaar.BadaBazaar.Model.Card;
import com.BadaBazaar.BadaBazaar.Model.Customer;
import com.BadaBazaar.BadaBazaar.Repository.CardRepository;
import com.BadaBazaar.BadaBazaar.Repository.CustomerRepository;
import com.BadaBazaar.BadaBazaar.RequestDto.CardRequestDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.CardDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.CardResponseDto;
import com.BadaBazaar.BadaBazaar.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImp implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws Exception {
        Customer customer;
        try{
            customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
        }catch (Exception e){
            throw new Exception("customer does not present");
        }

        Card card = Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .expiry(cardRequestDto.getExpiry())
                .customer(customer)
                .build();

        customer.getCardList().add(card);
        customerRepository.save(customer);

        CardResponseDto cardResponseDto = new CardResponseDto();
        cardResponseDto.setCustomerName(customer.getName());

        List<CardDto> cardDtoList = new ArrayList<>();

        for(Card c: customer.getCardList()){
            CardDto cardDto = new CardDto();
            cardDto.setCardNo(c.getCardNo());
            cardDto.setCardType(c.getCardType());
            cardDto.setCvv(c.getCvv());
            cardDto.setExpiry(c.getExpiry());
            cardDtoList.add(cardDto);
        }
        cardResponseDto.setCardDtoList(cardDtoList);

        return cardResponseDto;
    }
}
