package com.dev.startupone.lib.data.dto.active;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ActiveRequest(
        String name,
        String description,
        String category,
        VariantRequest variant
) {
}
