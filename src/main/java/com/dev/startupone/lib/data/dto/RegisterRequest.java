package com.dev.startupone.lib.data.dto;

import com.dev.startupone.lib.data.enums.RoleEnum;
import lombok.Builder;

@Builder
public record RegisterRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        String cpfcnpj,
        RoleEnum role
) {
}
