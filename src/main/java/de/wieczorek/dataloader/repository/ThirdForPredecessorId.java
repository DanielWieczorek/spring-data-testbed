package de.wieczorek.dataloader.repository;

import de.wieczorek.dataloader.entity.Third;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ThirdForPredecessorId {
    int predecessorId;
    Third third;
}
