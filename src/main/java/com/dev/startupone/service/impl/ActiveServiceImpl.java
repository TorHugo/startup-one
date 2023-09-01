package com.dev.startupone.service.impl;

import com.dev.startupone.lib.data.domain.ActiveModel;
import com.dev.startupone.lib.data.domain.VariantModel;
import com.dev.startupone.lib.data.dto.active.ActiveRequest;
import com.dev.startupone.lib.data.dto.active.ActiveFullResponse;
import com.dev.startupone.lib.data.dto.active.ActiveResponse;
import com.dev.startupone.mapper.ActiveMapper;
import com.dev.startupone.repository.ActiveRepository;
import com.dev.startupone.repository.VariantRepository;
import com.dev.startupone.service.ActiveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.dev.startupone.lib.util.ValidationUtils.isNull;
import static com.dev.startupone.lib.util.ValidationUtils.nonNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActiveServiceImpl implements ActiveService {

    private final ActiveRepository activeRepository;
    private final VariantRepository variantRepository;
    private final ActiveMapper activeMapper;

    @Override
    public ActiveFullResponse createActive(final ActiveRequest active) {
        ActiveModel recoverActive = null;

        log.info("[1] - Mapping active.");
        final ActiveModel activeModel = activeMapper.mapper(active);
        log.info("[2] - Mapping variant.");
        final VariantModel variantModel = activeMapper.mapper(active.variant());
        log.info("[3] - Validating existing active. Active: [{}].", active.name());
        recoverActive = validatingExistingActive(active.name());
        if (isNull(recoverActive)) {
            log.info("[-] - Saving active in the database.");
            saveActive(activeModel);
            recoverActive = validatingExistingActive(active.name());
        }
        log.info("[4] - Saving variant in the database.");
        saveVariant(variantModel, recoverActive);
        log.info("[5] - Mapping response.");
        VariantModel recoverVariant = recoverVariant(variantModel);
        return activeMapper.mapper(recoverActive, recoverVariant);
    }

    @Override
    public List<ActiveResponse> findActive(final String category, final String name) {
        log.info("[-] - Validating what response.");
        if (nonNull(category)) {
            log.info("[0] - Retrieve active by category. Category: [{}].", category);
            final List<ActiveModel> actives = activeRepository.recoverAllByCategory(category);
            log.info("[1] - Mapping response.");
            return activeMapper.mapper(actives);
        }
        if (nonNull(name))
            log.info("[0] - Retrieve active by name.");

        log.info("[0] - Retrieve all active.");
        return null;
    }

    private VariantModel recoverVariant(final VariantModel variantModel) {
        return variantRepository.recoverByActiveId(variantModel.getActiveId());
    }

    private void saveActive(final ActiveModel activeModel) {
        activeRepository.persist(activeModel);
    }

    private void saveVariant(final VariantModel variantModel, final ActiveModel activeModel) {
        variantModel.setActiveId(activeModel.getId());
        variantRepository.saveVariant(variantModel);
    }

    private ActiveModel validatingExistingActive(final String name) {
        return activeRepository.recoverByName(name);
    }
}