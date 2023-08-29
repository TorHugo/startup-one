package com.dev.startupone.service;

import com.dev.startupone.lib.dto.AuthenticationRequest;
import com.dev.startupone.lib.dto.AuthenticationResponse;
import com.dev.startupone.lib.dto.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(final RegisterRequest request);

    AuthenticationResponse authenticate(final AuthenticationRequest request);
}
