package com.dev.startupone.resource;

import com.dev.startupone.lib.data.dto.active.ActiveRequest;
import com.dev.startupone.lib.data.dto.active.ActiveFullResponse;
import com.dev.startupone.lib.data.dto.active.ActiveResponse;
import com.dev.startupone.service.ActiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/finance-active")
@RequiredArgsConstructor
public class ActiveController {

    private final ActiveService activeService;

    @PostMapping("/create")
    public ResponseEntity<ActiveFullResponse> create(
            @RequestBody final ActiveRequest object
    ){
        return ResponseEntity.ok(activeService.createActive(object));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ActiveResponse>> findActive(
            @RequestParam(value = "category") final String category,
            @RequestParam(value = "name") final String name

    ){
        return ResponseEntity.ok(activeService.findActive(category, name));
    }
}
