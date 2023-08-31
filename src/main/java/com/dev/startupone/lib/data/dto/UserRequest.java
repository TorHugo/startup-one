package com.dev.startupone.lib.data.dto;

public record UserRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        String cpfcnpj
) {
}
