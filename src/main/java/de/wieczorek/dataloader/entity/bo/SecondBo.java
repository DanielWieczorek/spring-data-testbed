package de.wieczorek.dataloader.entity.bo;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class SecondBo {

    Integer id;
    String secondField;
    @Builder.Default
    List<ThirdBo> thirds = new ArrayList<>();

    SecondBo parent;
}
