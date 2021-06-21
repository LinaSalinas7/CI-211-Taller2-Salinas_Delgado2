package taller2.thymeleaf.service;

import java.util.Optional;

import taller2.thymeleaf.Exception.LogicalException;
import taller2.thymeleaf.model.Tasktype;

public interface TaskTypeService {
	
	public Tasktype saveTaskType(Tasktype taskType);
	public Tasktype getTaskTypeById(long id) throws LogicalException;
	public Tasktype editTaskType(Tasktype Tasktype) throws LogicalException;
	//public Tasktype addTaskType(Long idInstitution);
	public Optional<Tasktype> searchTaskType(long id);
	public Iterable<Tasktype> findAll();
	public void delete(Tasktype taskType);
	public Optional<Tasktype> findById(long id);
	
	
	
	

}
