package com.dev.startupone.lib.dto;

public record UserRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        String cpfcnpj
) {
}
