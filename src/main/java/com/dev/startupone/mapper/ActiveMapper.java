package com.dev.startupone.mapper;

import com.dev.startupone.lib.data.domain.ActiveModel;
import com.dev.startupone.lib.data.domain.VariantModel;
import com.dev.startupone.lib.data.dto.active.ActiveRequest;
import com.dev.startupone.lib.data.dto.active.SignalRequest;
import com.dev.startupone.lib.data.dto.active.VariantRequest;
import org.springframework.stereotype.Component;

import static com.dev.startupone.lib.util.ValidationUtils.isNull;
@Component
public class ActiveMapper {
    public ActiveModel mapper(final ActiveRequest active) {
        return ActiveModel.builder()
                .name(active.name())
                .description(active.description())
                .category(active.category())
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
}
