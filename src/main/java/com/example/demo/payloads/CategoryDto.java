package com.example.demo.payloads;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.example.demo.entities.Post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {

	
	private Integer categoryId;
	
	@NotEmpty
	private String categoryTitle;
	
	private String categoryDesc;

//	private List<PostDto> posts = new ArrayList<>();
	
}
