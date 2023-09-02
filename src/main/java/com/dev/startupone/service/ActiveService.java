package com.dev.startupone.service;

import com.dev.startupone.lib.data.dto.active.ActiveRequest;
import com.dev.startupone.lib.data.dto.active.ActiveFullResponse;
import com.dev.startupone.lib.data.dto.active.ActiveResponse;
import com.dev.startupone.lib.data.dto.active.ActiveUpdate;

import java.util.List;

public interface ActiveService {
    ActiveFullResponse createActive(final ActiveRequest object);
    List<ActiveResponse> findAllActive(final String category);
    List<ActiveResponse> findActiveByName(final String name, final String timeOffer);
    ActiveResponse updateActiveByName(final String name, final String timeOffer, final ActiveUpdate active);
    List<ActiveResponse> findActiveFilter(final String signal, final String date);
}
