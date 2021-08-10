package de.wieczorek.dataloader.service.mapping;

import de.wieczorek.dataloader.entity.Second;
import de.wieczorek.dataloader.entity.bo.SecondBo;
import de.wieczorek.dataloader.service.MappingContext;
import org.springframework.stereotype.Service;

@Service
public class SecondMappingService {

    public SecondBo map(MappingContext<Integer> context, Second entity) {
        var cached = context.get(SecondBo.class, entity.getId());
        if (cached == null) {
            var mapped = SecondBo.builder()
                    .id(entity.getId())
                    .secondField(entity.getSecondField())
                    .build();

            context.add(SecondBo.class, SecondBo::getId, mapped);
            return mapped;
        }
        return cached;
    }
}
