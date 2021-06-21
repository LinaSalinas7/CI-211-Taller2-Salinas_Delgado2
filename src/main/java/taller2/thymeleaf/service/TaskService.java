package taller2.thymeleaf.service;

import java.util.Optional;

import taller2.thymeleaf.Exception.LogicalException;
import taller2.thymeleaf.model.Task;

public interface TaskService {
	
	public Task creatTarea(Task task) throws LogicalException;
	public Task editartarea(Task task) throws LogicalException;
	public Task getTaskById(long id) throws LogicalException;
	public Optional<Task> searchTask(long id);
	public Iterable<Task> findAll();
	public Task saveTask(Task task);
	public void delete(Task task);
	public Optional<Task> findById(long id);
	

}
