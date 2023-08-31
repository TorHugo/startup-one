package com.dev.startupone.lib.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FinanceCategory {

    FINANCIAL_STOCKS(1L, "enum.finance.category.stocks"),
    FINANCIAL_CURRENCY(2L, "enum.finance.category.currency"),
    FINANCIAL_BMF_FUTURE(3L, "enum.finance.category.bmf.future"),
    FINANCIAL_TITLE(4L, "enum.finance.category.title");

    private final Long id;
    private final String description;

}
