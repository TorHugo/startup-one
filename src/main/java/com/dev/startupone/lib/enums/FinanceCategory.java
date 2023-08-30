package com.dev.startupone.lib.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FinanceCategory {

    FINANCIAL_STOCKS(1L, "Financial Stocks."),
    FINANCIAL_CURRENCY(2L, "Financial Currency."),
    FINANCIAL_TITLE(3L, "Financial Title");

    private final Long id;
    private final String description;

}
