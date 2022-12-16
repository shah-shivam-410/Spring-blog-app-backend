package com.example.demo.controllers;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payloads.APIResponses;
import com.example.demo.payloads.CommentDto;
import com.example.demo.payloads.PostDto;
import com.example.demo.services.CommentService;

@RestController
@RequestMapping(path = "/api/v1/comments")
public class CommentController {

	@Autowired
	private CommentService service;
	
	@PostMapping(path = "/user/{userId}/post/{postId}")
	public ResponseEntity<CommentDto> createPost(@Valid @RequestBody CommentDto cmt, @PathVariable Integer userId, @PathVariable Integer postId) {
		CommentDto createdComment = service.createComment(cmt, userId, postId);
		return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/{commentId}")
	public ResponseEntity<APIResponses> deleteComment(@PathVariable Integer commentId) {
		service.deleteComment(commentId);
		return new ResponseEntity<>(new APIResponses("Comment deleted successfully", true, LocalDateTime.now()), HttpStatus.OK);
	}
	
}
