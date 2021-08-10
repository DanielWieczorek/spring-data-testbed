package de.wieczorek.dataloader.entity.bo;

import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Builder
@Value
public class FirstBo {

    Integer id;
    String firstField;
    @Builder.Default
    List<SecondBo> seconds = new ArrayList<>();
    @Builder.Default
    List<ThirdBo> thirds = new ArrayList<>();
}
