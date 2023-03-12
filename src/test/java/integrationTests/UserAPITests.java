package integrationTests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.payloads.UserDto;

import io.restassured.http.Method;
import io.restassured.response.Response;

@Disabled
class UserAPITests {
	
	private static Logger log = LoggerFactory.getLogger(UserAPITests.class);
	
	int initialNoOfUsers = 0;

	@Test
	void getAllUserTest() {
		baseURI = "http://localhost:8081/api/v1/users";
		Response resp = given().request(Method.GET, "/");
		assertEquals(resp.statusCode(), 200);
		UserDto[] userDtos = resp.as(UserDto[].class);
		initialNoOfUsers = userDtos.length;
		log.info("Intital no. of users: {}", initialNoOfUsers);
		assertTrue(initialNoOfUsers > 0);
	}
	
	@Test
	void createUserTest() {
		baseURI = "http://localhost:8081/api/v1/users";
		Response resp = given().request(Method.GET, "/");
		assertEquals(resp.statusCode(), 200);
		UserDto[] userDtos = resp.as(UserDto[].class);
		initialNoOfUsers = userDtos.length;
		log.info("Intital no. of users: {}", initialNoOfUsers);
		assertTrue(initialNoOfUsers > 0);
	}
	
}
