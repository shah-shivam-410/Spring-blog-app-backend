package com.example.demo.payloads;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.entities.Category;
import com.example.demo.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDto {

	@NotNull
	@Size(min = 4)
	private String title;
	
	private String content;

	private String imageName;

	@NotNull
	private Category category;

	@NotNull
	private User user;
}
