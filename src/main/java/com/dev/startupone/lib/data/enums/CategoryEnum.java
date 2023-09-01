package com.dev.startupone.lib.data.enums;

import com.dev.startupone.lib.exception.impl.DataBaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum CategoryEnum {

    FINANCIAL_STOCKS(1L, "enum.finance.category.stocks"),
    FINANCIAL_CURRENCY(2L, "enum.finance.category.currency"),
    FINANCIAL_BMF_FUTURE(3L, "enum.finance.category.bmf.future"),
    FINANCIAL_TITLE(4L, "enum.finance.category.title");

    private final Long id;
    private final String description;

    public static String parse(final String category) {
        return Arrays.stream(values()).filter(object -> object.name().equals(category))
                .findFirst().orElseThrow(() -> new DataBaseException("Value from domain not found!.")).name();
    }
}
