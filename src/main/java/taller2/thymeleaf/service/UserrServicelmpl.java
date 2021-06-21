package taller2.thymeleaf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taller2.thymeleaf.model.Userr;
import taller2.thymeleaf.repsitory.UserrRepository;


@Service
public class UserrServicelmpl implements UserrService{

	private UserrRepository userRepo;

	@Autowired
	public UserrServicelmpl(UserrRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public void save(Userr userr) {
		userRepo.save(userr);
	}
	
	
}
