package com.example.demo.payloads;

import javax.validation.constraints.NotEmpty;

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
