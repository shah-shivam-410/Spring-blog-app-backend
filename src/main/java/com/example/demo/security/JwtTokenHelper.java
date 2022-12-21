package com.example.demo.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@SuppressWarnings("deprecation")
public class JwtTokenHelper implements Serializable {

	private static final long serialVersionUID = 7008375124389347049L;
	public static final long TOKEN_VALIDITY = 1 * 60 * 60;
	private static final SecretKey KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); //or HS384 or HS512;

	public String generateJwtToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return Jwts
				.builder()
				.setClaims(claims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
				.signWith(KEY, SignatureAlgorithm.HS256)
				.compact();
	}

	public Boolean validateJwtToken(String token, UserDetails userDetails) {
		String username = getUsernameFromToken(token);
		Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
		Boolean isTokenExpired = claims.getExpiration().before(new Date());
		return (username.equals(userDetails.getUsername()) && !isTokenExpired);
	}

	public String getUsernameFromToken(String token) {
		final Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

}
