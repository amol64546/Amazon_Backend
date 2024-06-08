package com.BadaBazaar.BadaBazaar.controller;

import com.BadaBazaar.BadaBazaar.requestDto.CustomerRequestDto;
import com.BadaBazaar.BadaBazaar.responseDto.CustomerResponseDto;
import com.BadaBazaar.BadaBazaar.service.Imp.CustomerServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerServiceImp customerService;

    @PostMapping
    public ResponseEntity<String> addCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        log.info("[POST]: Request to add customer: {}", customerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(customerRequestDto));
    }

    @GetMapping("{customerId}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable int customerId) throws Exception {
        log.info("[GET]: Request to get customer by ID: {}", customerId);
        try{
            return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerById(customerId));
        }catch (Exception e){
            log.error("Error while fetching customer by ID: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }

    }

    @GetMapping
    public List<CustomerResponseDto> getAll(){
        return customerService.getAll();
    }

    @GetMapping("{emailId}")
    public ResponseEntity<CustomerResponseDto> getCustomerByEmail(@PathVariable String emailId) throws Exception {
        log.info("[GET]: Request to get customer by email: {}", emailId);
        try{
            return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerByEmail(emailId));
        }catch (Exception e){
            log.error("Error while fetching customer by email: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }

    }

    @PutMapping("{customerId}/mobile/{mobNo}")
    public ResponseEntity<CustomerResponseDto> updateMobNo(
            @PathVariable int customerId,
            @PathVariable String mobNo) throws Exception {
        log.info("[PUT]: Request to update mobile number: {}", mobNo);
        try{
            return ResponseEntity.status(HttpStatus.OK).body(customerService.updateMobNo(customerId,mobNo));
        }catch (Exception e){
            log.error("Error while updating mobile number: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
    @PutMapping("{customerId}/email/{email}")
    public ResponseEntity<CustomerResponseDto> updateEmail(@PathVariable int customerId, @PathVariable String email) throws Exception{
        log.info("[PUT]: Request to update email: {}", email);
        try{
            return ResponseEntity.status(HttpStatus.OK).body(customerService.updateEmail(customerId,email));
        }catch (Exception e){
            log.error("Error while updating email: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<String> deleteById(@PathVariable int customerId) throws Exception{
        log.info("[DELETE]: Request to delete customer by ID: {}", customerId);
        try{
            customerService.deleteById(customerId);
        }catch (Exception e){
            log.error("Error while deleting customer: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Customer deleted successfully");
    }

}
