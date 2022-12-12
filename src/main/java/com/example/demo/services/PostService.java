package com.example.demo.services;

import java.util.List;

import com.example.demo.payloads.PostDto;
import com.example.demo.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto p, Integer userId, Integer categoryId);
	PostDto updatePost(PostDto p, Integer postId);
	void deletePost(Integer postId);
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	PostDto getPostById(Integer postId);
	List<PostDto> getAllPostbyCategory(Integer categoryId);
	List<PostDto> getAllPostbyUser(Integer userId);
	List<PostDto> searchPosts(String keyword);
	
}
