package com.services.customer;

import com.services.customer.model.Customer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(
            CustomerRegistrationRequest
                    customerRegistrationRequest) {

        Customer newCustomer = Customer.builder()
                .lastName(customerRegistrationRequest.lastName())
                .firstName(customerRegistrationRequest.firstName())
                .email(customerRegistrationRequest.email())
                .build();

        customerRepository.saveAndFlush(newCustomer);
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                newCustomer.getId().intValue()
                );

        if(fraudCheckResponse.isFrauster()) {
            throw new IllegalStateException("Cliente with Problems");
        }

    }
}
