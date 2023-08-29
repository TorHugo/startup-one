package com.dev.startupone.lib.dto;

import com.dev.startupone.lib.enums.Role;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserResponse (
        Long userId,
        String firstName,
        String lastName,
        String email,
        Role permission,
        LocalDateTime creatAt,
        LocalDateTime updateAt
){
}
