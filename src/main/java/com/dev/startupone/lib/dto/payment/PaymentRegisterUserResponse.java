package com.dev.startupone.lib.dto.payment;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PaymentRegisterUserResponse(
        String object,
        String id,
        LocalDate dateCreated,
        String name,
        String email,
        String country
) {
}
