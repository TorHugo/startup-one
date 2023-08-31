package com.dev.startupone.lib.data.dto.active;

import java.math.BigDecimal;

public record FinanceActiveRequest(
        String name,
        BigDecimal value,
        Float variation
) {
}
