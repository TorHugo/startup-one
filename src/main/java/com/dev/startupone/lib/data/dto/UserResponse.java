package com.dev.startupone.lib.data.dto;

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
