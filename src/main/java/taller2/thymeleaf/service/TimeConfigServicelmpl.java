package taller2.thymeleaf.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taller2.thymeleaf.Exception.LogicalException;
import taller2.thymeleaf.model.Timerconfig;
import taller2.thymeleaf.repsitory.TimeConfigRepository;

@Service
public class TimeConfigServicelmpl implements TimeConfigService{
	
	private TimeConfigRepository timeConfigRepository;
	private InstitutionService institutionService;
	private TaskService taskService;
	
	@Autowired 
	public TimeConfigServicelmpl(TimeConfigRepository timeConfigRepository, InstitutionService institutionService, TaskService taskService) {
		this.timeConfigRepository=timeConfigRepository;
		this.institutionService = institutionService;
		this.taskService = taskService;
	}

	@Override
	public Timerconfig guardarTimeConfig(Timerconfig timerConfig) throws LogicalException {
		if(timerConfig==null) {
			throw new LogicalException("La configuración de tiempo no puede ser nula");
		}
		institutionService.getInstitutionById(timerConfig.getTimconId());
		taskService.getTaskById(timerConfig.getTimconId());
		timeConfigRepository.save(timerConfig);
		return timerConfig;
	}

	@Override
	public Timerconfig getTaskTypeById(long id) throws LogicalException {
		try {
			Timerconfig timeConfig = timeConfigRepository.findById(id).get();
			return timeConfig;
		}catch(NoSuchElementException e) {
			throw new LogicalException("No se ha encontrado el tiempo de configuración");
		}
	}

	@Override
	public Timerconfig editarTimeConfig(Timerconfig timerconfig) throws LogicalException {
		if(timerconfig==null) {
			throw new LogicalException("La configuración de tiempo no puede ser nula");		
		}
		institutionService.getInstitutionById(timerconfig.getTimconId());
		taskService.getTaskById(timerconfig.getTimconId());
		
		Timerconfig timeCProv = getTaskTypeById(timerconfig.getTimconId());

		if(timeCProv.getInstInstId()==null ||  timeCProv.getTimconTimerstring()==null || timeCProv.getTask() == null) {
			throw new LogicalException("El valor no puede ser null");
		}
		else {
			timeCProv.setInstInstId(timerconfig.getInstInstId());
			timeCProv.setTask(timerconfig.getTask());
			timeCProv.setTimconId(timerconfig.getTimconId());
			timeCProv.setTimconTimerstring(timerconfig.getTimconTimerstring());
			timeConfigRepository.save(timeCProv);
			return timeCProv;
		}
		
	}

	@Override
	public Optional<Timerconfig> searchTimeConfig(long id) {
		Optional<Timerconfig> optional = timeConfigRepository.findById(id);
		return optional;
	}

	@Override
	public Iterable<Timerconfig> findAll() {
		return timeConfigRepository.findAll();
	}

	@Override
	public Timerconfig saveTimerConfig(Timerconfig timerConfig) {
		return timeConfigRepository.save(timerConfig);
	}

	@Override
	public void delete(Timerconfig timerConfig) {
		timeConfigRepository.delete(timerConfig);
		
	}

	@Override
	public Optional<Timerconfig> findById(long id) {
		return timeConfigRepository.findById(id);
	}

}
