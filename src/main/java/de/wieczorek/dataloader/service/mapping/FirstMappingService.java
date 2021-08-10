package de.wieczorek.dataloader.service.mapping;

import de.wieczorek.dataloader.entity.First;
import de.wieczorek.dataloader.entity.bo.FirstBo;
import de.wieczorek.dataloader.service.MappingContext;
import org.springframework.stereotype.Service;

@Service
public class FirstMappingService {

    public FirstBo map(MappingContext<Integer> context, First entity) {
        var cached = context.get(FirstBo.class, entity.getId());
        if (cached == null) {
            var mapped = FirstBo.builder()
                    .id(entity.getId())
                    .firstField(entity.getFirstField())
                    .build();
            context.add(FirstBo.class, FirstBo::getId, mapped);
            return mapped;
        }
        return cached;
    }
}
