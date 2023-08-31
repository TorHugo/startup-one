package com.dev.startupone.repository.impl;

import com.dev.startupone.lib.data.database.DatabaseService;
import com.dev.startupone.lib.data.domain.VariantModel;
import com.dev.startupone.repository.VariantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
@PropertySource("classpath:query/variant_query.properties")
public class VariantRepositoryImpl implements VariantRepository {

    private final DatabaseService service;

    @Value("${SPI.VARIANT}")
    private String queryPersistVariant;
    @Override
    public void saveVariant(final VariantModel variantModel) {
        service.persist(queryPersistVariant, variantModel);
    }
}
