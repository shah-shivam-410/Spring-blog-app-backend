package com.example.demo.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class JwtAuthRequest {

	private String email;
	private String password;
	
}
