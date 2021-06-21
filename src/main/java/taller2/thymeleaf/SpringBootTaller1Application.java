package taller2.thymeleaf;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;


import taller2.thymeleaf.model.Institution;
import taller2.thymeleaf.model.UserType;
import taller2.thymeleaf.model.Userr;
import taller2.thymeleaf.service.ActionnService;
import taller2.thymeleaf.service.FetTaskInstanceService;
import taller2.thymeleaf.service.InstitutionService;
import taller2.thymeleaf.service.TaskService;
import taller2.thymeleaf.service.TaskTypeService;
import taller2.thymeleaf.service.TimeConfigService;
import taller2.thymeleaf.service.UserrService;
import taller2.thymeleaf.model.Actionn;
//import taller2.thymeleaf.model.Actiontype;
//import taller2.thymeleaf.model.Autotransition;

@SpringBootApplication
//@ComponentScan("taller2.thymeleaf")
public class SpringBootTaller1Application {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	public static void main(String[] args) {

		SpringApplication.run(SpringBootTaller1Application.class, args);
	}

	@Bean
	public CommandLineRunner dummy(UserrService userrService, InstitutionService institutionService, ActionnService actionnService, 
			TaskService taskService, TaskTypeService taskTypeService, FetTaskInstanceService fetTaskInstanceService, 
			TimeConfigService timeConfig) {
		return (args) -> {


			Userr userrAdmin = new Userr();
			userrAdmin.setInstInstId(new BigDecimal(82388));
			userrAdmin.setUserName("Lina");
			userrAdmin.setType(UserType.administrador);
			userrAdmin.setUserPassword("{noop}12345");
			userrService.save(userrAdmin);

			Userr userrOp = new Userr();
			userrOp.setInstInstId(new BigDecimal(8372372));
			userrOp.setUserName("Daniel");
			userrOp.setType(UserType.operador);
			userrOp.setUserPassword("{noop}56789");
			userrService.save(userrOp );

			Institution institution1 = new Institution();
			institution1.setInstId(8372372);
			institution1.setInstName("Icesi");
			institution1.setInstAcademicserverurl("www.Hola.com");
			institution1.setInstAcadprogrammedcoursesurl("wwww.Sistemas.com");
			institution1.setInstAcadphysicalspacesurl("www.232.com");
			institution1.setInstAcadloginurl("www.jsjdj.com");
			institutionService.save(institution1);
			
			Institution institution2 = new Institution();
			institution1.setInstId(8372374);
			institution1.setInstName("Javeriana");
			institution1.setInstAcademicserverurl("www.Javeriana.com");
			institution1.setInstAcadprogrammedcoursesurl("wwww.sistemas.com");
			institution1.setInstAcadphysicalspacesurl("www.2323.com");
			institution1.setInstAcadloginurl("www.hola.com");
			institutionService.save(institution2);
			
			
			
			
			
		
			
		};
	}
	
	/*

	ConfigurableApplicationContext c = 

	UserrService userr = c.getBean(UserrService.class);
	InstitutionService institution = c.getBean(InstitutionService.class);

	Userr userrAdmin = new Userr();
	userrAdmin.setInstInstId(new BigDecimal(82388));
	userrAdmin.setUserName("Lina");
	userrAdmin.setType(UserType.administrador);
	userrAdmin.setUserPassword("{noop}dknjsdjds");
	userr.save(userrAdmin);

	Userr userrOp = new Userr();
	userrOp.setInstInstId(new BigDecimal(74362));
	userrOp.setUserName("Daniel");
	userrOp.setType(UserType.operador);
	userrOp.setUserPassword("{noop}ñakslks");
	userr.save(userrOp );

	Institution institution1 = new Institution();
	institution1.setInstId(8372372);
	institution1.setInstName("Icesi");
	institution1.setInstAcademicserverurl("www.Hola.com");
	institution1.setInstAcadprogrammedcoursesurl("wwww.Sistemas.com");
	institution1.setInstAcadphysicalspacesurl("www.232.com");
	institution1.setInstAcadloginurl("www.jsjdj.com");
	institution.save(institution1);*/


}
