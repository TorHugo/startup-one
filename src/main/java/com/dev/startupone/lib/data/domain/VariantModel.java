package com.dev.startupone.lib.data.domain;

import jakarta.persistence.*;
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
@Entity
@Table(name = "variant_model")
public class VariantModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variant_id")
    private Long id;
    private Long activeId;
    private BigDecimal value;
    private Float variation;
    private BigDecimal volume;
    private Long signalId;
    private Boolean isBuy;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
