package com.bada.bazaar.service.Impl;

import com.aidtaas.mobius.error.services.exception.ApiException;
import com.bada.bazaar.exception.ErrorConstants;
import com.bada.bazaar.model.Customer;
import com.bada.bazaar.model.Cart;
import com.bada.bazaar.repository.CustomerRepository;
import com.bada.bazaar.repository.CartRepository;
import com.bada.bazaar.requestDto.BuyerRequestDto;
import com.bada.bazaar.responseDto.CustomerResponseDto;
import com.bada.bazaar.service.CustomerService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final CartRepository cartRepository;

    @Override
    public CustomerResponseDto addBuyer(BuyerRequestDto buyerRequestDto) {
        Customer customer = modelMapper.map(buyerRequestDto, Customer.class);
        Cart cart = Cart.builder()
          .buyerId(customer.getId())
          .build();
        cartRepository.save(cart);
        customer.setCartId(cart.getId());
        customerRepository.save(customer);
        return modelMapper.map(customer, CustomerResponseDto.class);
    }

    @Override
    public CustomerResponseDto getBuyerById(Integer customerId) {
        Optional<Customer> buyer = customerRepository.findById(customerId);
        if(buyer.isEmpty()){
            throw new ApiException(ErrorConstants.NOT_FOUND, "Customer not found");
        }
        return modelMapper.map(buyer, CustomerResponseDto.class);
    }

    @Override
    public void deleteById(Integer customerId) {
        if(!customerRepository.existsById(customerId)){
            throw new RuntimeException("Customer not found");
        }
        customerRepository.deleteById(customerId);
    }

}
