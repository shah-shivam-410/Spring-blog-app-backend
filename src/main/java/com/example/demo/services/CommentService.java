package com.example.demo.services;

import com.example.demo.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto cd, Integer userId, Integer postId);
	void deleteComment(Integer commentId);
	
}
