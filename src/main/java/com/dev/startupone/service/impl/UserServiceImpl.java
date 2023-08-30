package com.dev.startupone.service.impl;

import com.dev.startupone.lib.domain.UserModel;
import com.dev.startupone.lib.dto.UserRequest;
import com.dev.startupone.lib.dto.UserResponse;
import com.dev.startupone.lib.exception.impl.DataBaseException;
import com.dev.startupone.mapper.UserMapper;
import com.dev.startupone.repository.UserRepository;
import com.dev.startupone.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import static com.dev.startupone.lib.util.ValidationUtils.isNullOrElse;
import static com.dev.startupone.lib.util.ParseUtils.parseString;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public UserResponse updateToUser(final Long userId, final UserRequest request) {
        log.info("[0] - Initial update user. UserId: [{}].", userId);
        log.info("[1] - Retrieving user from database.");
        UserModel before = userById(userId);
        log.info("[2] - Mapping new user.");
        UserModel after = mapper.mappingUser(request, before);
        log.info("[3] - Saving new user in the database.");
        saveToUser(after);
        log.info("[4] - Mapping to response.");
        return mapper.mappingUserResponse(after);
    }
    @Override
    public UserResponse findByUserId(final Long userId) {
        log.info("[0] - Initial retrieving user from database.");
        UserModel userModel = userById(userId);
        log.info("[1] - Mapping to response.");
        return mapper.mappingUserResponse(userModel);
    }
    public void saveToUser(final UserModel user){
        userRepository.save(user);
    }
    private UserModel userById(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new DataBaseException("exception.user.not.found"));
    }
}
