package com.dev.startupone.service;

import com.dev.startupone.lib.data.dto.active.ActiveRequest;
import com.dev.startupone.lib.data.dto.active.ActiveResponse;

public interface ActiveService {
    ActiveResponse createActive(final ActiveRequest object);
}
