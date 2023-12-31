package com.dev.startupone.service.impl;

import com.dev.startupone.lib.data.domain.UserModel;
import com.dev.startupone.lib.data.dto.AuthRequest;
import com.dev.startupone.lib.data.dto.AuthResponse;
import com.dev.startupone.lib.data.dto.RegisterRequest;
import com.dev.startupone.lib.exception.impl.DataBaseException;
import com.dev.startupone.mapper.UserMapper;
import com.dev.startupone.repository.UserRepository;
import com.dev.startupone.security.JwtService;
import com.dev.startupone.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper mapper;

    @Override
    public AuthResponse register(final RegisterRequest request) {
        log.info("[0] - Validating existing user. E-mail: [{}].", request.email());
        if (validatingExistingUser(request.email()))
            throw new DataBaseException("User " + request.email() + " exists in the database!.");
        log.info("[1] - Mapping new user.");
        var user = mapper.requestToUser(request);
        log.info("[2] - Saving to user in the database.");
        var userId = saveToUser(user);
        log.info("[3] - Generate Access TOKEN.");
        return AuthResponse.builder()
                .token(jwtService.generateToken(user))
                .userId(userId)
                .build();
    }

    private boolean validatingExistingUser(final String email) {
        UserModel user = userRepository.recoverByEmail(email);
        return Objects.nonNull(user);
    }

    private Long saveToUser(final UserModel user){
        userRepository.save(user);
        return userRepository.recoverByEmail(user.getEmail()).getId();
    }

    @Override
    public AuthResponse authenticate(final AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var user = userRepository.recoverByEmail(request.email());

        if (Objects.isNull(user))
            throw new DataBaseException("User not found!");

        return AuthResponse.builder()
                .token(jwtService.generateToken(user))
                .userId(user.getId())
                .build();
    }
}
