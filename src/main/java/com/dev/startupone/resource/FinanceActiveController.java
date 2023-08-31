package com.dev.startupone.resource;

import com.dev.startupone.service.FinanceActiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/finance-active")
@RequiredArgsConstructor
public class FinanceActiveController {

    private final FinanceActiveService financeActiveService;

    @PostMapping("/create")
    public ResponseEntity<String> testing(
            @RequestBody final Object object
    ){
        return ResponseEntity.ok(financeActiveService.createActive(object));
    }
}
