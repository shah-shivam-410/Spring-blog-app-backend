package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "S_CATEGORY")
@Data
@NoArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROW_ID")
	private Integer categoryId;
	
	@Column(name = "X_CATEGORY_TITLE")
	private String categoryTitle;
	
	@Column(name = "X_CATEGORY_DESC")
	private String categoryDesc;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Post> posts = new ArrayList<>();
	
}
