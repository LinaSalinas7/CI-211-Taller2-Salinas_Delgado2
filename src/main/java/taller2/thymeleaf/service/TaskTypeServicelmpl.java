package taller2.thymeleaf.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taller2.thymeleaf.Exception.LogicalException;
import taller2.thymeleaf.model.Tasktype;
import taller2.thymeleaf.repsitory.TaskTypeRepository;

@Service
public class TaskTypeServicelmpl implements TaskTypeService {
	
	private TaskTypeRepository taskTypeRepository;
	private InstitutionService institutionService;

	@Autowired 
	public TaskTypeServicelmpl(TaskTypeRepository taskTypeRepository, InstitutionService institutionService) {
		this.taskTypeRepository = taskTypeRepository;
		this.institutionService = institutionService;
	}

	@Override
	public Tasktype saveTaskType(Tasktype taskType){
		
		return taskTypeRepository.save(taskType);
			
		
	}

	@Override
	public Tasktype editTaskType(Tasktype tasktype) throws LogicalException {
		if(tasktype==null) {
			throw new LogicalException("El tipo de tarea no puede ser null");			
		}
		institutionService.getInstitutionById(tasktype.getTasktypeId());
		Tasktype taskTypeProv = getTaskTypeById(tasktype.getTasktypeId());
		
		if(taskTypeProv.getInstInstId()==null) {
			throw new LogicalException("El identificador de la institución no puede ser null");	
		}
		else if(taskTypeProv.getTasks()==null) {
			throw new LogicalException("El identificador de la tarea no puede ser null");	
		}
		else if(taskTypeProv.getTasktypeName().equals("")) {
			throw new LogicalException("El el nombre del tipo de tarea no puede ser null");	
		}
		else {
			taskTypeProv.setInstInstId(tasktype.getInstInstId());
			taskTypeProv.setTasktypeId(tasktype.getTasktypeId());
			taskTypeProv.setTasktypeName(tasktype.getTasktypeName());
			taskTypeProv.setTasks(tasktype.getTasks());
			taskTypeRepository.save(taskTypeProv);
			return taskTypeProv;
		}
	}

	@Override
	public Tasktype getTaskTypeById(long id) throws LogicalException {
		try {
			Tasktype taskType = taskTypeRepository.findById(id).get();
			return taskType;
		}catch(NoSuchElementException e) {
			throw new LogicalException("No se ha encontrado la Tarea");
		}
	}
	
	@Override
	public Optional<Tasktype> searchTaskType(long id) {
		
		Optional<Tasktype> optional = taskTypeRepository.findById(id);
		return optional;
		
	}
	@Override
	public Iterable<Tasktype> findAll(){
		return taskTypeRepository.findAll();
	}

	@Override
	public void delete(Tasktype taskType) {
		taskTypeRepository.delete(taskType);
		
	}

	@Override
	public Optional<Tasktype> findById(long id) {
		return taskTypeRepository.findById(id);
	}

}
