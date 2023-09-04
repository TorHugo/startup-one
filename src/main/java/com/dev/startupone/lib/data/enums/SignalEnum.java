package com.dev.startupone.lib.data.enums;

import com.dev.startupone.lib.exception.impl.DataBaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum SignalEnum {

    DEFAULT(1L, "enum.signal.entry.default"),
    WARNING(2L, "enum.signal.entry.warning"),
    BUY(3L, "enum.signal.entry.buy"),
    STRONG(4L, "enum.signal.entry.strong");

    private final Long id;
    private final String description;
    public static SignalEnum parse(final String description) {
        return Arrays.stream(values()).filter(object -> object.name().equals(description))
                .findFirst().orElseThrow(() -> new DataBaseException("Value from domain not found!."));
    }

    public static SignalEnum parse(final Long signalId) {
        return Arrays.stream(values()).filter(object -> object.getId().equals(signalId))
                .findFirst().orElseThrow(() -> new DataBaseException("Value from domain not found!."));
    }
}
