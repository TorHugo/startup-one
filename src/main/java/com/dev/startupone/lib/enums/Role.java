package com.dev.startupone.lib.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ACCESS_USER(1L, "Default User."),
    ACCESS_USER_PREMIUM(2L, "Premium User."),
    ACCESS_USER_ADMIN(3L, "Admin User");

    private final Long id;
    private final String description;
}
