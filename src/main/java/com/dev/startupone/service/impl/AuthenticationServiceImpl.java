package com.dev.startupone.service.impl;

import com.dev.startupone.lib.domain.UserModel;
import com.dev.startupone.lib.dto.AuthenticationRequest;
import com.dev.startupone.lib.dto.AuthenticationResponse;
import com.dev.startupone.lib.dto.RegisterRequest;
import com.dev.startupone.lib.enums.Role;
import com.dev.startupone.lib.exception.impl.DataBaseException;
import com.dev.startupone.repository.UserRepository;
import com.dev.startupone.service.AuthenticationService;
import com.dev.startupone.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(final RegisterRequest request) {
        log.info("[0] - Validating existing user. E-mail: [{}].", request.getEmail());
        if (validatingExistingUser(request.getEmail()))
            throw new DataBaseException("User " + request.getEmail() + " exists in the database!.");

        log.info("[1] - Mapping new user.");
        var user = UserModel.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        log.info("[2] - Saving to user in the database.");
        saveToUser(user);

        log.info("[3] - Generate Access TOKEN.");
        return AuthenticationResponse.builder()
                .token(jwtService.generateToken(user))
                .build();
    }

    private boolean validatingExistingUser(final String email) {
        List<UserModel> lsUser = userRepository.findAllUserByEmail(email);
        return !lsUser.isEmpty();
    }

    private void saveToUser(final UserModel user){
        userRepository.save(user);
    }

    @Override
    public AuthenticationResponse authenticate(final AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        return AuthenticationResponse.builder()
                .token(jwtService.generateToken(user))
                .build();
    }
}
