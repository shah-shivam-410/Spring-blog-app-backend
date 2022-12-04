package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "S_USERS")
@NoArgsConstructor
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROW_ID")
	private Integer id;
	
	@Column(name = "X_USER_NAME")
	private String name;
	
	@Column(name = "X_USER_EMAIL")
	private String email;

	@Column(name = "X_USER_PASSWORD")
	private String password;
	
	@Column(name = "X_USER_ABOUT")
	private String about;
	

}
