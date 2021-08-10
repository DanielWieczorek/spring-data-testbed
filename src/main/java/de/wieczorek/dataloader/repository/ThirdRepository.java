package de.wieczorek.dataloader.repository;

import de.wieczorek.dataloader.entity.Third;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Tuple;
import java.util.List;

public interface ThirdRepository extends CrudRepository<Third,Integer> {

    @Query("select s.id, t from Second s inner join s.thirds t where s.id in ?1")
     List<Tuple> getThirdForSecondList(List<Integer> seconds);

    @Query("select s.id, t from First s inner join s.thirds t where s.id in ?1")
    List<Tuple> getThirdForFirstList(List<Integer> firsts);


}
