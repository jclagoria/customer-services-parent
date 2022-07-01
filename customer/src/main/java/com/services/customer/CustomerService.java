package com.services.customer;

import com.services.customer.model.Customer;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {
    public void registerCustomer(
            CustomerRegistrationRequest
                    customerRegistrationRequest) {
        Customer newCustomer = Customer.builder()
                .lastName(customerRegistrationRequest.lastName())
                .firstName(customerRegistrationRequest.firstName())
                .email(customerRegistrationRequest.email())
                .build();

        customerRepository.save(newCustomer);
    }
}
