package com.dev.startupone.lib.data.dto;

import com.dev.startupone.lib.data.enums.Role;
import lombok.Builder;

@Builder
public record RegisterRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        String cpfcnpj,
        Role role
) {
}
