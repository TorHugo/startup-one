package com.dev.startupone.lib.data.dto.active;

import lombok.Builder;

@Builder
public record ActiveUpdate(
        String name,
        String description,
        String category
) {
}
