package com.dev.startupone.lib.data.dto;

import lombok.Builder;

@Builder
public record AuthResponse (
        String token,
        Long userId
){
}
