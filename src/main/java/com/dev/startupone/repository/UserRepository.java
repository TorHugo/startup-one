package com.dev.startupone.repository;

import com.dev.startupone.lib.data.domain.UserModel;

public interface UserRepository {
    UserModel recoverByEmail(final String email);
    void save(final UserModel user);
    UserModel recoverById(final Long id);
}
