package com.pay.payment_simulator.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record PaymentInputDTO(
        @NotBlank(message = "BookingId is required")
        UUID bookingId,

        @NotNull(message = "amountPaid due is required")
        @Positive(message = "amountPaid due must be greater than zero")
        Double amountPaid
) {
}
