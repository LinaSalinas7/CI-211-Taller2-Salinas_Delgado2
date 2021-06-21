package taller2.thymeleaf.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taller2.thymeleaf.Exception.LogicalException;
import taller2.thymeleaf.model.Actionn;
import taller2.thymeleaf.repsitory.ActionnRepository;

@Service
public class ActionnServicelmpl implements ActionnService{
	
	private ActionnRepository actionnRepository;
	
	@Autowired
	public ActionnServicelmpl(ActionnRepository actionnRepository) {
		this.actionnRepository = actionnRepository;
	}

	@Override
	public Actionn crearActionn(Actionn actionn) throws LogicalException {
		if(actionn == null) {
			throw new  LogicalException("La Acción no puede ser vacia");
		}
		actionnRepository.save(actionn);
		return actionn;
	}

	@Override
	public Actionn getActionnById(long id) throws LogicalException {
		try {
			Actionn actionn = actionnRepository.findById(id).get();
			return actionn;
		}catch(NoSuchElementException e) {
			throw new LogicalException("No se ha encontrado la Acción");
		}
	}

	@Override
	public Optional<Actionn> searchActionn(long id) {
		
		Optional<Actionn> optional = actionnRepository.findById(id);
		return optional;
	}

	@Override
	public Iterable<Actionn> findAll() {
		return actionnRepository.findAll();
	}

	@Override
	public Actionn save(Actionn actionn) {
		return actionnRepository.save(actionn);
	}

	
}
