package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entities.User;
import com.example.demo.payloads.UserDto;
import com.example.demo.repositories.UserRepo;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDto createUser(UserDto u) {
		User user = this.dtoToUser(u);
		return this.userToDto(this.repo.save(user));
	}

	@Override
	public UserDto updateUser(UserDto u, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserById(Integer userId) {
		// TODO Auto-generated method stub

	}
	
	private User dtoToUser(UserDto u) {
		User user = new User();
		user.setId(u.getId());
		user.setName(u.getName());
		user.setEmail(u.getEmail());
		user.setPassword(u.getPassword());
		user.setAbout(u.getAbout());
		return user;
	}
	
	private UserDto userToDto(User u) {
		UserDto user = new UserDto();
		user.setId(u.getId());
		user.setName(u.getName());
		user.setEmail(u.getEmail());
		user.setPassword(u.getPassword());
		user.setAbout(u.getAbout());
		return user;
	}

}
