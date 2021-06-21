package taller2.thymeleaf.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import taller2.thymeleaf.model.Userr;
import taller2.thymeleaf.repsitory.UserrRepository;


@Service
public class MyCustomUserDetailsService implements UserDetailsService {
	
	private UserrRepository userrRepository;
	
	@Autowired
	public MyCustomUserDetailsService(UserrRepository userrRepository) {
		this.userrRepository=userrRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Userr> userr = userrRepository.findByUserName(username);

		if (userr != null) {
			User.UserBuilder builder = User.withUsername(username).password(userr.get().getUserPassword())
					.roles(userr.get().getType().toString());
			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}