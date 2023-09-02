package com.dev.startupone.lib.data.dto.active;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record ActiveFullResponse(
        @JsonProperty("active_id")
        Long activeId,
        String name,
        String category,
        String timeOffer,
        VariantResponse variant
) {
}
