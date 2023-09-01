package com.dev.startupone.repository;

import com.dev.startupone.lib.data.domain.ActiveModel;

import java.util.List;

public interface ActiveRepository {
    ActiveModel recoverByName(final String name);
    void persist(final ActiveModel activeModel);

    List<ActiveModel> recoverAllByCategory(final String category);
}
