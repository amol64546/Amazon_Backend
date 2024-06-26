package com.bada.bazaar.service.Imp;

import com.bada.bazaar.model.Buyer;
import com.bada.bazaar.model.Cart;
import com.bada.bazaar.repository.BuyerRepository;
import com.bada.bazaar.repository.CartRepository;
import com.bada.bazaar.requestDto.BuyerRequestDto;
import com.bada.bazaar.responseDto.BuyerResponseDto;
import com.bada.bazaar.service.BuyerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuyerServiceImp implements BuyerService {
    private final BuyerRepository buyerRepository;
    private final ModelMapper modelMapper;
    private final CartRepository cartRepository;

    @Override
    public BuyerResponseDto addBuyer(BuyerRequestDto buyerRequestDto) {

        Buyer buyer = modelMapper.map(buyerRequestDto, Buyer.class);

        Cart cart = Cart.builder()
          .buyerId(buyer.getId())
          .build();

        cartRepository.save(cart);

        buyer.setCartId(cart.getId());

        buyerRepository.save(buyer);

        return modelMapper.map(buyer, BuyerResponseDto.class);
    }

    @Override
    public BuyerResponseDto getBuyerById(Integer customerId) {
        Customer customer = buyerRepository.findById(customerId).get();

        return CustomerConverter.CustomerToCustomerResponseDto(customer);
    }

    @Override
    public void deleteById(Integer customerId) {
        if(!buyerRepository.existsById(customerId)){
            throw new RuntimeException("Customer not found");
        }
        buyerRepository.deleteById(customerId);
    }

}
