package xyz.cafeconleche.web.training.app.config.security;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import xyz.cafeconleche.web.training.dev.service.SecurityService;

@EnableWebSecurity
@Configuration
public class SpringMultipleWebSecurityConfig {
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		System.out.println("--- configureGlobal ---");
		authenticationManagerBuilder.userDetailsService(securityService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Configuration
	@Order(1)
	public static class RestWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		
		@Autowired // restAuthenticationEntryPoint
		private AuthenticationEntryPoint authenticationEntryPoint;
		
		@Bean
		public AuthenticationSuccessHandler successHandler() {
			return new SavedRequestAwareAuthenticationSuccessHandler();
		}
		
		@Bean
		public AuthenticationFailureHandler failureHandler() {
			return new SimpleUrlAuthenticationFailureHandler();
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			System.out.println("--- configure rest security ---");
			
//			http
//				//.antMatcher("/rest/**")
//				.csrf().disable()
//				.exceptionHandling()
//				.authenticationEntryPoint(authenticationEntryPoint)
//			.and()
//				.authorizeRequests()
//				.antMatchers("/rest/secure/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
//				.antMatchers("/rest/secure/admin/**").access("hasRole('ROLE_ADMIN')")
//			.and()
//				.formLogin()
//				.successHandler(successHandler())
//				.failureHandler(failureHandler())
//				.and()
//				.logout();
			
//			http.antMatcher("/rest/**")
//				.authorizeRequests()
//				.antMatchers("/rest/secure/admin/**").access("hasRole('ROLE_ADMIN')")
//				.antMatchers("/rest/secure/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
//				.and().formLogin().loginPage("/login").failureUrl("/login?error")
//					.usernameParameter("principal").passwordParameter("credentials")
//				.and().logout().logoutSuccessUrl("/login?logout")
//				.and().exceptionHandling().accessDeniedPage("/403")
//				.and().csrf();
			
			http
//				.antMatcher("/rest/**")
				.authorizeRequests().and()
				.csrf().requireCsrfProtectionMatcher(requireCsrfProtectionMatcher())
				.and()
//				.csrf().disable()
				.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint)
				.and()
				.authorizeRequests()
				.antMatchers("/rest/secure/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
				.antMatchers("/rest/secure/admin/**").access("hasRole('ROLE_ADMIN')")
				.and()
				.formLogin()
				.successHandler(successHandler())
				.failureHandler(failureHandler())
				.and()
				.logout();
			
		}
		
	}
	
	@Configuration
	@Order(2)
	public static class WebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			System.out.println("--- configure web security ---");
			http
				.authorizeRequests()
				.antMatchers("/secure/admin/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/secure/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
			.and()
				.formLogin().loginPage("/login").failureUrl("/login?error")
					.usernameParameter("principal").passwordParameter("credentials")
			.and()
				.logout().logoutSuccessUrl("/login?logout")
			.and()
				.exceptionHandling().accessDeniedPage("/403")
			.and()
//				.csrf();
				.csrf().requireCsrfProtectionMatcher(requireCsrfProtectionMatcher());
//				.csrf().disable();
//				.csrf().ignoringAntMatchers("/rest/**");
			
		}
		
	}
	
	
	public static RequestMatcher requireCsrfProtectionMatcher() {
	
		Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
	    RegexRequestMatcher unprotectedMatcher = new RegexRequestMatcher("/rest/*", null);
		
		RequestMatcher requestMatcher = new RequestMatcher() {
			
			@Override
			public boolean matches(HttpServletRequest request) {
				
				System.out.println("request: " + request);
				System.out.println("headers: " + request.getHeaderNames());
				String XMyApp = request.getHeader("X-MyApp");
				System.out.println("myapp: " + XMyApp);
				
				if (allowedMethods.matcher(request.getMethod()).matches()) {
					System.out.println("--- false ---");
		            return false;
		        }
				
				System.out.println("--- maybe ---: " + !unprotectedMatcher.matches(request));
		        return !unprotectedMatcher.matches(request);
			}
		};
		
		return requestMatcher;
	}
	

}
