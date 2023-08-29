package com.dev.startupone.service.impl;

import com.dev.startupone.lib.domain.UserModel;
import com.dev.startupone.lib.dto.AuthRequest;
import com.dev.startupone.lib.dto.AuthResponse;
import com.dev.startupone.lib.dto.RegisterRequest;
import com.dev.startupone.lib.enums.Role;
import com.dev.startupone.lib.exception.impl.DataBaseException;
import com.dev.startupone.repository.UserRepository;
import com.dev.startupone.security.JwtService;
import com.dev.startupone.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.dev.startupone.lib.util.ConstantsUtils.DEFAULT_VERSION;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthResponse register(final RegisterRequest request) {
        log.info("[0] - Validating existing user. E-mail: [{}].", request.email());
        if (validatingExistingUser(request.email()))
            throw new DataBaseException("User " + request.email() + " exists in the database!.");
        log.info("[1] - Mapping new user.");
        var user = requestToUser(request);
        log.info("[2] - Saving to user in the database.");
        var userId = saveToUser(user);
        log.info("[3] - Generate Access TOKEN.");
        return AuthResponse.builder()
                .token(jwtService.generateToken(user))
                .userId(userId)
                .build();
    }

    private UserModel requestToUser(RegisterRequest request) {
        return UserModel.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .creatAt(LocalDateTime.now())
                .updateAt(null)
                .build();
    }

    private boolean validatingExistingUser(final String email) {
        List<UserModel> lsUser = userRepository.findAllUserByEmail(email);
        return !lsUser.isEmpty();
    }

    private Long saveToUser(final UserModel user){
        UserModel savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    @Override
    public AuthResponse authenticate(final AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        return AuthResponse.builder()
                .token(jwtService.generateToken(user))
                .userId(user.getId())
                .build();
    }
}
