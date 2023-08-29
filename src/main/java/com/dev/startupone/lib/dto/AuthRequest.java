package com.dev.startupone.lib.dto;

import lombok.Builder;

@Builder
public record AuthRequest (
        String email,
        String password
){
}
