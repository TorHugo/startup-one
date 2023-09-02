package com.dev.startupone.repository.impl;

import com.dev.startupone.lib.data.database.DatabaseService;
import com.dev.startupone.lib.data.domain.ActiveModel;
import com.dev.startupone.lib.data.domain.ActiveCustom;
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

import static com.dev.startupone.lib.util.ValidationUtils.isNull;
import static com.dev.startupone.lib.util.ValidationUtils.nonNull;

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
    @Value("${SPS.ACTIVE}")
    private String queryRecoverAllActive;
    @Value("${SPS.ACTIVE.WHERE.FIND_NAME}")
    private String queryRecoverFindName;
    @Value("${SPS.ACTIVE.WHERE.FIND_NAME.AND.TIME_OFFER}")
    private String queryRecoverFindNameAndTimeOffer;
    @Value("${SPI.ACTIVE}")
    private String queryPersistActive;
    @Value("${SPU.ACTIVE}")
    private String queryUpdateActive;
    @Override
    public ActiveModel recoverByName(final String name, final String timeOffer) {
        log.info("[-] - recoverByName().");
        return service.retrieve(queryRecoverByName,
                buildParam(name, timeOffer),
                BeanPropertyRowMapper.newInstance(ActiveModel.class))
                .orElse(null);
    }

    @Override
    public void persist(final ActiveModel activeModel) {
        if (isNull(activeModel.getId()))
            service.persist(queryPersistActive, activeModel);
        else
            service.persist(queryUpdateActive, buildParamUpdate(activeModel));
    }

    private MapSqlParameterSource buildParamUpdate(final ActiveModel activeModel) {
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("name", activeModel.getName());
        parameter.addValue("description", activeModel.getDescription());
        parameter.addValue("category", activeModel.getCategory());
        parameter.addValue("activeId", activeModel.getId());
        return parameter;
    }

    @Override
    public List<ActiveCustom> recoverAllActive(final String category,
                                               final String name,
                                               final String timeOffer) {
        if (nonNull(name)) {
            if (nonNull(timeOffer))
                return service.retrieveList(queryRecoverFindNameAndTimeOffer,
                        buildParam(name, timeOffer),
                        BeanPropertyRowMapper.newInstance(ActiveCustom.class));

            return service.retrieveList(queryRecoverFindName,
                    buildParam(name),
                    BeanPropertyRowMapper.newInstance(ActiveCustom.class));
        }

        if (nonNull(category))
            return service.retrieveList(queryRecoverAllByCategory,
                                    buildParamCategory(category),
                                    BeanPropertyRowMapper.newInstance(ActiveCustom.class));

        return service.retrieveList(queryRecoverAllActive,
                                    BeanPropertyRowMapper.newInstance(ActiveCustom.class));
    }

    private MapSqlParameterSource buildParam(final String name){
        return new MapSqlParameterSource("name", name);
    }
    private MapSqlParameterSource buildParam(final String name, final String timeOffer){
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("timeOffer", timeOffer);
        parameter.addValue("name", name);
        return parameter;
    }
    private MapSqlParameterSource buildParamCategory(final String category){
        return new MapSqlParameterSource("category", category);
    }
}
