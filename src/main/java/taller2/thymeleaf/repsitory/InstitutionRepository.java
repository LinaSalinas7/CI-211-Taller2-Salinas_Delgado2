package taller2.thymeleaf.repsitory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import taller2.thymeleaf.model.Institution;

@Repository
public interface InstitutionRepository extends CrudRepository<Institution,Long> {

}
