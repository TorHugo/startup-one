package com.dev.startupone.service;

import com.dev.startupone.lib.dto.AuthRequest;
import com.dev.startupone.lib.dto.AuthResponse;
import com.dev.startupone.lib.dto.RegisterRequest;

public interface AuthenticationService {
    AuthResponse register(final RegisterRequest request);

    AuthResponse authenticate(final AuthRequest request);
}
