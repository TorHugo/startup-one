package com.dev.startupone.repository;

import com.dev.startupone.lib.data.domain.ActiveModel;
import com.dev.startupone.lib.data.domain.ActiveCustom;

import java.util.List;

public interface ActiveRepository {
    ActiveModel recoverByName(final String name, final String timeOffer);
    void persist(final ActiveModel activeModel);

    List<ActiveCustom> recoverAllActive(final String category,
                                        final String name,
                                        final String timeOffer,
                                        final String signal,
                                        final String order);
}
