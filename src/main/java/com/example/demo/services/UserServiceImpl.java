package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.UserDto;
import com.example.demo.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDto createUser(UserDto u) {
		User user = this.dtoToUser(u);
		return this.userToDto(repo.save(user));
	}

	@Override
	public UserDto updateUser(UserDto u, Integer userId) {
		User user = repo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		user.setName(u.getName());
		user.setEmail(u.getEmail());
		user.setPassword(u.getPassword());
		user.setAbout(u.getAbout());
		return this.userToDto(repo.save(user));		
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = repo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> list = repo.findAll();
		List<UserDto> dtoList = list.stream().map(m -> this.userToDto(m)).collect(Collectors.toList());
		return dtoList;
	}

	@Override
	public void deleteUserById(Integer userId) {
		User user = repo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		repo.delete(user);
	}
	
	private User dtoToUser(UserDto u) {
		User user = mapper.map(u, User.class);
//		user.setId(u.getId());
//		user.setName(u.getName());
//		user.setEmail(u.getEmail());
//		user.setPassword(u.getPassword());
//		user.setAbout(u.getAbout());
		return user;
	}
	
	private UserDto userToDto(User u) {
		return mapper.map(u, UserDto.class);
	}

}
