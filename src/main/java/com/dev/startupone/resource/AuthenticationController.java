package com.dev.startupone.resource;

import com.dev.startupone.lib.dto.AuthRequest;
import com.dev.startupone.lib.dto.AuthResponse;
import com.dev.startupone.lib.dto.RegisterRequest;
import com.dev.startupone.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody final RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody final AuthRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
