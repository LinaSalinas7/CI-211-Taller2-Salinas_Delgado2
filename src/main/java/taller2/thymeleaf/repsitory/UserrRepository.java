package taller2.thymeleaf.repsitory;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import taller2.thymeleaf.model.Userr;

@Repository
public interface UserrRepository extends CrudRepository<Userr, Long> {
	Optional<Userr> findByUserName(String username);

}
	