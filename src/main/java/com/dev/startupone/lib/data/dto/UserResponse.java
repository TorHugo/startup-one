package com.dev.startupone.lib.data.dto;

import com.dev.startupone.lib.data.enums.Role;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserResponse (
        Long userId,
        String firstName,
        String lastName,
        String email,
        String cpfcnpj,
        String paymentId,
        String permission,
        LocalDateTime creatAt,
        LocalDateTime updateAt
){
}
