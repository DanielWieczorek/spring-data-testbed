package de.wieczorek.dataloader.service.mapping;

import de.wieczorek.dataloader.entity.Third;
import de.wieczorek.dataloader.entity.bo.ThirdBo;
import de.wieczorek.dataloader.service.MappingContext;
import org.springframework.stereotype.Service;

@Service
public class ThirdMappingService {

    public ThirdBo map(MappingContext<Integer> context, Third entity) {
        var cached = context.get(ThirdBo.class, entity.getId());
        if (cached == null) {
            var mapped = ThirdBo.builder()
                    .id(entity.getId())
                    .thirdField(entity.getThirdField())
                    .build();

            context.add(ThirdBo.class, ThirdBo::getId, mapped);
            return mapped;
        }
        return cached;
    }
}
