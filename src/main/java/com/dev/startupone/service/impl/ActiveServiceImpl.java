package com.dev.startupone.service.impl;

import com.dev.startupone.lib.data.domain.ActiveModel;
import com.dev.startupone.lib.data.domain.VariantModel;
import com.dev.startupone.lib.data.domain.ActiveCustom;
import com.dev.startupone.lib.data.dto.active.*;
import com.dev.startupone.lib.exception.impl.DataBaseException;
import com.dev.startupone.mapper.ActiveMapper;
import com.dev.startupone.repository.ActiveRepository;
import com.dev.startupone.repository.VariantRepository;
import com.dev.startupone.service.ActiveService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Transactional
    public ActiveFullResponse createActive(final ActiveRequest active) {
        log.info("[1] - Mapping active.");
        final ActiveModel activeModel = activeMapper.mapper(active);
        log.info("[2] - Mapping variant.");
        final VariantModel variantModel = activeMapper.mapper(active.variant());
        log.info("[3] - Validating existing active. Active: [{}].", active.name());
        ActiveModel recoverActive = validatingExistingActive(active.name(), active.timeOffer());
        if (isNull(recoverActive)) {
            log.info("[-] - Saving active in the database.");
            saveActive(activeModel);
            recoverActive = validatingExistingActive(active.name(), active.timeOffer());
        }
        log.info("[4] - Saving variant in the database.");
        saveVariant(variantModel, recoverActive);
        log.info("[5] - Mapping response.");
        VariantModel recoverVariant = recoverVariant(variantModel);
        return activeMapper.mapper(recoverActive, recoverVariant);
    }

    @Override
    @Transactional
    public List<ActiveResponse> findAllActive(final String category,
                                              final String name,
                                              final String timeOffer,
                                              final String signal,
                                              final String order) {
        log.info("[-] - Validating what response.");
        if (nonNull(category)) {
            log.info("[0] - Retrieve active by category. Category: [{}].", category);
            final List<ActiveCustom> actives = recoverActiveByCategory(category, order);
            log.info("[1] - Mapping to response.");
            return activeMapper.mapper(actives);
        } else if (nonNull(name) && nonNull(timeOffer)){
            List<ActiveResponse> activeResponses = new ArrayList<>();
            log.info("[0] - Retrieve active by name. Name: [{}] and TimeOffer: [{}].", name, timeOffer);
            final List<ActiveCustom> lsActive = recoverActiveNameAndTimeOffer(name, timeOffer, order);

            lsActive.forEach(active -> {
                log.info("[1] - Retrieve all variant by activeId.");
                List<VariantModel> lsVariants = recoverVariantByActiveId(active.getActiveId());
                log.info("[2] - Mapping variant to response.");
                List<VariantResponse> lsVariantsResponse = activeMapper.mapperVariants(lsVariants);
                log.info("[3] - Mapping variant in to active.");
                ActiveResponse response = activeMapper.mapper(active, lsVariantsResponse);
                activeResponses.add(response);
            });
            log.info("[4] - Mapping response.");
            return activeResponses;
        } else if (nonNull(name)){
            log.info("[0] - Retrieve active by name. Name: [{}].", name);
            final List<ActiveCustom> actives = recoverActiveName(name, order);
            log.info("[1] - Mapping response.");
            return activeMapper.mapper(actives);
        } else if (nonNull(signal)) {
            log.info("[0] - Retrieve active by signal. Signal: [{}].", signal);
            final List<ActiveCustom> actives = recoverActiveBySignal(signal, order);
            log.info("[1] - Mapping response.");
            return activeMapper.mapper(actives);
        }

        log.info("[0] - Retrieve all active.");
        final List<ActiveCustom> actives = recoverAllActive(order);
        log.info("[1] - Mapping response.");
        return activeMapper.mapper(actives);
    }

    @Override
    @Transactional
    public ActiveResponse updateActiveByName(final String name, final String timeOffer, final ActiveUpdate request) {
        log.info("[0] - Initial update active. ActiveName: [{}].", name);
        log.info("[1] - Retrieving active from database.");
        final ActiveCustom recoverActive = recoverActiveName(name, timeOffer)
                                            .stream().findFirst().orElseThrow(() -> new DataBaseException(""));
        log.info("[2] - Mapping newActive.");
        final ActiveModel model = activeMapper.mapperToActiveModel(request, recoverActive);
        log.info("[3] - Saving new active in the database.");
        saveActive(model);
        log.info("[4] - Mapping to response.");
        return activeMapper.mapper(model);
    }

    private List<VariantModel> recoverVariantByActiveId(final Long activeId) {
        return variantRepository.recoverAllByActiveId(activeId);
    }

    private List<ActiveCustom> recoverActiveBySignal(final String signal, final String order) {
        return activeRepository.recoverAllActive(null, null, null, signal, order);
    }
    private List<ActiveCustom> recoverAllActive(final String order) {
        return activeRepository.recoverAllActive(null, null, null, null, order);
    }

    private List<ActiveCustom> recoverActiveByCategory(final String category, final String order) {
        return activeRepository.recoverAllActive(category, null, null, null, order);
    }
    private List<ActiveCustom> recoverActiveName(final String name, final String order) {
        return activeRepository.recoverAllActive(null, name, null, null, order);
    }
    private List<ActiveCustom> recoverActiveNameAndTimeOffer(final String name, final String timeOffer, final String order) {
        return activeRepository.recoverAllActive(null, name, timeOffer, null, order);
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

    private ActiveModel validatingExistingActive(final String name, final String timeOffer) {
        return activeRepository.recoverByName(name, timeOffer);
    }
}