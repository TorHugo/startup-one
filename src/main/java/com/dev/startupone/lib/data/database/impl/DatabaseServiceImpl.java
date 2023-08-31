package com.dev.startupone.lib.data.database.impl;

import com.dev.startupone.lib.data.database.DatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DatabaseServiceImpl implements DatabaseService {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void persist(final String query) {
        this.jdbcTemplate.update(query);
    }

    @Override
    public void persist(final String query, final Object object) {
        final var params = new BeanPropertySqlParameterSource(object);
        this.namedParameterJdbcTemplate.update(query, params);
    }

    @Override
    public void persist(final String query, final SqlParameterSource params) {
        this.namedParameterJdbcTemplate.update(query, params);
    }

    @Override
    public <T> Optional<T> retrieve(final String query, final Class<T> requiredType) {
        try {
            return Optional.of(this.jdbcTemplate.queryForObject(query, requiredType));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public <T> Optional<T> retrieve(final String query, final SqlParameterSource params, final Class<T> requiredType) {
        try {
            return Optional.of(this.namedParameterJdbcTemplate.queryForObject(query, params, requiredType));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause().getMessage());
            return Optional.empty();
        }
    }

    @Override
    public <T> Optional<T> retrieve(final String query, final SqlParameterSource params, final RowMapper<T> rowMapper) {
        try {
            return Optional.of(this.namedParameterJdbcTemplate.queryForObject(query, params, rowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause().getMessage());
            return Optional.empty();
        }
    }

    @Override
    public <T> List<T> retrieveList(final String query, final SqlParameterSource params, final Class<T> requiredType) {
        try {
            return this.namedParameterJdbcTemplate.queryForList(query, params, requiredType);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public <T> List<T> retrieveList(final String query, final RowMapper<T> rowMapper) {
        return this.namedParameterJdbcTemplate.query(query, rowMapper);
    }

    @Override
    public <T> List<T> retrieveList(final String query, final SqlParameterSource params, final RowMapper<T> rowMapper) {
        return this.namedParameterJdbcTemplate.query(query, params, rowMapper);
    }
}
