package com.services.customer;

public record CustomerRegistrationRequest(
        String lastName,
        String firstName,
        String email) {



}
