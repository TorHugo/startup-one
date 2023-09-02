package com.dev.startupone.repository;

import com.dev.startupone.lib.data.domain.VariantModel;

import java.util.List;

public interface VariantRepository {
    void saveVariant(final VariantModel variantModel);
    VariantModel recoverByActiveId(final Long activeId);
    List<VariantModel> recoverAllByActiveId(final Long activeId);
}
