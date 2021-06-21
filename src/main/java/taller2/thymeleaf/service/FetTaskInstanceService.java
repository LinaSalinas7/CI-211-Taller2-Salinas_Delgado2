package taller2.thymeleaf.service;

import java.util.Optional;

import taller2.thymeleaf.Exception.LogicalException;
import taller2.thymeleaf.model.FetTaskinstance;

public interface FetTaskInstanceService {
	
	public Optional<FetTaskinstance> searchFetTaskinstance(long id) throws LogicalException; 
	public FetTaskinstance editarInstanciaTarea(FetTaskinstance fetTaskinstance) throws LogicalException;
	public FetTaskinstance crearInstanciaTarea(FetTaskinstance fetTaskinstance) throws LogicalException;
	public Iterable<FetTaskinstance> findAll();
	public FetTaskinstance saveFetTaskInstance(FetTaskinstance fetTaskInstance);
	public void delete(FetTaskinstance fetTAskInstance);
	public Optional<FetTaskinstance> findById(long id);

}
