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
@Table(name = "S_ROLE")
@NoArgsConstructor
@Data
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROW_ID")
	private Integer id;
	
	@Column(name = "X_ROLE_NAME")
	private String name;
	
}
