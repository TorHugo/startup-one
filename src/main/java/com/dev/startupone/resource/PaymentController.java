package com.dev.startupone.resource;

import com.dev.startupone.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping(value = "/{userId}")
    public ResponseEntity<Object> paymentPremium(
            @PathVariable final Long userId
            ){
        return ResponseEntity.ok(paymentService.createUser(userId));
    }
}
