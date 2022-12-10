package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Post;
import com.example.demo.payloads.PostDto;

public interface PostService {

	PostDto createPost(PostDto p);
	PostDto updatePost(PostDto p, Integer postId);
	List<Post> getAllPost();
	Post getPostById(Integer postId);
	List<Post> getAllPostbyCategory(Integer categoryId);
	List<Post> getAllPostbyUser(Integer userId);
	List<Post> searchPosts(String keyword);
	
}
