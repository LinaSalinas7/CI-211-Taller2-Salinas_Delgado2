package taller2.thymeleaf.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import taller2.thymeleaf.Exception.LogicalException;
import taller2.thymeleaf.model.Task;
import taller2.thymeleaf.repsitory.TaskRepository;

@Service
public class TaskServicelmpl implements TaskService {

	private TaskRepository taskRepository;
	private ActionnService actionnService;
	private TaskTypeService taskTypeService;

	public TaskServicelmpl(TaskRepository taskRepository, ActionnService actionnService, TaskTypeService taskTypeService) {
		this.taskRepository= taskRepository;
		this.actionnService = actionnService;
		this.taskTypeService = taskTypeService;
	}

	@Override
	public Task creatTarea(Task task) throws LogicalException {
		if(task == null) {
			throw new LogicalException("La tarea no puede ser vacia");
		}
		else if(task.getTaskTimeunit()==null|| task.getTaskTimevalue() ==null) {
			throw new LogicalException("La unidad y valor de tiempo no pueden ser vacios");
		}
		else {
			actionnService.getActionnById(task.getTaskId());
			taskTypeService.getTaskTypeById(task.getTaskId());
			taskRepository.save(task);
			return task;
		}
	}

	@Override
	public Task editartarea(Task task) throws LogicalException {
		
		if(task==null) {
			throw new LogicalException("La tarea no puede ser vacia");
		}
		
		actionnService.getActionnById(task.getTaskId());
		taskTypeService.getTaskTypeById(task.getTaskId());
		
		Task taskTemp = getTaskById(task.getTaskId());
		
		 if(taskTemp.getTaskTimeunit()==null|| task.getTaskTimevalue() ==null) {
				throw new LogicalException("La unidad y valor de tiempo no pueden ser vacios");
		 }
		 
		 else {
			 taskTemp.setActionn(task.getActionn());
			 taskTemp.setTaskId(task.getTaskId());
			 taskTemp.setTaskTimeunit(task.getTaskTimeunit());
			 taskTemp.setTaskTimevalue(task.getTaskTimevalue());
			 taskTemp.setTaskTriggervalue(task.getTaskTriggervalue());
			 taskRepository.save(taskTemp);
			 return taskTemp;
		 }

	}

	@Override
	public Task getTaskById(long id) throws LogicalException {
		try {
			Task task = taskRepository.findById(id).get();
			return task;
		}catch(NoSuchElementException e) {
			throw new LogicalException("No se ha encontrado la Tarea");
		}

	}

	@Override
	public Optional<Task> searchTask(long id) {
		Optional<Task> optional = taskRepository.findById(id);
		return optional;
	}

	@Override
	public Iterable<Task> findAll() {
		return taskRepository.findAll();
	}

	@Override
	public Task saveTask(Task task) {

		return taskRepository.save(task);
	}

	@Override
	public void delete(Task task) {
		taskRepository.delete(task);
	}

	@Override
	public Optional<Task> findById(long id) {
		return taskRepository.findById(id);
	}

}
