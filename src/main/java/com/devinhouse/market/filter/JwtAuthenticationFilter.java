package com.devinhouse.market.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.devinhouse.market.model.transport.CredentialsDTO;
import com.devinhouse.market.model.transport.CustomerDetails;
import com.devinhouse.market.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authManager;
	private JwtUtil jwtUtil;

	public JwtAuthenticationFilter(AuthenticationManager authManager, JwtUtil jwtUtil) {
		this.authManager = authManager;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			ServletInputStream inputStream = request.getInputStream();
			CredentialsDTO credentialsDTO = new ObjectMapper().readValue(inputStream, CredentialsDTO.class);
		
			return authManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							credentialsDTO.getEmail(), 
							credentialsDTO.getPassword(), 
							new ArrayList<>()));
		
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(e.getMessage());
		} catch (AccountExpiredException e) {
			throw new AccountExpiredException(e.getMessage());
		} catch (Exception e) {
			
			throw new AuthenticationServiceException(e.getMessage());
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		CustomerDetails customer = (CustomerDetails) authResult.getPrincipal();
		String generateToken = jwtUtil.generateToken(customer.getUsername(), new HashSet<String>());
		
		response.addHeader("Authorization",  "Bearer " + generateToken);
		
	}

}
