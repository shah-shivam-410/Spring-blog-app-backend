package com.example.demo.entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "S_POSTS")
@NoArgsConstructor
@Data
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROW_ID")
	private Integer id;
	
	@Column(name = "X_POST_TITLE", nullable = false)
	private String title;
	
	@Column(name = "X_POST_CONTENT", length = 500)
	private String content;
	
	@Column(name = "X_IMG_NAME")
	private String imageName;
	
	@Column(name = "X_CREATED_DATE", nullable = false)
	private Date addedDate; 
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();
	
}
