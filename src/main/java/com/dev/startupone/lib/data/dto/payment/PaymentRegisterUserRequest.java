package com.dev.startupone.lib.data.dto.payment;

import lombok.Builder;

@Builder
public record PaymentRegisterUserRequest(
        String name,
        String cpfcnpj,
        String email
) {
}
