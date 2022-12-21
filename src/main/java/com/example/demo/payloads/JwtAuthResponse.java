package com.example.demo.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class JwtAuthResponse {

	private String token;
	
}
