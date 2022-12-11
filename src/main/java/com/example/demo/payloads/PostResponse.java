package com.example.demo.payloads;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostResponse {

	private List<PostDto> content;
	private Integer pageNumber;
	private Integer pageSize;
	private Integer currentNoOfElements;
	private Long totalElements;
	private int totalPages;
	private boolean lastPage;
	
}
