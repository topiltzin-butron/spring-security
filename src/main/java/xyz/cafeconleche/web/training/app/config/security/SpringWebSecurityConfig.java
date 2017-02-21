package xyz.cafeconleche.web.training.app.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import xyz.cafeconleche.web.training.dev.service.SecurityService;

//@Configuration
//@EnableWebSecurity
public class SpringWebSecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	private SecurityService securityService;
//	
//	@Autowired
//	private void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		System.out.println("--- configureGlobal ---");
//		authenticationManagerBuilder.userDetailsService(securityService).passwordEncoder(passwordEncoder());
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		System.out.println("--- configure ---");
//		http.authorizeRequests()
//			.antMatchers("/rest/secure/admin/**").access("hasRole('ROLE_ADMIN')")
//			.antMatchers("/rest/secure/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
//			.and().formLogin().loginPage("/login").failureUrl("/login?error")
//				.usernameParameter("principal").passwordParameter("credentials")
//			.and().logout().logoutSuccessUrl("/login?logout")
//			.and().exceptionHandling().accessDeniedPage("/403")
//			.and().csrf();
//	}
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		PasswordEncoder encoder = new BCryptPasswordEncoder();
//		return encoder;
//	}

}
