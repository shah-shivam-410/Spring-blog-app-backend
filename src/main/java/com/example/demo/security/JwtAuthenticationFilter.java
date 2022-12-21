package com.example.demo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService service;
	
	@Autowired
	private JwtTokenHelper tokenHelper;
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = null;
		String userName = null;
		
		String reqTOken = request.getHeader("Authorization");
//		System.out.println(reqTOken);
		
		if(reqTOken != null && reqTOken.startsWith("Bearer")) {
			token = reqTOken.substring(7);
			userName = tokenHelper.getUsernameFromToken(token);
//			System.out.println(userName);
		}
		
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = service.loadUserByUsername(userName);
			
			if(tokenHelper.validateJwtToken(token, userDetails)) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);;
				
			} else {
				System.out.println("Invalid token");
			}
			
		}
		
		filterChain.doFilter(request, response);
		
	}

}
