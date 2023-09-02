package com.dev.startupone.lib.data.domain;

import com.dev.startupone.lib.data.dto.active.SignalRequest;
import com.dev.startupone.lib.data.dto.active.VariantResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActiveCustom {

    private Long activeId;
    private String name;
    private String description;
    private String category;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private BigDecimal value;
    private String signal;
    private Boolean isBuy;
}
