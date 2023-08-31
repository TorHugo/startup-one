package com.dev.startupone.lib.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SignalEntry {

    WARNING(1L, "enum.signal.entry.warning"),
    BUY(2L, "enum.signal.entry.buy"),
    STRONG(3L, "enum.signal.entry.strong");

    private final Long id;
    private final String description;
}
