package com.dev.startupone.service.impl;

import com.dev.startupone.client.PaymentClient;
import com.dev.startupone.lib.data.domain.UserModel;
import com.dev.startupone.lib.data.dto.UserResponse;
import com.dev.startupone.lib.data.dto.payment.PaymentRegisterUserRequest;
import com.dev.startupone.lib.data.dto.payment.PaymentRegisterUserResponse;
import com.dev.startupone.lib.exception.impl.DataBaseException;
import com.dev.startupone.lib.exception.impl.ResourceNotFoundException;
import com.dev.startupone.mapper.UserMapper;
import com.dev.startupone.repository.UserRepository;
import com.dev.startupone.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.dev.startupone.lib.util.ValidationUtils.isNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final UserRepository userRepository;
    private final PaymentClient paymentClient;
    private final UserMapper mapper;

    @Override
    public UserResponse createUser(final Long userId) {
        log.info("[0] - Initial retrieving user from database. UserId [{}].", userId);
        UserModel user = userRepository.recoverById(userId);

        log.info("[1] - Validating customer existence for payment.");
//        if (isNull(user.getPaymentId())){
            log.info("[1.1] - Mapping User to UserRequest.");
            final PaymentRegisterUserRequest request = mapper.mappingToUser(user);
            log.info("[1.2] - Request creat client to payment.");
            final PaymentRegisterUserResponse paymentRegisterUserResponse = paymentClient.registerPaymentClient(request);
            log.info("[1.3] - Validating to response.");
            if (isNull(paymentRegisterUserResponse.id()))
                throw new ResourceNotFoundException("exception.payment.create.client");
            log.info("[1.4] - Update user to database. PaymentId: [{}].", paymentRegisterUserResponse.id());
            updateToUser(paymentRegisterUserResponse.id(), user.getId());
//        }

        log.info("[2] - Mapping to return.");
        return mapper.mappingUserResponse(user);
    }

    public void updateToUser(final String paymentId, final Long userId){
//        userRepository.updatePaymentIdById(paymentId, userId);
    }
}