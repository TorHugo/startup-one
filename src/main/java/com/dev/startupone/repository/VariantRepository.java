package com.dev.startupone.repository;

import com.dev.startupone.lib.data.domain.VariantModel;

public interface VariantRepository {
    void saveVariant(final VariantModel variantModel);

    VariantModel recoverByActiveId(final Long activeId);
}
