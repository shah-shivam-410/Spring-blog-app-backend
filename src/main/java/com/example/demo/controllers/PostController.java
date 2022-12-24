package com.example.demo.controllers;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.AppConstants;
import com.example.demo.payloads.APIResponses;
import com.example.demo.payloads.PostDto;
import com.example.demo.payloads.PostResponse;
import com.example.demo.services.PostService;

@RestController
@RequestMapping(path = "/api/v1/posts")
public class PostController {
	
	@Autowired
	private PostService service;

	@PostMapping(path = "/user/{userId}/category/{catId}")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto post, @PathVariable Integer userId, @PathVariable Integer catId) {
		PostDto createdPost = service.createPost(post, userId, catId);
		return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/user/{userId}")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
		List<PostDto> userPosts = service.getAllPostbyUser(userId);
		return new ResponseEntity<>(userPosts, HttpStatus.OK);
	}
	
	@GetMapping(path = "/category/{catId}")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer catId) {
		List<PostDto> catPosts = service.getAllPostbyCategory(catId);
		return new ResponseEntity<>(catPosts, HttpStatus.OK);
	}
	
	@GetMapping(path = "/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
		PostDto post = service.getPostById(postId);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}
	
	@GetMapping(path = "/", produces = "application/xml")
	public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber, 
													 @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
													 @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
													 @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
		PostResponse posts = service.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}
	
	@PutMapping(path = "/{postId}")
	public ResponseEntity<PostDto> getAllPosts(@Valid @RequestBody PostDto p, @PathVariable Integer postId) {
		PostDto post = service.updatePost(p, postId);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{postId}")
	public ResponseEntity<APIResponses> deletePost(@PathVariable Integer postId) {
		service.deletePost(postId);
		return new ResponseEntity<>(new APIResponses("Post deleted successfully", true, LocalDateTime.now()), HttpStatus.OK);
	}
	
	@GetMapping(path = "/search/{keyword}")
	public ResponseEntity<List<PostDto>> serchPostByTitle(@PathVariable String keyword) {
		List<PostDto> posts = service.searchPosts(keyword);
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}
	
}
