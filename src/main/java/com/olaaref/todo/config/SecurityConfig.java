package com.olaaref.todo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.olaaref.todo.security.TodoUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	@Qualifier("persistentTokenRepository")
	private PersistentTokenRepository persistentTokenRepository;
	
	@Autowired
	private TodoUserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeRequests(configurer ->
						configurer
							.antMatchers("/user/**", "/role/**")
							.hasRole("ADMIN")
							
							.anyRequest()
							.authenticated())
				
				.formLogin(configurer -> 
						configurer
							.loginPage("/login")
							.usernameParameter("email")
							.loginProcessingUrl("/processLogin")
							.defaultSuccessUrl("/")
							.permitAll())
				
				.logout(configurer -> 
						configurer
							.permitAll())
				
				.rememberMe(configurer ->
						configurer
							.tokenValiditySeconds(3*24*60*60)
							.key("re-mem-ber-me")
							.tokenRepository(persistentTokenRepository)
							.userDetailsService(userDetailsService))
				
				.authenticationProvider(authenticationProvider())
				
				.exceptionHandling(configurer -> 
						configurer
							.accessDeniedPage("/access-denied"))
				
				.build();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> 
				web.ignoring()
					.antMatchers("/resources/js/**", "/resources/css/**", "/resources/image/**", "/resources/webfonts/**");
						
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		authenticationProvider.setUserDetailsService(userDetailsService);
		return authenticationProvider;
	}
	
}
