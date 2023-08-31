package com.dev.startupone.repository;

import com.dev.startupone.lib.data.domain.ActiveModel;

public interface ActiveRepository {
    ActiveModel recoverByName(final String name);
    void persist(final ActiveModel activeModel);
}
