package com.example.demo.controllers;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.example.demo.payloads.UserDto;
import com.example.demo.services.UserService;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

	private static Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService service;

	// POST: Create user
	@PostMapping(path = "/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
		UserDto createdUser = service.createUser(user);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	// PUT: Update user
	@PutMapping(path = "/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user, @PathVariable Integer userId) {
		UserDto updatedUser = service.updateUser(user, userId);
		return ResponseEntity.ok(updatedUser);
	}

	// DELETE: Delete user
	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<APIResponses> deleteUser(@PathVariable Integer userId) {
		service.deleteUserById(userId);
		return new ResponseEntity<>(new APIResponses("User deleted successfully", true, LocalDateTime.now()), HttpStatus.OK);
	}

	// GET: Get all user
	@GetMapping(path = "/")
	public ResponseEntity<List<UserDto>> getAllUser() {
		return ResponseEntity.ok(service.getAllUsers());
	}

	// GET: Get user for specific ID
	@GetMapping(path = "/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable Integer userId) {
		return ResponseEntity.ok(service.getUserById(userId));
	}

}
