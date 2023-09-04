package com.dev.startupone.mapper;

import com.dev.startupone.lib.data.domain.ActiveCustom;
import com.dev.startupone.lib.data.domain.ActiveModel;
import com.dev.startupone.lib.data.domain.VariantModel;
import com.dev.startupone.lib.data.dto.active.*;
import com.dev.startupone.lib.data.enums.CategoryEnum;
import com.dev.startupone.lib.data.enums.SignalEnum;
import com.dev.startupone.lib.data.enums.TimeOfferEnum;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.dev.startupone.lib.util.ValidationUtils.isNull;
import static com.dev.startupone.lib.util.ValidationUtils.isNullOrElse;
import static com.dev.startupone.lib.util.ParseUtils.parseString;
import static com.dev.startupone.lib.util.ParseUtils.parseLong;

@Component
public class ActiveMapper {
    public ActiveModel mapper(final ActiveRequest active) {
        return ActiveModel.builder()
                .name(active.name())
                .description(active.description())
                .categoryId(parseToCategory(active.category()).getId())
                .timeOffer(TimeOfferEnum.parse(active.timeOffer()))
                .build();
    }

    public VariantModel mapper(final VariantRequest variant) {
        return VariantModel.builder()
                .value(variant.value())
                .variation(variant.variation())
                .volume(variant.volume())
                .signalId(returnSignal(variant.signal()))
                .isBuy(returnIsBuy(variant.signal()))
                .build();
    }

    private Boolean returnIsBuy(final SignalRequest signal) {
        if (isNull(signal.signalBuy()))
            return Boolean.FALSE;

        return Boolean.TRUE;
    }

    private Long returnSignal(final SignalRequest signal){
        final SignalEnum signalEnum = SignalEnum.parse(
                isNull(signal.signalBuy()) ? signal.signalSale().force() : signal.signalBuy().force()
        );

        return signalEnum.getId();
    }

    public ActiveFullResponse mapper (final ActiveModel active,
                                      final VariantModel variant){
        return ActiveFullResponse.builder()
                .activeId(active.getId())
                .name(active.getName())
                .category(parseToCategory(active.getCategoryId()).name())
                .timeOffer(active.getTimeOffer())
                .variant(VariantResponse.builder()
                        .variantId(variant.getId())
                        .value(variant.getValue())
                        .variation(variant.getVariation())
                        .createAt(variant.getCreateAt())
                        .build())
                .build();
    }

    public List<ActiveResponse> mapper(final List<ActiveCustom> actives) {
        return actives.stream()
                .map(active ->
                        ActiveResponse.builder()
                                    .activeId(active.getActiveId())
                                    .name(active.getName())
                                    .description(active.getDescription())
                                    .category(CategoryEnum.parse(active.getCategoryId()).name())
                                    .timeOffer(active.getTimeOffer())
                                    .createAt(active.getCreateAt())
                                    .updateAt(active.getUpdateAt())
                                    .value(active.getValue())
                                    .signal(returnSignal(active))
                                .build()
                ).collect(Collectors.toList());
    }

    public ActiveResponse mapper(final ActiveCustom active,
                                 final List<VariantResponse> lsVariantsResponse) {
        return ActiveResponse.builder()
                .activeId(active.getActiveId())
                .name(active.getName())
                .description(active.getDescription())
                .category(CategoryEnum.parse(active.getCategoryId()).name())
                .createAt(active.getCreateAt())
                .updateAt(active.getUpdateAt())
                .value(active.getValue())
                .signal(returnSignal(active))
                .timeOffer(active.getTimeOffer())
                .variants(lsVariantsResponse)
                .build();
    }

    private SignalRequest returnSignal(final ActiveCustom active) {
        final SignalEnum signalEnum = SignalEnum.parse(active.getSignalId());

        if (active.getIsBuy())
            return SignalRequest.builder()
                    .signalBuy(SignalObject.builder()
                            .force(signalEnum.name())
                            .build())
                    .signalSale(null)
                    .build();

        return SignalRequest.builder()
                .signalBuy(null)
                .signalSale(SignalObject.builder()
                        .force(signalEnum.name())
                        .build())
                .build();
    }

    public List<VariantResponse> mapperVariants(final List<VariantModel> lsVariants) {
        return lsVariants.stream().map(variant ->
                VariantResponse.builder()
                        .variantId(variant.getId())
                        .value(variant.getValue())
                        .variation(variant.getVariation())
                        .createAt(variant.getCreateAt())
                .build()).collect(Collectors.toList());
    }

    public ActiveModel mapperToActiveModel(final ActiveUpdate request,
                                           final ActiveCustom before) {
        return ActiveModel.builder()
                .id(before.getActiveId())
                .name(parseString(isNullOrElse(request.name(), before.getName())))
                .description(parseString(isNullOrElse(request.description(), before.getDescription())))
                .categoryId(parseLong(
                        isNullOrElse(
                                parseToCategory(request.category()).getId(),
                                parseToCategory(before.getCategoryId()).getId())
                        )
                )
                .createAt(before.getCreateAt())
                .updateAt(LocalDateTime.now())
                .build();
    }

    public ActiveResponse mapper(final ActiveModel active) {
        return ActiveResponse.builder()
                .activeId(active.getId())
                .name(active.getName())
                .description(active.getDescription())
                .category(parseToCategory(active.getCategoryId()).name())
                .createAt(active.getCreateAt())
                .updateAt(active.getUpdateAt())
                .build();
    }

    private CategoryEnum parseToCategory(final Long categoryId){
        return CategoryEnum.parse(categoryId);
    }
    private CategoryEnum parseToCategory(final String categoryName){
        return CategoryEnum.parse(categoryName);
    }
}
