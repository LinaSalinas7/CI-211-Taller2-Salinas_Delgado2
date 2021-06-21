package taller2.thymeleaf.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taller2.thymeleaf.Exception.LogicalException;
import taller2.thymeleaf.model.FetTaskinstance;
import taller2.thymeleaf.repsitory.FetTaskInstanceRepository;

@Service
public class FetTaskInstanceServicelmpl implements  FetTaskInstanceService{
	
	private FetTaskInstanceRepository fetTaskInstanceRepository;
	private ActionnService actionnService;
	
	@Autowired
	public FetTaskInstanceServicelmpl(FetTaskInstanceRepository fetTaskInstanceRepository, ActionnService actionnService) {
		this.fetTaskInstanceRepository=fetTaskInstanceRepository;
		this.actionnService = actionnService;
	}

	@Override
	public Optional<FetTaskinstance> searchFetTaskinstance(long id) throws LogicalException {
		
		Optional<FetTaskinstance> optional = fetTaskInstanceRepository.findById(id);
		return optional;
		
	}

	@Override
	public FetTaskinstance editarInstanciaTarea(FetTaskinstance fetTaskinstance) throws LogicalException {
		if(fetTaskinstance==null) {
			throw new LogicalException("La instancia no puede ser nula");
		}
		
		actionnService.getActionnById(fetTaskinstance.getTaskinsId());
		
		//FetTaskinstance  instanceProv = buscarInstanciaTarea(fetTaskinstance.getTaskinsId());
		
		/*if(instanceProv.getTaskinsExecutiondate()== null) {
			throw new LogicalException("La fecha no puede ser null");
			
		}
		
		else if(instanceProv.getTaskinsTargettype()==null) {
			throw new LogicalException("Este campo no puede ser null");
			
		}
		else if(instanceProv.getTaskinsTargetvalue()==null) {
			throw new LogicalException("Este campo no puede ser null");
		}
		
		else {
			instanceProv.setTaskinsExecutiondate(fetTaskinstance.getTaskinsExecutiondate());
			instanceProv.setTaskinsTargettype(fetTaskinstance.getTaskinsTargettype());
			instanceProv.setTaskinsTargetvalue(fetTaskinstance.getTaskinsTargetvalue());
			instanceProv.setTask(fetTaskinstance.getTask());
			fetTaskInstanceRepository.save(instanceProv);
			return instanceProv;
		}*/
		return fetTaskinstance;
		
	}

	@Override
	public FetTaskinstance crearInstanciaTarea(FetTaskinstance fetTaskinstance) throws LogicalException {
		if(fetTaskinstance== null) {
			throw new LogicalException("La instancia no puede ser vacia");
		}
		/*else if(fetTaskinstance.getTaskinsExecutiondate()=>LocalDate.now()) {
			
		}*/
		actionnService.getActionnById(fetTaskinstance.getTaskinsId());
		fetTaskInstanceRepository.save(fetTaskinstance);
		return fetTaskinstance;
	}

	@Override
	public Iterable<FetTaskinstance> findAll() {
		return fetTaskInstanceRepository.findAll();
	}

	@Override
	public FetTaskinstance saveFetTaskInstance(FetTaskinstance fetTaskInstance) {
		return fetTaskInstanceRepository.save(fetTaskInstance);
	}

	@Override
	public void delete(FetTaskinstance fetTAskInstance) {
		fetTaskInstanceRepository.delete(fetTAskInstance);
		
	}

	@Override
	public Optional<FetTaskinstance> findById(long id) {
		return fetTaskInstanceRepository.findById(id);
	}
	
	

}
