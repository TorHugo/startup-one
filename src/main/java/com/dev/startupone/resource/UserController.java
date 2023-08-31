package com.dev.startupone.resource;

import com.dev.startupone.lib.data.dto.UserRequest;
import com.dev.startupone.lib.data.dto.UserResponse;
import com.dev.startupone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserResponse> findByUserId(
            @PathVariable final Long userId
    ) {
        return ResponseEntity.ok(userService.findByUserId(userId));
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<UserResponse> updateToUserById(
            @PathVariable final Long userId,
            @RequestBody final UserRequest user
            ) {
        return ResponseEntity.ok(userService.updateToUser(userId, user));
    }

}
