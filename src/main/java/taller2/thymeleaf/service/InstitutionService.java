package taller2.thymeleaf.service;

import java.util.Optional;

import taller2.thymeleaf.Exception.LogicalException;
import taller2.thymeleaf.model.Institution;

public interface InstitutionService{
	
	public Institution crearInstitucion(Institution institution) throws LogicalException;
	public Institution getInstitutionById(long id) throws LogicalException;
	public Optional<Institution> search (long id);
	public Iterable<Institution> findAll();
	public Institution save(Institution institution);
	
	
}
