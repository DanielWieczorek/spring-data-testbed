package de.wieczorek.dataloader.service;

import de.wieczorek.dataloader.entity.bo.FirstBo;
import de.wieczorek.dataloader.repository.FirstRepository;
import de.wieczorek.dataloader.service.mapping.FirstMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FirstService {

    @Autowired
    private FirstRepository repo;

    @Autowired
    private FirstMappingService mapper;

    public Set<FirstBo> findAll(MappingContext<Integer> mappingContext) {
        var result = StreamSupport.stream(repo.findAll().spliterator(), false)
                .map(x -> mapper.map(mappingContext, x))
                .collect(Collectors.toSet());

        mappingContext.addAll(FirstBo.class, FirstBo::getId, result);
        return result;
    }
}
