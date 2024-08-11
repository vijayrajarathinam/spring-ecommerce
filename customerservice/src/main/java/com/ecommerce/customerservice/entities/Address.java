package com.ecommerce.customerservice.entities;


import lombok.*;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Validated
public class Address {
    private String street;
    private String doorNum;
    private String zipCode;
}
