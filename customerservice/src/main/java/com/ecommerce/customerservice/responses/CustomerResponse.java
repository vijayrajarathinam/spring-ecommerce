package com.ecommerce.customerservice.responses;


import com.ecommerce.customerservice.entities.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerResponse(
        String id,
        @NotNull(message = "First name is required")
        String firstname,
        @NotNull(message = "Last name is required")
        String lastname,
        @NotNull(message = "Email is required")
        @Email(message = "Is not a valid Email address")
        String email,
        Address address
) {}
