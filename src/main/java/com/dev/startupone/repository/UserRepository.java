package com.dev.startupone.repository;

import com.dev.startupone.lib.domain.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByEmail(final String email);
    List<UserModel> findAllUserByEmail(final String email);
}
