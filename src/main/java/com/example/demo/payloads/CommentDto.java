package com.example.demo.payloads;

import javax.persistence.ManyToOne;

import com.example.demo.entities.Post;
import com.example.demo.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {

	private Integer id;

	private String content;

}
