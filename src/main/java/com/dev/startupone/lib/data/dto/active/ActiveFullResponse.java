package com.dev.startupone.lib.data.dto.active;

import lombok.Builder;

@Builder
public record ActiveFullResponse(
        Long activeId,
        String name,
        String category,
        VariantResponse variant
) {
}
