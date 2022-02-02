package com.devinhouse.market.config;

import java.util.Set;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.devinhouse.market.filter.JwtAuthenticationFilter;
import com.devinhouse.market.service.CustomerService;
import com.devinhouse.market.utils.JwtUtil;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] PUBLIC_MATCH_POST = { "/login/**" };

	private Environment environment;
	private PasswordEncoder encoder;
	private CustomerService customerService;
	private JwtUtil jwtUtil;

	public SecurityConfig(CustomerService customerService, PasswordEncoder encoder, Environment environment,
			JwtUtil jwtUtil) {
		this.customerService = customerService;
		this.encoder = encoder;
		this.environment = environment;
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customerService).passwordEncoder(encoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String[] profiles = this.environment.getActiveProfiles();
		if(profiles == null || !Set.of(profiles).contains("prod")) {
			http.cors().disable();
			http.csrf().disable();			
		}
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, PUBLIC_MATCH_POST)
		.permitAll()
		.anyRequest().authenticated();
		
		http.addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtUtil));
		
	}

}
