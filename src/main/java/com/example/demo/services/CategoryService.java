package com.example.demo.services;

import java.util.List;

import com.example.demo.payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto cat);
	CategoryDto updateCategory(CategoryDto cat, Integer id);
	CategoryDto getCategoryById(Integer id);
	List<CategoryDto> getAllCategory();
	void deleteCategoryById(Integer id);
	
}
