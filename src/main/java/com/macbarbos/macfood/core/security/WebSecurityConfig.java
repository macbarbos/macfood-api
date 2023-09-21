package com.macbarbos.macfood.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig /* extends WebSecurityConfigurerAdapter */ {

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception { http
	 * .httpBasic()
	 * 
	 * .and() .authorizeRequests() .antMatchers("/v1/cozinhas/**").permitAll()
	 * .anyRequest().authenticated()
	 * 
	 * .and() .sessionManagement()
	 * .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	 * 
	 * .and() .csrf().disable(); }
	 */
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.httpBasic()
		
		.and()
		.authorizeRequests()
			.antMatchers("/v1/cozinhas/**").permitAll()
			.anyRequest().authenticated()
		
		.and()
		.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			
		.and()
			.csrf().disable();
        return http.build();
    }
	
}