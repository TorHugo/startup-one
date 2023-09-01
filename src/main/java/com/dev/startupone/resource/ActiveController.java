package com.dev.startupone.resource;

import com.dev.startupone.lib.data.dto.active.ActiveRequest;
import com.dev.startupone.lib.data.dto.active.ActiveResponse;
import com.dev.startupone.service.ActiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/finance-active")
@RequiredArgsConstructor
public class ActiveController {

    private final ActiveService activeService;

    @PostMapping("/create")
    public ResponseEntity<ActiveResponse> testing(
            @RequestBody final ActiveRequest object
    ){
        return ResponseEntity.ok(activeService.createActive(object));
    }
}
