package taller2.thymeleaf.ControllerImplementarion;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Validations.Validation1;
import taller2.thymeleaf.Exception.LogicalException;
import taller2.thymeleaf.model.Task;
import taller2.thymeleaf.service.ActionnService;
import taller2.thymeleaf.service.TaskService;
import taller2.thymeleaf.service.TaskTypeService;

@Controller
public class TaskControllerlmpl {
	
	private TaskService taskService;
	private ActionnService actionnService;
	private TaskTypeService taskTypeService;
	
	@Autowired
	public TaskControllerlmpl(ActionnService actionnService, TaskTypeService taskTypeService, TaskService taskService) {
		this.taskService = taskService;
		this.actionnService = actionnService;
		this.taskTypeService = taskTypeService;
	}
	
	@GetMapping("/task/")
	public String indexTask(Model model) {
		model.addAttribute("tasks", taskService.findAll());
		return "task/index-task";
	}
	
	@GetMapping("/task/add-task")
	public String addTask(Model model, @ModelAttribute("task") Task task) {
		model.addAttribute("task", new Task());
		model.addAttribute("actionn", actionnService.findAll());
		model.addAttribute("taskTypes", taskTypeService.findAll());
		return "task/add-task";
	}
	
	@PostMapping("/task/add-task")
	public String saveTask(@Validated(Validation1.class) Task task, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancel"))
			if (bindingResult.hasErrors()) {
				model.addAttribute("actionn", actionnService.findAll());
				model.addAttribute("taskTypes", taskTypeService.findAll());
				return "task/add-task";
			} else {
				taskService.saveTask(task);		}
		return "redirect:/task/";
	}
	
	@GetMapping("/task/edit/{id}")
	public String showUpDateForm(@PathVariable("id") long id, Model model) throws LogicalException {
		Optional<Task> task = taskService.findById(id);
		if (task==null) {
			throw new LogicalException("Invalid user Id:"+id);
		}
		
		model.addAttribute("task", task.get());
		model.addAttribute("actionn", actionnService.findAll());
		model.addAttribute("taskTypes", taskTypeService.findAll());
		return "task/edit-task";
			
	}
	
	@PostMapping("/task/edit/{id}")
	public String upDateUser(@PathVariable("id") long id, 
			@RequestParam(value = "action", required = true) String action,
			@Validated(Validation1.class) Task task, BindingResult bindingResult, Model model) {
		if(action != null && !action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("task", task);
				model.addAttribute("actionn", actionnService.findAll());
				model.addAttribute("taskType", taskTypeService.findAll());
				return "task/edit-task";
			}
			taskService.saveTask(task);
		}
		return "redirect:/task/";
	}
	
	@GetMapping("/task/delete/{id}")
	public String deleteTask(@PathVariable("id")long id, Model model) throws LogicalException {
		Task task = taskService.findById(id)
				.orElseThrow(()-> new LogicalException("Invalid user Id:"+id));
		taskService.delete(task);
		return "redirect:/task/";
	
	}
	

}