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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payloads.APIResponses;
import com.example.demo.payloads.CategoryDto;
import com.example.demo.services.CategoryService;

@RestController
@RequestMapping(path = "/api/v1/categories")
public class CategoryController {


	@Autowired
	private CategoryService service;

	@PostMapping(path = "/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto category) {
		CategoryDto createdCategory = service.createCategory(category);
		return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto category, @PathVariable Integer categoryId) {
		CategoryDto updatedCategory = service.updateCategory(category, categoryId);
		return ResponseEntity.ok(updatedCategory);
	}

	@DeleteMapping(path = "/{categoryId}")
	public ResponseEntity<APIResponses> deleteCategory(@PathVariable Integer categoryId) {
		service.deleteCategoryById(categoryId);
		return new ResponseEntity<>(new APIResponses("Category deleted successfully", true, LocalDateTime.now()), HttpStatus.OK);
	}

	@GetMapping(path = "/")
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		return ResponseEntity.ok(service.getAllCategory());
	}

	@GetMapping(path = "/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId) {
		return ResponseEntity.ok(service.getCategoryById(categoryId));
	}

}
