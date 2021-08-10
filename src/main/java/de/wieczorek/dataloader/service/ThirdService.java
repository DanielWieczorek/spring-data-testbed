package de.wieczorek.dataloader.service;

import de.wieczorek.dataloader.entity.Third;
import de.wieczorek.dataloader.entity.bo.FirstBo;
import de.wieczorek.dataloader.entity.bo.SecondBo;
import de.wieczorek.dataloader.entity.bo.ThirdBo;
import de.wieczorek.dataloader.repository.ThirdRepository;
import de.wieczorek.dataloader.service.mapping.ThirdMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ThirdService {

    @Autowired
    private ThirdRepository repo;

    @Autowired
    private ThirdMappingService mapper;

    public Set<ThirdBo> findAllBySecondAndLink(MappingContext<Integer> mappingContext, Set<SecondBo> seconds) {
        var secondIdToSecond = seconds.stream().collect(Collectors.toMap(SecondBo::getId, Function.identity()));
        var secondIds = seconds.stream().map(SecondBo::getId).collect(Collectors.toList());

        var result = new HashSet<ThirdBo>();

        repo.getThirdForSecondList(secondIds)
                .forEach(x -> {
                    var second = secondIdToSecond.get(x.get(0, Integer.class));
                    var third = x.get(1, Third.class);
                    var mapped = mapper.map(mappingContext, third);
                    second.getThirds().add(mapped);
                });

        mappingContext.addAll(ThirdBo.class, ThirdBo::getId, result);
        return result;
    }

    public Set<ThirdBo> findAllByFirstAndLink(MappingContext<Integer> mappingContext, Set<FirstBo> firsts) {
        var firstIdToFirst = firsts.stream().collect(Collectors.toMap(FirstBo::getId, Function.identity()));
        var firstIds = firsts.stream().map(FirstBo::getId).collect(Collectors.toList());

        var result = new HashSet<ThirdBo>();

        repo.getThirdForFirstList(firstIds)
                .forEach(x -> {
                    var first = firstIdToFirst.get(x.get(0, Integer.class));
                    var third = x.get(1, Third.class);
                    var mapped = mapper.map(mappingContext, third);
                    result.add(mapped);
                    first.getThirds().add(mapped);
                });

        mappingContext.addAll(ThirdBo.class, ThirdBo::getId, result);
        return result;
    }
}
