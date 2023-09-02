package com.dev.startupone.lib.data.dto.active;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ActiveResponse(
        @JsonProperty("active_id")
        Long activeId,
        String name,
        String description,
        String category,
        @JsonProperty("time_offer")
        String timeOffer,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
        @JsonProperty("create_at")
        LocalDateTime createAt,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
        @JsonProperty("update_at")
        LocalDateTime updateAt,
        BigDecimal value,
        SignalRequest signal,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        List<VariantResponse> variants
) {
}
