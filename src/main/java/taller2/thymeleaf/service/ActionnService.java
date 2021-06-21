package taller2.thymeleaf.service;

import java.util.Optional;

import taller2.thymeleaf.Exception.LogicalException;
import taller2.thymeleaf.model.Actionn;

public interface ActionnService {
	
	public Actionn crearActionn(Actionn actionn) throws LogicalException;
	public Actionn getActionnById(long id) throws LogicalException;
	public Optional<Actionn> searchActionn(long id);
	public Iterable<Actionn> findAll();
	public Actionn save(Actionn actionn);
	

}
