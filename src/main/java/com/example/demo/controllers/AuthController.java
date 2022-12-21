package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payloads.JwtAuthRequest;
import com.example.demo.payloads.JwtAuthResponse;
import com.example.demo.security.JwtTokenHelper;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping(path = "/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest req) {
		authenticate(req.getEmail(), req.getPassword());
		String token = jwtTokenHelper.generateJwtToken(userDetailsService.loadUserByUsername(req.getEmail()));
		JwtAuthResponse resp = new JwtAuthResponse();
		resp.setToken(token);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	private void authenticate(String email, String password) {	
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, password);
		authenticationManager.authenticate(usernamePasswordAuthenticationToken);
	}
	
}
