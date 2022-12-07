package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.repositories.UserRepo;

@SpringBootTest
class SpringBlogAppApplicationTests {

	@Autowired
	private UserRepo repo;
	
	@Test
	public void repoWiringTest() {
		System.out.println(repo);
		System.out.println(repo.getClass());
		assertNotNull(repo);
	}

}
