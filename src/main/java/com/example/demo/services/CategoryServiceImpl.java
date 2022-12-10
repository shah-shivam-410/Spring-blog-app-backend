package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Category;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.CategoryDto;
import com.example.demo.repositories.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	ModelMapper mapper;
	
	@Autowired
	CategoryRepo repo;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto cat) { 
		return categoryToDto(repo.save(dtoToCategory(cat)));
	}

	@Override
	public CategoryDto updateCategory(CategoryDto cat, Integer id) {
		Category category = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoy", "Category ID", id));
		category.setCategoryTitle(cat.getCategoryTitle());
		category.setCategoryDesc(cat.getCategoryDesc());
		return categoryToDto(repo.save(category));
	}

	@Override
	public void deleteCategoryById(Integer id) {
		Category category = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoy", "Category ID", id));
		repo.delete(category);
	}

	@Override
	public CategoryDto getCategoryById(Integer id) {
		return categoryToDto(repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoy", "Category ID", id)));
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<CategoryDto> list = repo.findAll().stream().map(m -> categoryToDto(m)).collect(Collectors.toList());
		return list;
	}

	private Category dtoToCategory(CategoryDto c) {
		return mapper.map(c, Category.class);
	}
	
	private CategoryDto categoryToDto(Category c) {
		return mapper.map(c, CategoryDto.class);
	}
}
