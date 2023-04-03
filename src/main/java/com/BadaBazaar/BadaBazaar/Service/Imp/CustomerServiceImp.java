package com.BadaBazaar.BadaBazaar.Service.Imp;

import com.BadaBazaar.BadaBazaar.Converter.CustomerConverter;
import com.BadaBazaar.BadaBazaar.Model.Cart;
import com.BadaBazaar.BadaBazaar.Model.Customer;
import com.BadaBazaar.BadaBazaar.Repository.CustomerRepository;
import com.BadaBazaar.BadaBazaar.RequestDto.CustomerRequestDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.CustomerResponseDto;
import com.BadaBazaar.BadaBazaar.Service.CustomerService;
import org.hibernate.transform.CacheableResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public String addCustomer(CustomerRequestDto customerRequestDto) {

        Customer customer = CustomerConverter.CustomerRequestDtoToCustomer(customerRequestDto);

        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);

        customer.setCart(cart);

        customerRepository.save(customer);

        return "Welcome to BadaBazaar.in";
    }

    public List<CustomerResponseDto> getAll() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerResponseDto> customerResponseDtoList = new ArrayList<>();
        for(Customer c: customerList){
            customerResponseDtoList.add(CustomerConverter.CustomerToCustomerResponseDto(c));
        }
        return customerResponseDtoList;
    }

    @Override
    public CustomerResponseDto getCustomerById(int customerId) {
        Customer customer = customerRepository.findById(customerId).get();

        return CustomerConverter.CustomerToCustomerResponseDto(customer);
    }
}
