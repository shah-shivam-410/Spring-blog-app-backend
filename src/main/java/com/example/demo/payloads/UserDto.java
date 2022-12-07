package com.example.demo.payloads;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

	private Integer id;
	
	@NotEmpty
	@Size(min = 4, message = "Username must be minimium of 4 character.")
	private String name;
	
	@Email(message = "Email should be in abc@xyz.com format")
	private String email;
	
	@NotEmpty
	@Size(min = 3, max = 10)
//	@Pattern(regexp = "")
	private String password;
	private String about;
	
}
