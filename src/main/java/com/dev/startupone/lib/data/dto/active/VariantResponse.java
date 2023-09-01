package com.dev.startupone.lib.data.dto.active;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record VariantResponse(
        Long variantId,
        BigDecimal value,
        Float variation
) {
}
