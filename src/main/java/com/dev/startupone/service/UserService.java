package com.dev.startupone.service;

import com.dev.startupone.lib.data.dto.UserRequest;
import com.dev.startupone.lib.data.dto.UserResponse;

public interface UserService {
    UserResponse updateToUser(final Long userId, final UserRequest user);

    UserResponse findByUserId(final Long userId);
}
