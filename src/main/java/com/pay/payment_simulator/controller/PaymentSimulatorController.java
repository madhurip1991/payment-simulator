package com.pay.payment_simulator.controller;

import com.pay.payment_simulator.dto.PaymentInputDTO;
import com.pay.payment_simulator.service.PaymentSimulatorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentSimulatorController {
    private final PaymentSimulatorService paymentSimulatorService;

    public PaymentSimulatorController(PaymentSimulatorService paymentSimulatorService) {
        this.paymentSimulatorService = paymentSimulatorService;
    }


    @PostMapping("/api/payments/simulate")
    public ResponseEntity<String> simulatePayment(@Valid @RequestBody PaymentInputDTO paymentInputDTO){
        return ResponseEntity.ok(paymentSimulatorService.simulatePayment(paymentInputDTO));
    }
}
