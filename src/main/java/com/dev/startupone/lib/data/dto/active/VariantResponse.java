package com.dev.startupone.lib.data.dto.active;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record VariantResponse(
        @JsonProperty("variant_id")
        Long variantId,
        BigDecimal value,
        Float variation,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
        @JsonProperty("create_at")
        LocalDateTime createAt
) {
}
