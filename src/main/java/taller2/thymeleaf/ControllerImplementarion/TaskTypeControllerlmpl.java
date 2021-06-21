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
import taller2.thymeleaf.model.Tasktype;
import taller2.thymeleaf.service.InstitutionService;
import taller2.thymeleaf.service.TaskTypeService;

@Controller
public class TaskTypeControllerlmpl{

	private TaskTypeService taskTypeService;
	private InstitutionService institutionService; 


	@Autowired
	public TaskTypeControllerlmpl(TaskTypeService taskTypeService, InstitutionService institutionService) {
		this.taskTypeService = taskTypeService;
		this.institutionService = institutionService;
	}

	@GetMapping("/taskType/")
	public String indexTaskType(Model model) {
		model.addAttribute("taskTypes", taskTypeService.findAll());
		return "taskType/index-taskType";
	}
	
	@GetMapping("/taskType/add-taskType")
	public String addTaskType(Model model, @ModelAttribute("taskType") Tasktype taskType) {
		model.addAttribute("taskType", new Tasktype());
		model.addAttribute("institutions", institutionService.findAll());
		return "taskType/add-taskType";
	}

	@PostMapping("/taskType/add-taskType")
	public String saveTaskType(@Validated(Validation1.class) Tasktype taskType, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancel"))
			if (bindingResult.hasErrors()) {
				model.addAttribute("institutions", institutionService.findAll());
				return "taskType/add-taskType";
			} else {
				taskTypeService.saveTaskType(taskType);		}
		return "redirect:/taskType/";
	}
	
	@GetMapping("/taskType/edit/{id}")
	public String showUpDateForm(@PathVariable("id") long id, Model model) throws LogicalException {
		Optional<Tasktype> tasktype = taskTypeService.findById(id);
		if (tasktype==null) {
			throw new LogicalException("Invalid user Id:"+id);
		}
		
		model.addAttribute("taskType", tasktype.get());
		model.addAttribute("institution", institutionService.findAll());
		return "taskType/edit-taskType";
			
	}
	
	@PostMapping("/taskType/edit/{id}")
	public String upDateUser(@PathVariable("id") long id, 
			@RequestParam(value = "action", required = true) String action,
			@Validated(Validation1.class) Tasktype taskType, BindingResult bindingResult, Model model) {
		if(action != null && !action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("taskType", taskType);
				model.addAttribute("institution", institutionService.findAll());
				return "taskType/edit-taskType";
			}
			taskTypeService.saveTaskType(taskType);
		}
		return "redirect:/taskType/";
	}
	
	@GetMapping("/taskType/delete/{id}")
	public String deleteTaskType(@PathVariable("id")long id, Model model) throws LogicalException {
		Tasktype taskType = taskTypeService.findById(id)
				.orElseThrow(()-> new LogicalException("Invalid Id:"+id));
		taskTypeService.delete(taskType);
		return "redirect:/taskType/";
	
	}

	

}