package com.ejemplo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.ejemplo.repository.UserRepository;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("DetailsServiceCustom")
	private UserDetailsService DetailsServ;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		/*auth.inMemoryAuthentication().withUser("tom").password("abc123").roles("USER");
		auth.inMemoryAuthentication().withUser("Ajay").password("ajay").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("dba").password("dba123").roles("DBA");*/
		auth.userDetailsService(DetailsServ);
		
		System.out.println("Entra por aqui 5000000000000000");
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
		//.antMatchers("/users/**").access("hasRole('ROLE_USER')")
		//.antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
		.antMatchers("/gestion/**").access("hasRole('ROLE_USER') or hasRole('ROLE_DBA') or hasRole('ROLE_ADMIN')")
		.and().formLogin()		
		.loginPage("/login")
		.usernameParameter("usuario")
		.passwordParameter("password")
		.and().csrf()
		.and().exceptionHandling().accessDeniedPage("/Access_Denied");
	}
	
}
