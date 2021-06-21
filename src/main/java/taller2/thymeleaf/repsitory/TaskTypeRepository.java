package taller2.thymeleaf.repsitory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import taller2.thymeleaf.model.Tasktype;

@Repository
public interface TaskTypeRepository extends CrudRepository<Tasktype,Long>{

}
