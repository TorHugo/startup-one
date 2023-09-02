package com.dev.startupone.lib.data.dto.active;

import com.dev.startupone.lib.data.enums.TimeOfferEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ActiveRequest(
        String name,
        String description,
        String category,
        @JsonProperty("time_offer")
        String timeOffer,
        VariantRequest variant
) {
}
