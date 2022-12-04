package com.example.demo.payloads;

import javax.persistence.Column;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

	private Integer id;
	private String name;
	private String email;
	private String password;
	private String about;
	
}
