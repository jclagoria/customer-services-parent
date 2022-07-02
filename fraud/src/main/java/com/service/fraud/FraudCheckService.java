package com.service.fraud;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {
        this.fraudCheckHistoryRepository = fraudCheckHistoryRepository;
    }

    public boolean isFraudulentCustomer(Integer customerId) {

        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .isFraudster(Boolean.FALSE.booleanValue())
                        .customerId(customerId)
                        .createAt(LocalDateTime.now())
                        .build());

        return Boolean.FALSE.booleanValue();
    }

}
