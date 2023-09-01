package com.dev.startupone.lib.data.dto.active;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ActiveResponse(
        Long activeId,
        String name,
        String description,
        String category,
        BigDecimal value,
        SignalRequest signal
) {
}
