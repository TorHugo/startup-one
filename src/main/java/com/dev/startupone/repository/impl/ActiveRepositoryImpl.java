package com.dev.startupone.repository.impl;

import com.dev.startupone.lib.data.database.DatabaseService;
import com.dev.startupone.lib.data.domain.ActiveModel;
import com.dev.startupone.repository.ActiveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
@PropertySource("classpath:query/active_query.properties")
public class ActiveRepositoryImpl implements ActiveRepository {

    private final DatabaseService service;

    @Value("${SPS.ACTIVE.WHERE.NAME}")
    private String queryRecoverByName;
    @Value("${SPS.ACTIVE.WHERE.CATEGORY}")
    private String queryRecoverAllByCategory;
    @Value("${SPI.ACTIVE}")
    private String queryPersistActive;
    @Override
    public ActiveModel recoverByName(final String name) {
        log.info("[-] - recoverByName().");
        return service.retrieve(queryRecoverByName,
                buildParam(name),
                BeanPropertyRowMapper.newInstance(ActiveModel.class))
                .orElse(null);
    }

    @Override
    public void persist(final ActiveModel activeModel) {
        service.persist(queryPersistActive, activeModel);
    }

    @Override
    public List<ActiveModel> recoverAllByCategory(final String category) {
        return service.retrieveList(queryRecoverAllByCategory,
                                    buildParamCategory(category),
                                    BeanPropertyRowMapper.newInstance(ActiveModel.class));
    }

    private MapSqlParameterSource buildParam(final String name){
        return new MapSqlParameterSource("name", name);
    }
    private MapSqlParameterSource buildParamCategory(final String category){
        return new MapSqlParameterSource("category", category);
    }
}
