package com.dev.startupone.lib.data.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActiveCustom {

    private Long activeId;
    private String name;
    private String description;
    private Long categoryId;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private BigDecimal value;
    private Long signalId;
    private Boolean isBuy;
    private String timeOffer;
}
