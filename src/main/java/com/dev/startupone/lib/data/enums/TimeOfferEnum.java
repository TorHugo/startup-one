package com.dev.startupone.lib.data.enums;

import com.dev.startupone.lib.exception.impl.DataBaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum TimeOfferEnum {

    ONE_MINUTE(1L, "Expected offer of one minute."),
    FIVE_MINUTE(2L, "Expected offer of five minute."),
    ONE_HOUR(3L, "Expected offer of one hour."),
    ONE_DAY(4L, "Expected offer of one day."),
    UNDEFINED(5L, "Expected offer of undefined time.");

    private final Long timeOfferId;
    private final String description;

    public static String parse(final String name) {
        return Arrays.stream(values()).filter(object -> object.name().equals(name))
                .findFirst().orElseThrow(() -> new DataBaseException("Value from domain not found!.")).name();
    }
}
