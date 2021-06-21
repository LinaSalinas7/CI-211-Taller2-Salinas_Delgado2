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
import taller2.thymeleaf.model.FetTaskinstance;
import taller2.thymeleaf.service.FetTaskInstanceService;
import taller2.thymeleaf.service.TaskService;

@Controller
public class FetTaskInstanceControllerlmpl {
	
	private TaskService taskService;
	private FetTaskInstanceService fetTaskInstanceService;
	
	@Autowired 
	public FetTaskInstanceControllerlmpl(TaskService taskService, FetTaskInstanceService fetTaskInstanceService) {
		this.taskService = taskService;
		this.fetTaskInstanceService = fetTaskInstanceService;
		
	}
	
	@GetMapping("/taskInstance/")
	public String indexTaskInstance(Model model) {
		model.addAttribute("taskInstances", fetTaskInstanceService.findAll());
		return "fetTaskInstance/index-fetTaskInstance";
	}
	
	@GetMapping("/taskInstance/add-taskInstance")
	public String addTaskInstance(Model model, @ModelAttribute("taskInstance") FetTaskinstance taskInstance) {
		model.addAttribute("taskInstance", new FetTaskinstance());
		model.addAttribute("tasks", taskService.findAll());
		return "fetTaskInstance/add-fetTaskInstance";
	}

	@PostMapping("/taskInstance/add-taskInstance")
	public String saveTaskInstance(@Validated(Validation1.class) FetTaskinstance taskInstance, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancel"))
			if (bindingResult.hasErrors()) {
				model.addAttribute("tasks", taskService.findAll());
				return "fetTaskInstance/add-fetTaskInstance";
			} else {
				fetTaskInstanceService.saveFetTaskInstance(taskInstance);		}
		return "redirect:/fetTaskInstance/";
	}
	
	@GetMapping("/taskInstance/edit/{id}")
	public String showUpDateForm(@PathVariable("id") long id, Model model) throws LogicalException {
		Optional<FetTaskinstance> taskInstance = fetTaskInstanceService.findById(id);
		if (taskInstance==null) {
			throw new LogicalException("Invalid Id:"+id);
		}
		
		model.addAttribute("taskInstance", taskInstance.get());
		model.addAttribute("task", taskService.findAll());
		return "fetTaskInstance/edit-fetTaskInstance";
			
	}
	
	@PostMapping("/taskInstance/edit/{id}")
	public String upDateUser(@PathVariable("id") long id, 
			@RequestParam(value = "action", required = true) String action,
			@Validated(Validation1.class) FetTaskinstance taskInstance, BindingResult bindingResult, Model model) {
		if(action != null && !action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("taskInstance", taskInstance);
				model.addAttribute("tasks", taskService.findAll());
				return "fetTaskInstance/edit-fetTaskInstance";
			}
			fetTaskInstanceService.saveFetTaskInstance(taskInstance);
		}
		return "redirect:/fetTaskInstance/";
	}
	
	@GetMapping("/taskInstance/delete/{id}")
	public String deleteTaskInstance(@PathVariable("id")long id, Model model) throws LogicalException {
		FetTaskinstance taskInstance = fetTaskInstanceService.findById(id)
				.orElseThrow(()-> new LogicalException("Invalid Id:"+id));
		fetTaskInstanceService.delete(taskInstance);
		return "redirect:/fetTaskInstance/";
	
	}


	

}
