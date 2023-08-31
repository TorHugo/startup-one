package com.dev.startupone.lib.data.dto;

import lombok.Builder;

@Builder
public record AuthRequest (
        String email,
        String password
){
}
