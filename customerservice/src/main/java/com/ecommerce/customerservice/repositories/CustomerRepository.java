package com.ecommerce.customerservice.repositories;

import com.ecommerce.customerservice.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository  extends MongoRepository<Customer, String> {
}
