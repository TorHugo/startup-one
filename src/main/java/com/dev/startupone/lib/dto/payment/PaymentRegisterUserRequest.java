package com.dev.startupone.lib.dto.payment;

import lombok.Builder;

@Builder
public record PaymentRegisterUserRequest(
        String name,
        String cpfcnpj,
        String email
) {
}
