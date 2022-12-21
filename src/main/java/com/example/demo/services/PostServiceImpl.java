package com.example.demo.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Category;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.PostDto;
import com.example.demo.payloads.PostResponse;
import com.example.demo.repositories.CategoryRepo;
import com.example.demo.repositories.PostRepo;
import com.example.demo.repositories.UserRepo;


@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo repo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public PostDto createPost(PostDto p, Integer userId, Integer categoryId) {	
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		Category cat = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
		Post post = mapper.map(p, Post.class);
		post.setAddedDate(new Date());
		post.setCategory(cat);
		post.setUser(user);
		repo.save(post);		
		return mapper.map(post, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto p, Integer postId) {
		Post post = repo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		post.setTitle(p.getTitle());
		post.setContent(p.getContent());
		post.setImageName(p.getImageName());
		Post updatedPost = repo.save(post);
		return mapper.map(updatedPost, PostDto.class);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		PostResponse resp = new PostResponse();
		PageRequest p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = repo.findAll(p);
		List<Post> posts = pagePost.getContent();
		List<PostDto> allDtos = posts.stream().map(m -> mapper.map(m, PostDto.class)).collect(Collectors.toList());
		resp.setContent(allDtos);
		resp.setPageNumber(pagePost.getNumber());
		resp.setPageSize(pagePost.getSize());
		resp.setTotalElements(pagePost.getTotalElements());
		resp.setTotalPages(pagePost.getTotalPages());
		resp.setLastPage(pagePost.isLast());
		resp.setCurrentNoOfElements(pagePost.getNumberOfElements());
		return resp;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = repo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		return mapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getAllPostbyCategory(Integer categoryId) {
		Category cat = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
		List<Post> postList = repo.findByCategory(cat);
		return postList.stream().map(m -> mapper.map(m, PostDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> getAllPostbyUser(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		List<Post> postList = repo.findByUser(user);
		return postList.stream().map(m -> mapper.map(m, PostDto.class)).collect(Collectors.toList());
	}

	@Override
	public void deletePost(Integer postId) {
		repo.deleteById(postId);
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = repo.findByTitleContaining(keyword);
		return posts.stream().map(m -> mapper.map(m, PostDto.class)).collect(Collectors.toList());
	}
	
}
