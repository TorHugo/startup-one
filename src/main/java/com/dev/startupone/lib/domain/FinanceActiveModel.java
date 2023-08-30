package com.dev.startupone.lib.domain;

import com.dev.startupone.lib.enums.FinanceCategory;
import jakarta.persistence.*;
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
@Entity
@Table(name = "t_finance_active")
public class FinanceActiveModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "finance_active_id")
    private Long id;

    private String name;        // nome do ativo
    private BigDecimal value;   // valor
    private Float variation;    // percentual de aumento
    private BigDecimal volume;  // volume de compra

    @Enumerated(EnumType.STRING)
    private FinanceCategory categoryModel;

    private LocalDateTime creatAt;
    private LocalDateTime updateAt;

}
