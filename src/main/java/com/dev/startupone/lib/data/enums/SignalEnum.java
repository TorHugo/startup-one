package com.dev.startupone.lib.data.enums;

import com.dev.startupone.lib.exception.impl.DataBaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum SignalEnum {

    WARNING(1L, "enum.signal.entry.warning"),
    BUY(2L, "enum.signal.entry.buy"),
    STRONG(3L, "enum.signal.entry.strong");

    private final Long id;
    private final String description;
}
