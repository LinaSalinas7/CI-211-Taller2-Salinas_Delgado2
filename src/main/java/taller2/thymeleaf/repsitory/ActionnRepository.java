package taller2.thymeleaf.repsitory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import taller2.thymeleaf.model.Actionn;

@Repository
public interface ActionnRepository extends CrudRepository<Actionn, Long> {

}
