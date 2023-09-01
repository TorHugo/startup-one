package com.dev.startupone.service;

import com.dev.startupone.lib.data.dto.active.ActiveRequest;
import com.dev.startupone.lib.data.dto.active.ActiveFullResponse;
import com.dev.startupone.lib.data.dto.active.ActiveResponse;

import java.util.List;

public interface ActiveService {
    ActiveFullResponse createActive(final ActiveRequest object);

    List<ActiveResponse> findActive(final String category, final String name);
}
