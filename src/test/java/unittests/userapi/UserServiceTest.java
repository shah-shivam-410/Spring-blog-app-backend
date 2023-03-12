package unittests.userapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.SpringBlogAppApplication;
import com.example.demo.payloads.UserDto;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.UserService;

@SpringBootTest(classes = SpringBlogAppApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepo userRepo;

	UserDto baseUser;
	UserDto userToBeDeleted;
	
	@BeforeAll
	void setUp() throws InterruptedException {
		UserDto userDto = new UserDto();
		userDto.setName("abcdefg");
		userDto.setPassword("qwerty");
		userDto.setAbout("111");
		userDto.setEmail("wqeqw@wqeqwe.com");
		baseUser = userService.createUser(userDto);
		
		userDto.setName("tobedelete");
		userDto.setPassword("qwerty");
		userDto.setAbout("111");
		userDto.setEmail("wqeqw@wqeqwe.com");
		userToBeDeleted = userService.createUser(userDto);
	}
	
	@Test
	void testCreateUser() {
		UserDto userDto = new UserDto();
		userDto.setName("123wew");
		userDto.setPassword("qwerty");
		userDto.setAbout("111");
		userDto.setEmail("wqeqw@wqeqwe.com");
		
		UserDto createdUser = userService.createUser(userDto);
		System.out.println("ID: " + createdUser.getId());
		assertEquals(true, userRepo.isUserExistById(createdUser.getId()));		
	}

	@Test
	void testUpdateUser() {
		String newEmail = "qwewe@11.com";
		baseUser.setEmail(newEmail);
		userService.updateUser(baseUser, baseUser.getId());
		assertEquals(
				newEmail, 
				userService.getUserById(baseUser.getId()).getEmail()
		);
		
		
	}

	@Test
	void testGetUserById() {
		UserDto userById = userService.getUserById(baseUser.getId());
		assertEquals(baseUser, userById);
	}

	@Test
	void testGetAllUsers() {
		List<UserDto> allUsers = userService.getAllUsers();
		System.out.println("Total users: " + allUsers.size());
		assertTrue(allUsers.size() > 0);
	}

	@Test
	void testDeleteUserById() {
		userService.deleteUserById(userToBeDeleted.getId());
		assertFalse(userRepo.isUserExistById(userToBeDeleted.getId()));
	}

}
