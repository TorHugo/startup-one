package com.dev.startupone.lib.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ACCESS_USER(1L, "enum.role.user"),
    ACCESS_USER_PREMIUM(2L, "enum.role.premium"),
    ACCESS_USER_ADMIN(3L, "enum.role.admin");

    private final Long id;
    private final String description;
}
