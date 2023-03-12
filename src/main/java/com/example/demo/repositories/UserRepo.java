package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
	
	@Query(value = "select case when count(u) > 0 then true else false end from User u where id = :id")
	boolean isUserExistById(Integer id);
	
}
