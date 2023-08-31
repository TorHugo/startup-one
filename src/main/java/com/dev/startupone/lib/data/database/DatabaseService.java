package com.dev.startupone.lib.data.database;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DatabaseService {
    void persist(final String query);

    void persist(final String query, final Object object);

    void persist(final String query, final SqlParameterSource params);

    <T> Optional<T> retrieve(final String query, final Class<T> requiredType);

    <T> Optional<T> retrieve(final String query, final SqlParameterSource params, final Class<T> requiredType);
    <T> Optional<T> retrieve(final String query, final SqlParameterSource params, final RowMapper<T> rowMapper);
    <T> List<T> retrieveList(final String query, final SqlParameterSource params, final Class<T> requiredType);

    <T> List<T> retrieveList(final String query, final RowMapper<T> rowMapper);

    <T> List<T> retrieveList(final String query, final SqlParameterSource params, final RowMapper<T> rowMapper);
}
