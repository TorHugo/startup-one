package com.dev.startupone.repository.impl;

import com.dev.startupone.lib.data.database.DatabaseService;
import com.dev.startupone.lib.data.domain.VariantModel;
import com.dev.startupone.repository.VariantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
@PropertySource("classpath:query/variant_query.properties")
public class VariantRepositoryImpl implements VariantRepository {

    private final DatabaseService service;

    @Value("${SPI.VARIANT}")
    private String queryPersistVariant;
    @Value("${SPS.VARIANT.WHERE.ACTIVE_ID}")
    private String queryRecoverVariant;
    @Override
    public void saveVariant(final VariantModel variantModel) {
        service.persist(queryPersistVariant, variantModel);
    }

    @Override
    public VariantModel recoverByActiveId(final Long activeId) {
        return service.retrieve(queryRecoverVariant,
                                buildParam(activeId),
                                BeanPropertyRowMapper.newInstance(VariantModel.class))
                .orElse(null);
    }

    private MapSqlParameterSource buildParam(final Long activeId){
        return new MapSqlParameterSource("activeId", activeId);
    }
}
