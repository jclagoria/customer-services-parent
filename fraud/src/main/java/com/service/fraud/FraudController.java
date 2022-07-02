package com.service.fraud;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
public class FraudController {

    private final FraudCheckService fraudCheckService;

    @GetMapping(path = "/{customerId}")
    @ResponseBody
    public FraudCheckResponse isFraudster(
            @PathVariable("customerId") Integer customerId
            ){

        log.info("System - Fraud Check for Customer Id {}" + customerId);
        boolean isFraudulentCustomer =
                fraudCheckService.isFraudulentCustomer(customerId);

        return new FraudCheckResponse(isFraudulentCustomer);
    }



}
