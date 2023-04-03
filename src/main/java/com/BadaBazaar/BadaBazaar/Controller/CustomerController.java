package com.BadaBazaar.BadaBazaar.Controller;

import com.BadaBazaar.BadaBazaar.Model.Customer;
import com.BadaBazaar.BadaBazaar.Repository.CustomerRepository;
import com.BadaBazaar.BadaBazaar.RequestDto.CustomerRequestDto;
import com.BadaBazaar.BadaBazaar.ResponseDto.CustomerResponseDto;
import com.BadaBazaar.BadaBazaar.Service.CustomerService;
import com.BadaBazaar.BadaBazaar.Service.Imp.CustomerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerServiceImp customerService;
    @PostMapping("/add")
    public String addCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        return customerService.addCustomer(customerRequestDto);
    }

    @GetMapping("/get/{customerId}")
    public CustomerResponseDto getCustomerById(@PathVariable int customerId) throws Exception {
        try{
            return customerService.getCustomerById(customerId);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @GetMapping("/get/all")
    public List<CustomerResponseDto> getAll(){
        return customerService.getAll();
    }

    @GetMapping("/get/email")
    public CustomerResponseDto getCustomerByEmail(@RequestParam String email) throws Exception {
        try{
            return customerService.getCustomerByEmail(email);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @PutMapping("/update/mob")
    public CustomerResponseDto updateMobNo(@RequestParam int customerId,@RequestParam String mobNo) throws Exception{
        try{
            return customerService.updateMobNo(customerId,mobNo);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{customerId}")
    public String deleteById(@PathVariable int customerId) throws Exception{
        try{
            customerService.deleteById(customerId);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return "Customer with ID: "+customerId+" has been deleted.";
    }

}
