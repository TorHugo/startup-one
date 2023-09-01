package com.dev.startupone.mapper;

import com.dev.startupone.lib.data.domain.ActiveModel;
import com.dev.startupone.lib.data.domain.VariantModel;
import com.dev.startupone.lib.data.dto.active.*;
import com.dev.startupone.lib.data.enums.CategoryEnum;
import org.springframework.stereotype.Component;

import static com.dev.startupone.lib.util.ValidationUtils.isNull;

@Component
public class ActiveMapper {
    public ActiveModel mapper(final ActiveRequest active) {
        return ActiveModel.builder()
                .name(active.name())
                .description(active.description())
                .category(CategoryEnum.parse(active.category()))
                .build();
    }

    public VariantModel mapper(final VariantRequest variant) {
        return VariantModel.builder()
                .value(variant.value())
                .variation(variant.variation())
                .volume(variant.volume())
                .signal(returnSignal(variant.signal()))
                .build();
    }

    private String returnSignal(final SignalRequest signal){
        return isNull(signal.signalBuy()) ? signal.signalSale().force() : signal.signalBuy().force();
    }

    public ActiveResponse mapper (final ActiveModel active, final VariantModel variant){
        return ActiveResponse.builder()
                .activeId(active.getId())
                .name(active.getName())
                .category(active.getCategory())
                .variant(VariantResponse.builder()
                        .variantId(variant.getId())
                        .value(variant.getValue())
                        .variation(variant.getVariation())
                        .build())
                .build();
    }
}
