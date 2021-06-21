package taller2.thymeleaf.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and().authorizeRequests()
		.antMatchers("/taskType/**").access("hasRole('administrador')")
		.antMatchers("/fetTaskInstance/**").access("hasRole('operador')")
		.antMatchers("/task/**").access("hasRole('operador')")
		.antMatchers("/timeConfig/**").access("hasRole('operador')")
		.anyRequest().authenticated()
		.and()
			.formLogin()
			.permitAll()
		.and()
			.logout()
			.invalidateHttpSession(true).clearAuthentication(true)	
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
			.permitAll()
		.and()
		.exceptionHandling()
			.accessDeniedHandler(accessDeniedHandler);
	}

//	@Autowired
//	private MyCustomUserDetailsService userDetailsService;

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authenticationProvider());
//	}
//	("/login*").permitAll().antMatchers("/users/*").hasRole("admin")
//	.antMatchers("/apps/**").hasAnyRole("doctor", "admin", "patient").anyRequest().authenticated().and().formLogin()
//	.loginPage("/login").permitAll().and().logout().invalidateHttpSession(true).clearAuthentication(true)
//	.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
//	.permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);

//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userDetailsService);
//		authProvider.setPasswordEncoder(encoder());
//		return authProvider;
//	}
//
//	@Bean
//	public PasswordEncoder encoder() {
//		return new BCryptPasswordEncoder(11);
//	}

	
}