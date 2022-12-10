package com.example.demo.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Post;
import com.example.demo.payloads.PostDto;
import com.example.demo.repositories.PostRepo;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo repo;
	
	@Autowired
	private ModelMapper mappper;
	
	@Override
	public PostDto createPost(PostDto p) {
		// TODO Auto-generated method stub
		Post post = mappper.map(p, Post.class);
		return null;
	}

	@Override
	public PostDto updatePost(PostDto p, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getAllPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getPostById(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getAllPostbyCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getAllPostbyUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
