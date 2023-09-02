package com.dev.startupone.mapper;

import com.dev.startupone.lib.data.domain.ActiveCustom;
import com.dev.startupone.lib.data.domain.ActiveModel;
import com.dev.startupone.lib.data.domain.VariantModel;
import com.dev.startupone.lib.data.dto.active.*;
import com.dev.startupone.lib.data.enums.CategoryEnum;
import com.dev.startupone.lib.data.enums.TimeOfferEnum;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.dev.startupone.lib.util.ValidationUtils.isNull;
import static com.dev.startupone.lib.util.ValidationUtils.isNullOrElse;
import static com.dev.startupone.lib.util.ParseUtils.parseString;

@Component
public class ActiveMapper {
    public ActiveModel mapper(final ActiveRequest active) {
        return ActiveModel.builder()
                .name(active.name())
                .description(active.description())
                .category(CategoryEnum.parse(active.category()))
                .timeOffer(TimeOfferEnum.parse(active.timeOffer()))
                .build();
    }

    public VariantModel mapper(final VariantRequest variant) {
        return VariantModel.builder()
                .value(variant.value())
                .variation(variant.variation())
                .volume(variant.volume())
                .signal(returnSignal(variant.signal()))
                .isBuy(returnIsBuy(variant.signal()))
                .build();
    }

    private Boolean returnIsBuy(final SignalRequest signal) {
        if (isNull(signal.signalBuy()))
            return Boolean.FALSE;

        return Boolean.TRUE;
    }

    private String returnSignal(final SignalRequest signal){
        return isNull(signal.signalBuy()) ? signal.signalSale().force() : signal.signalBuy().force();
    }

    public ActiveFullResponse mapper (final ActiveModel active,
                                      final VariantModel variant){
        return ActiveFullResponse.builder()
                .activeId(active.getId())
                .name(active.getName())
                .category(active.getCategory())
                .timeOffer(active.getTimeOffer())
                .variant(VariantResponse.builder()
                        .variantId(variant.getId())
                        .value(variant.getValue())
                        .variation(variant.getVariation())
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
                                    .category(active.getCategory())
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
                .category(active.getCategory())
                .createAt(active.getCreateAt())
                .updateAt(active.getUpdateAt())
                .value(active.getValue())
                .signal(returnSignal(active))
                .timeOffer(active.getTimeOffer())
                .variants(lsVariantsResponse)
                .build();
    }

    private SignalRequest returnSignal(final ActiveCustom active) {
        if (active.getIsBuy())
            return SignalRequest.builder()
                    .signalBuy(SignalObject.builder()
                            .force(active.getSignal())
                            .build())
                    .signalSale(null)
                    .build();

        return SignalRequest.builder()
                .signalBuy(null)
                .signalSale(SignalObject.builder()
                        .force(active.getSignal())
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
                .category(parseString(isNullOrElse(request.category(), before.getCategory())))
                .createAt(before.getCreateAt())
                .updateAt(LocalDateTime.now())
                .build();
    }

    public ActiveResponse mapper(final ActiveModel model) {
        return ActiveResponse.builder()
                .activeId(model.getId())
                .name(model.getName())
                .description(model.getDescription())
                .category(model.getCategory())
                .createAt(model.getCreateAt())
                .updateAt(model.getUpdateAt())
                .build();
    }
}
