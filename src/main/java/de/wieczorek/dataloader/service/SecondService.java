package de.wieczorek.dataloader.service;

import de.wieczorek.dataloader.entity.Second;
import de.wieczorek.dataloader.entity.bo.FirstBo;
import de.wieczorek.dataloader.entity.bo.SecondBo;
import de.wieczorek.dataloader.repository.SecondRepository;
import de.wieczorek.dataloader.service.mapping.SecondMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SecondService {

    @Autowired
    private SecondRepository repo;

    @Autowired
    private SecondMappingService mapper;

    public Set<SecondBo> findAllByFirstAndLink(MappingContext<Integer> mappingContext, Set<FirstBo> firsts) {
        var firstIdToFirst = firsts.stream().collect(Collectors.toMap(FirstBo::getId, Function.identity()));
        var firstIds = firsts.stream().map(FirstBo::getId).collect(Collectors.toList());

        var result = new HashSet<SecondBo>();

        repo.getSecondForFirstList(firstIds)
                .forEach(x -> {
                    var first = firstIdToFirst.get(x.get(0, Integer.class));
                    var second = x.get(1, Second.class);
                    var mapped = mapper.map(mappingContext, second);
                    result.add(mapped);
                    first.getSeconds().add(mapped);
                });

        return result;
    }

    public Set<SecondBo> findAllByChildrenAndLink(MappingContext<Integer> mappingContext, Set<SecondBo> seconds) {
        var secondIdToSecond = seconds.stream().collect(Collectors.toMap(SecondBo::getId, Function.identity()));
        var secondIds = seconds.stream().map(SecondBo::getId).collect(Collectors.toList());

        var result = new HashSet<SecondBo>();

        repo.getSecondForSecondList(secondIds)
                .forEach(x -> {
                    var child = secondIdToSecond.get(x.get(0, Integer.class));
                    var parentEntity = x.get(1, Second.class);
                    var mapped = mapper.map(mappingContext, parentEntity);
                    result.add(mapped);
                    child.setParent(mapped);
                });

        return result;
    }
}
