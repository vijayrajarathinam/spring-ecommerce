package com.ecommerce.customerservice.controllers;


import com.ecommerce.customerservice.requests.CustomerRequest;
import com.ecommerce.customerservice.responses.CustomerResponse;
import com.ecommerce.customerservice.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers(){
        return ResponseEntity.ok(customerService.findAllCustomer());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Boolean> getCustomer(@PathVariable String customerId ){
        return ResponseEntity.ok(customerService.existById(customerId));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String customerId ){
        customerService.deleteOneCustomer(customerId);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/exists/{customerId}")
    public ResponseEntity<Boolean> getCustomerExists(@PathVariable String customerId ){
        return ResponseEntity.ok(customerService.existById(customerId));
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request){
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request){
        customerService.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }
}
