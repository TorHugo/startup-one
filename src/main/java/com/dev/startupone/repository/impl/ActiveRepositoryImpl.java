package com.dev.startupone.repository.impl;

import com.dev.startupone.lib.data.database.DatabaseService;
import com.dev.startupone.lib.data.domain.ActiveModel;
import com.dev.startupone.lib.data.domain.ActiveCustom;
import com.dev.startupone.lib.data.enums.CategoryEnum;
import com.dev.startupone.lib.data.enums.SignalEnum;
import com.dev.startupone.repository.ActiveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

import static com.dev.startupone.lib.util.ValidationUtils.isNull;
import static com.dev.startupone.lib.util.ValidationUtils.nonNull;

@Slf4j
@Repository
@RequiredArgsConstructor
@PropertySource("classpath:query/active_query.properties")
public class ActiveRepositoryImpl implements ActiveRepository {

    private final DatabaseService service;
    private static final String ASC = "ASC";

    @Value("${SPS.ACTIVE.WHERE.NAME}")
    private String queryRecoverByName;
    @Value("${SPS.ACTIVE.WHERE.CATEGORY.ORDER_BY.ASC}")
    private String queryRecoverAllByCategoryAsc;
    @Value("${SPS.ACTIVE.WHERE.CATEGORY.ORDER_BY.DESC}")
    private String queryRecoverAllByCategoryDesc;
    @Value("${SPS.ACTIVE.WHERE.FIND_NAME.AND.TIME_OFFER.DEFAULT}")
    private String queryRecoverFindNameAndTimeOfferDefault;
    @Value("${SPS.ACTIVE.WHERE.FIND_NAME.ORDER_BY.ASC}")
    private String queryRecoverFindNameAsc;
    @Value("${SPS.ACTIVE.WHERE.FIND_NAME.ORDER_BY.DESC}")
    private String queryRecoverFindNameDesc;
    @Value("${SPS.ACTIVE.WHERE.SIGNAL.ORDER_BY.ASC}")
    private String queryRecoverSignalAsc;
    @Value("${SPS.ACTIVE.WHERE.SIGNAL.ORDER_BY.DESC}")
    private String queryRecoverSignalDesc;
    @Value("${SPS.ACTIVE.ORDER_BY.ASC}")
    private String queryRecoverAllActiveAsc;
    @Value("${SPS.ACTIVE.ORDER_BY.DESC}")
    private String queryRecoverAllActiveDesc;
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

    @Override
    public List<ActiveCustom> recoverAllActive(final String category,
                                               final String name,
                                               final String timeOffer,
                                               final String signal,
                                               final String order) {
        if (nonNull(category)){
            log.info("[-] - recoverAllActiveByCategory({}).", category);
            return service.retrieveList(validatingCategoryOrder(order),
                    buildParamCategory(category, order),
                    BeanPropertyRowMapper.newInstance(ActiveCustom.class));
        } else if (nonNull(name) && nonNull(timeOffer)) {
            log.info("[-] - recoverAllActiveByNameAndTimeOffer({}, {}).", name, timeOffer);
            return service.retrieveList(queryRecoverFindNameAndTimeOfferDefault,
                    buildParam(name, timeOffer),
                    BeanPropertyRowMapper.newInstance(ActiveCustom.class));
        } else if (nonNull(name)) {
            log.info("[-] - recoverAllActiveByName({}).", name);
            return service.retrieveList(validatingNameOrder(order),
                    buildParam(name),
                    BeanPropertyRowMapper.newInstance(ActiveCustom.class));
        } else if (nonNull(signal)) {
            log.info("[-] - recoverAllActiveBySignal({}).", signal);
            return service.retrieveList(validatingSignalOrder(order),
                    buildParamOrder(signal),
                    BeanPropertyRowMapper.newInstance(ActiveCustom.class));
        } else {
            log.info("[-] - recoverAllActive().");
            return service.retrieveList(validatingOrder(order),
                    BeanPropertyRowMapper.newInstance(ActiveCustom.class));
        }
    }

    private String validatingOrder(final String order) {
        return Objects.equals(order, ASC) ? queryRecoverAllActiveAsc : queryRecoverAllActiveDesc;
    }

    private String validatingSignalOrder(final String order) {
        return Objects.equals(order, ASC) ? queryRecoverSignalAsc : queryRecoverSignalDesc;
    }

    private String validatingNameOrder(final String order) {
        return Objects.equals(order, ASC) ? queryRecoverFindNameAsc : queryRecoverFindNameDesc;
    }

    private String validatingCategoryOrder(final String order) {
        return Objects.equals(order, ASC) ? queryRecoverAllByCategoryAsc : queryRecoverAllByCategoryDesc;
    }

    private MapSqlParameterSource buildParamOrder(final String signal) {
        final SignalEnum signalEnum = SignalEnum.parse(signal);
        return new MapSqlParameterSource("signal", signalEnum.getId());
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
    private MapSqlParameterSource buildParamCategory(final String category, final String order){
        final MapSqlParameterSource parameter = new MapSqlParameterSource();
        final Long categoryId =
                CategoryEnum.parse(category).getId();
        parameter.addValue("category", categoryId);
        parameter.addValue("order", order);
        return parameter;
    }
    private MapSqlParameterSource buildParamUpdate(final ActiveModel activeModel) {
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("name", activeModel.getName());
        parameter.addValue("description", activeModel.getDescription());
        parameter.addValue("category", activeModel.getCategoryId());
        parameter.addValue("activeId", activeModel.getId());
        return parameter;
    }
}
