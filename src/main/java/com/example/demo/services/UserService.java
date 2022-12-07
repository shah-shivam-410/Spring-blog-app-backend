package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto u);

	UserDto updateUser(UserDto u, Integer userId);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUsers();

	void deleteUserById(Integer userId);
	
}
