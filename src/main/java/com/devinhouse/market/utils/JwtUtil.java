package com.devinhouse.market.utils;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.devinhouse.market.model.transport.SubjectDTO;
import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private String secret;
	private Long expiration;
	private Gson gson;

	public JwtUtil(@Value("${market.jwt.secret}") String secret, 
			@Value("${market.jwt.expiration}") Long expiration,
			Gson gson) {
		this.secret = secret;
		this.expiration = expiration;
		this.gson = gson;
	}

	public String generateToken(String email, Set<String> authority) {
		SubjectDTO subject = new SubjectDTO(email, authority);
		return generateToken(gson.toJson(subject));
	}

	private String generateToken(String subject) {
		Date date = new Date(System.currentTimeMillis() + expiration);
		JwtBuilder builder = Jwts.builder().setExpiration(date).setSubject(subject).signWith(SignatureAlgorithm.HS512,
				secret);
		return builder.compact();
	}

	public boolean validarToken(String token) {
		Claims claims = getClaims(token);
		Date expiration = claims.getExpiration();
		long currentTimeMillis = System.currentTimeMillis();
		if (currentTimeMillis > expiration.getTime()) {
			return false;
		}

		String subject = claims.getSubject();
		SubjectDTO subjectDTO = gson.fromJson(subject, SubjectDTO.class);
		if (subjectDTO.isNotValid()) {
			return false;
		}
		
		return true;
	}

	private Claims getClaims(String token) {
		Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
		Claims body = parseClaimsJws.getBody();
		return body;
	}

}
