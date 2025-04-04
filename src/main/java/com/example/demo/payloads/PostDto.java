package com.example.demo.payloads;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDto {

	private Integer id;
	
	@NotNull
	@Size(min = 4)
	private String title;
	
	private String content;

	private String imageName = "default.png";

	private CategoryDto category;

	private UserDto user;
	
	private List<CommentDto> comments = new ArrayList<>();
}
