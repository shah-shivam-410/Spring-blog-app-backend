package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payloads.PostDto;
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
	
}
