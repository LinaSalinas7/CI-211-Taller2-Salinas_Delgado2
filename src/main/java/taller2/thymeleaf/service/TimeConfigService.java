package taller2.thymeleaf.service;

import java.util.Optional;

import taller2.thymeleaf.Exception.LogicalException;
import taller2.thymeleaf.model.Timerconfig;

public interface TimeConfigService {
	
	public Timerconfig guardarTimeConfig(Timerconfig timerConfig) throws LogicalException;
	public Timerconfig getTaskTypeById(long id) throws LogicalException;
	public Timerconfig editarTimeConfig(Timerconfig timerconfig) throws LogicalException;
	public Optional<Timerconfig> searchTimeConfig(long id);
	public Iterable<Timerconfig> findAll();
	public Timerconfig saveTimerConfig(Timerconfig timerConfig);
	public void delete(Timerconfig timerConfig);
	public Optional<Timerconfig> findById(long id);

}
