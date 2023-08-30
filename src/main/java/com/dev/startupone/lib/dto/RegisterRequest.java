package com.dev.startupone.lib.dto;

import lombok.Builder;

@Builder
public record RegisterRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        String cpfcnpj
) {
}
