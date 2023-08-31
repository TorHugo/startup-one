package com.dev.startupone.lib.data.dto.active;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record VariantRequest(
        BigDecimal value,
        BigDecimal variation,
        BigDecimal volume,
        SignalRequest signal
) {
}
