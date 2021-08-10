package de.wieczorek.dataloader.repository;

import de.wieczorek.dataloader.entity.Second;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Set;

public interface SecondRepository extends CrudRepository<Second, Integer> {

    @Query("select f.id, s from First f inner join f.seconds s where f.id in ?1")
    Set<Tuple> getSecondForFirstList(List<Integer> firsts);

    @Query("select s.id, s.parent from Second s where s.id in ?1")
    Set<Tuple> getSecondForSecondList(List<Integer> seconds);

}
