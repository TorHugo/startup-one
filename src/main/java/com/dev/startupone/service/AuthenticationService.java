package com.dev.startupone.service;

import com.dev.startupone.lib.data.dto.AuthRequest;
import com.dev.startupone.lib.data.dto.AuthResponse;
import com.dev.startupone.lib.data.dto.RegisterRequest;

public interface AuthenticationService {
    AuthResponse register(final RegisterRequest request);

    AuthResponse authenticate(final AuthRequest request);
}
