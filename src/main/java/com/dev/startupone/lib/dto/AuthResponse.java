package com.dev.startupone.lib.dto;

import lombok.Builder;

@Builder
public record AuthResponse (
        String token,
        Long userId
){
}
