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
import taller2.thymeleaf.model.Timerconfig;
import taller2.thymeleaf.service.InstitutionService;
import taller2.thymeleaf.service.TaskService;
import taller2.thymeleaf.service.TimeConfigService;

@Controller
public class TimeConfigControllerlmpl{
	
	private TimeConfigService timerConfigService;
	private TaskService taskService;
	private InstitutionService institutionService;
	
	@Autowired
	public TimeConfigControllerlmpl(TimeConfigService timerConfigService, TaskService taskService, InstitutionService institutionService) {
		this.timerConfigService = timerConfigService;
		this.taskService = taskService;
		this.institutionService = institutionService;
	}
	
	@GetMapping("/login")
	public String login() {
		return "/login";
	}
	
	@GetMapping("/timeConfig/")
	public String indextimerConfig(Model model) {
		model.addAttribute("timerConfigs", timerConfigService.findAll());
		return "timeConfig/index-timeConfig";
	}
	
	@GetMapping("/timerConfig/add-timerConfig")
	public String addtimerConfig(Model model, @ModelAttribute("timerConfig") Timerconfig timerConfig) {
		model.addAttribute("timerConfig", new Timerconfig());
		model.addAttribute("institutions", institutionService.findAll());
		model.addAttribute("tasks", taskService.findAll());
		return "timeConfig/add-timeConfig";
	}
	
	@PostMapping("/timerConfig/add-timerConfig")
	public String saveTimerConfig(@Validated(Validation1.class) Timerconfig timerConfig, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancel"))
			if (bindingResult.hasErrors()) {
				model.addAttribute("institutions", institutionService.findAll());
				model.addAttribute("tasks", taskService.findAll());
				return "timeConfig/add-timeConfig";
			} else {
				timerConfigService .saveTimerConfig(timerConfig);		}
		return "redirect:/timeConfig/";
	}
	
	@GetMapping("/timerConfig/edit/{id}")
	public String showUpDateForm(@PathVariable("id") long id, Model model) throws LogicalException {
		Optional<Timerconfig> timerConfig = timerConfigService.findById(id);
		if (timerConfig==null) {
			throw new LogicalException("Invalid user Id:"+id);
		}
		
		model.addAttribute("timerConfig", timerConfig.get());
		model.addAttribute("institutions", institutionService.findAll());
		model.addAttribute("tasks", taskService.findAll());
		return "timeConfig/edit-timeConfig";
			
	}
	
	@PostMapping("/timerConfig/edit/{id}")
	public String upDateUser(@PathVariable("id") long id, 
			@RequestParam(value = "action", required = true) String action,
			@Validated(Validation1.class) Timerconfig timerConfig, BindingResult bindingResult, Model model) {
		if(action != null && !action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("timerConfig", timerConfig);
				model.addAttribute("institutions", institutionService.findAll());
				return "timeConfig/edit-timeConfig";
			}
			timerConfigService.saveTimerConfig(timerConfig);
		}
		return "redirect:/timeConfig/";
	}
	
	@GetMapping("/timerConfig/delete/{id}")
	public String deleteTimerConfig(@PathVariable("id")long id, Model model) throws LogicalException {
		Timerconfig timerConfig = timerConfigService.findById(id)
				.orElseThrow(()-> new LogicalException("Invalid user Id:"+id));
		timerConfigService.delete(timerConfig);
		return "redirect:/timeConfig/";
	
	}
	
}
