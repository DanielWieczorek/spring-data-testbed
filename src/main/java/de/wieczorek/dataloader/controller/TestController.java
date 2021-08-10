package de.wieczorek.dataloader.controller;

import de.wieczorek.dataloader.entity.First;
import de.wieczorek.dataloader.entity.bo.FirstBo;
import de.wieczorek.dataloader.repository.FirstRepository;
import de.wieczorek.dataloader.service.FirstService;
import de.wieczorek.dataloader.service.MappingContext;
import de.wieczorek.dataloader.service.SecondService;
import de.wieczorek.dataloader.service.ThirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
public class TestController {

    @Autowired
    private FirstRepository repo;

    @Autowired
    private FirstService firstService;

    @Autowired
    private SecondService secondService;

    @Autowired
    private ThirdService thirdService;

    @GetMapping("inefficient")
    public Iterable<First> getInefficient() {
        return repo.findAll();
    }

    @GetMapping("efficient")
    public Iterable<FirstBo> getEfficient() {
        var mappingContext = new MappingContext<Integer>();
        var first = firstService.findAll(mappingContext);

        var second = secondService.findAllByFirstAndLink(mappingContext, first);
        var parentSecond = secondService.findAllByChildrenAndLink(mappingContext, second);
        var grandparentSecond = secondService.findAllByChildrenAndLink(mappingContext, parentSecond);
        var allSeconds = new HashSet<>(second);
        allSeconds.addAll(parentSecond);
        allSeconds.addAll(grandparentSecond);

        var third = thirdService.findAllBySecondAndLink(mappingContext, allSeconds);
        thirdService.findAllByFirstAndLink(mappingContext, first);
        return first;
    }
}
