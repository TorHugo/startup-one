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

import static com.dev.startupone.lib.util.ValidationUtils.isNull;

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

    @Value("${SPU.USER}")
    private String queryUpdateUser;

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
        log.info("[-] - To save().");
        if (isNull(user.getId()))
            service.persist(queryPersistUser, user);
        else
            service.persist(queryUpdateUser, buildParams(user));
    }

    private MapSqlParameterSource buildParams(final UserModel user) {
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("firstName", user.getFirstName());
        parameter.addValue("lastName", user.getLastName());
        parameter.addValue("email", user.getEmail());
        parameter.addValue("cpfcnpj", user.getCpfcnpj());
        parameter.addValue("userId", user.getId());
        return parameter;
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
