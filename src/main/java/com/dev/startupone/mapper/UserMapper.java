package com.dev.startupone.mapper;

import com.dev.startupone.lib.data.domain.UserModel;
import com.dev.startupone.lib.data.dto.RegisterRequest;
import com.dev.startupone.lib.data.dto.UserRequest;
import com.dev.startupone.lib.data.dto.UserResponse;
import com.dev.startupone.lib.data.dto.payment.PaymentRegisterUserRequest;
import com.dev.startupone.lib.data.enums.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.dev.startupone.lib.util.ParseUtils.parseString;
import static com.dev.startupone.lib.util.ValidationUtils.isNullOrElse;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public PaymentRegisterUserRequest mappingToUser(final UserModel user) {
        return PaymentRegisterUserRequest.builder()
                .name(user.getCompletName())
                .cpfcnpj(user.getCpfcnpj())
                .email(user.getEmail())
                .build();
    }

    public UserResponse mappingUserResponse(final UserModel user) {
        return UserResponse.builder()
                .userId(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .permission(user.getRole())
                .cpfcnpj(user.getCpfcnpj())
//                .paymentId(user.getPaymentId())
                .creatAt(user.getCreateAt())
                .updateAt(user.getUpdateAt())
                .build();
    }

    public UserModel requestToUser(RegisterRequest request) {
        return UserModel.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(RoleEnum.ACCESS_USER.name())
                .createAt(LocalDateTime.now())
                .updateAt(null)
                .cpfcnpj(request.cpfcnpj())
                .build();
    }

    public UserModel mappingUser(final UserRequest request,
                                 final UserModel before) {
        return UserModel.builder()
                .id(before.getId())
                .email(parseString(isNullOrElse(request.email(), before.getEmail())))
                .password(parseString(isNullOrElse(request.password(), before.getPassword())))
                .firstName(parseString(isNullOrElse(request.firstName(), before.getFirstName())))
                .lastName(parseString(isNullOrElse(request.lastName(), before.getLastName())))
                .cpfcnpj(parseString(isNullOrElse(request.cpfcnpj(), before.getCpfcnpj())))
                .role(before.getRole())
                .updateAt(LocalDateTime.now())
                .createAt(before.getCreateAt())
                .build();
    }
}
