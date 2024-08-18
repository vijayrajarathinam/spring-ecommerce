package com.ecommerce.orderservice.service;


import com.ecommerce.orderservice.responses.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "customer-service",
        url = "${application.config.customer-url}"
)
public interface CustomerService {

    @GetMapping("/{customerId}")
    Optional<CustomerResponse> getCustomer(@PathVariable String customerId );
}
