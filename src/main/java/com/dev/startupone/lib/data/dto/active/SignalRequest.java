package com.dev.startupone.lib.data.dto.active;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record SignalRequest(
        @JsonProperty("signal_sale")
        SignalObject signalSale,
        @JsonProperty("signal_buy")
        SignalObject signalBuy
) {
}
