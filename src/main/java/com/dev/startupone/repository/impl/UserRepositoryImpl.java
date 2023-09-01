package com.dev.startupone.repository.impl;

import com.dev.startupone.lib.data.database.DatabaseService;
import com.dev.startupone.lib.data.domain.UserModel;
import com.dev.startupone.lib.exception.impl.DataBaseException;
import com.dev.startupone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
@PropertySource("classpath:query/user_query.properties")
public class UserRepositoryImpl implements UserRepository {

    private final DatabaseService service;
    private final MessageSource message;

    @Value("${SPS.USER.WHERE.EMAIL}")
    private String queryRecoverByEmail;

    @Value("${SPI.USER}")
    private String queryPersistUser;

    @Value("${SPS.USER.WHERE.ID}")
    private String queryRecoverById;

    @Override
    public UserModel recoverByEmail(final String email) {
        log.info("[-] - To recoverByEmail. Email: [{}].", email);
        return service.retrieve(queryRecoverByEmail,
                                buildParams(email),
                                BeanPropertyRowMapper.newInstance(UserModel.class)).orElse(null);
    }

    @Override
    public void save(final UserModel user) {
        log.info("[-] - To save.");
        service.persist(queryPersistUser, user);
    }

    @Override
    public UserModel recoverById(final Long id) {
        log.info("[-] - To recoverById. userId: [{}].", id);
        return service.retrieve(queryRecoverById,
                                buildParams(id),
                                BeanPropertyRowMapper.newInstance(UserModel.class))
                .orElseThrow(() -> new DataBaseException("exception.user.not.found"));
    }

    private MapSqlParameterSource buildParams(final String value){
        final MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("email", value);
        return parameter;
    }

    private MapSqlParameterSource buildParams(final Long value){
        final MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("id", value);
        return parameter;
    }
}
