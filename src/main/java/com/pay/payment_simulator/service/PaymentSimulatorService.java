package com.pay.payment_simulator.service;

import com.pay.payment_simulator.dto.PaymentInputDTO;
import com.pay.payment_simulator.security.JwtUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentSimulatorService {
    private static final Logger logger= LoggerFactory.getLogger(PaymentSimulatorService.class);
    private final WebClient webClient;

    public PaymentSimulatorService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public String simulatePayment(@Valid PaymentInputDTO paymentInputDTO) {
        String transactionId=UUID.randomUUID().toString();

        Map<String, Object> payload=new HashMap<>();
        payload.put("transactionId", transactionId);
        payload.put("bookingId", paymentInputDTO.bookingId());
        payload.put("amountPaid", paymentInputDTO.amountPaid());
        payload.put("paymentTime", LocalDateTime.now());

        logger.info("Simulating payment: transactionId={}, bookingId={}, amountPaid={}",
                transactionId, paymentInputDTO.bookingId(), paymentInputDTO.amountPaid());

        String jwtToken= JwtUtil.generateToken("payment-simulator");

        String result = webClient.post()
                .uri("/webhooks/payments")
                .header("Authorization", "Bearer " + jwtToken)
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(5))
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(2))
                        .maxBackoff(Duration.ofSeconds(10))
                        .filter(throwable -> throwable instanceof WebClientResponseException &&
                                ((WebClientResponseException) throwable).getStatusCode().is5xxServerError()))
                .doOnSuccess(response -> logger.info("Webhook sent successfully for transactionId={}", transactionId))
                .doOnError(error -> logger.error("Failed to send webhook for transactionId={}", transactionId, error))
                        .block();


        return "Payment simulation triggered. Webhook Response : "+result;
    }
}
