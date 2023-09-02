package com.dev.startupone.resource;

import com.dev.startupone.lib.data.dto.UserRequest;
import com.dev.startupone.lib.data.dto.active.ActiveRequest;
import com.dev.startupone.lib.data.dto.active.ActiveFullResponse;
import com.dev.startupone.lib.data.dto.active.ActiveResponse;
import com.dev.startupone.lib.data.dto.active.ActiveUpdate;
import com.dev.startupone.service.ActiveService;
import jakarta.servlet.http.PushBuilder;
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

    @GetMapping("/all-category")
    public ResponseEntity<List<ActiveResponse>> findAllActive(
            @RequestParam(value = "category", required = false) final String category
    ){
        return ResponseEntity.ok(activeService.findAllActive(category));
    }

    @GetMapping("/name")
    public ResponseEntity<List<ActiveResponse>> findActiveByName(
            @RequestParam(value = "name", required = true) final String name,
            @RequestParam(value = "timeOffer", required = false) final String timeOffer
    ){
        return ResponseEntity.ok(activeService.findActiveByName(name, timeOffer));
    }

    @PutMapping(value = "/{name}/{timeOffer}")
    ResponseEntity<ActiveResponse> updateActiveByName(
            @PathVariable final String name,
            @PathVariable final String timeOffer,
            @RequestBody final ActiveUpdate active
    ){
        return ResponseEntity.ok(activeService.updateActiveByName(name, timeOffer, active));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ActiveResponse>> findAllActiveOfFilter(
            @RequestParam(value = "signal", required = false) final String signal,
            @RequestParam(value = "date", required = false) final String date

    ){
        return ResponseEntity.ok(activeService.findActiveFilter(signal, date));
    }
}
