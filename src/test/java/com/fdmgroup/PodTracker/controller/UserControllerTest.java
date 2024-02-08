package com.fdmgroup.PodTracker.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fdmgroup.PodTracker.model.User;
import com.fdmgroup.PodTracker.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	@Mock
	private UserService userServiceMock;
	@Mock
	private PasswordEncoder encoder;
	@Mock
	private Authentication authMock;

	@InjectMocks
	private UserController userController;
	private User user;
	private long userId;

	@BeforeEach
	void setUp() throws Exception {
		userId = 2L;
		this.user = new User();
		this.user.setUserId(userId);
		this.user.setName("Harry Potter");
		this.user.setUsername("harry.potter@fgroup.com");
		this.user.setPassword(encoder.encode("password"));
	}

	@Test
	void test_FindById_ShouldReturnUser_WhenIdFound() {
		// Arrange
		when(userServiceMock.findById(userId)).thenReturn(user);
		// Act
		User expected = userController.findById(userId);
		// Assert
		assertEquals(expected.getUsername(), "harry.potter@fgroup.com");
		assertEquals(expected.getUserId(), userId);
		assertEquals(expected.getName(), "Harry Potter");
	}

	@Test
	void test_FindAll_ShouldReturnAllUsers() {
		// Arrange
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		when(userServiceMock.findAll()).thenReturn(users);
		// Act
		List<User> result = userController.findAll();
		// Assert
		assertNotNull(result);
		assertEquals(2, result.size());
	}

	@Test
	void test_FindUserByUsername_ShouldReturnUser_WhenUsernameIsValid() {
		// Arrange
		when(userServiceMock.findByUsername("harry.potter@fgroup.com")).thenReturn(user);
		// Act
		User expected = userController.findUserByUsername("harry.potter@fgroup.com");
		// Assert
		assertEquals(expected.getUsername(), "harry.potter@fgroup.com");
		assertEquals(expected.getUserId(), userId);
		assertEquals(expected.getName(), "Harry Potter");
	}

	@Test
	void test_UpdateUser_ShouldUpdateUserFields_WhenUserFound() {
		// Arrange
		when(userServiceMock.updateUser(eq(userId), any(User.class))).thenReturn(user);
		User updatedUser = userController.updateUser(userId, new User());
		// Act
		// Assert
		assertEquals(user, updatedUser);
	}
	
	@Test
	void test_CreateUser_ShouldReturnCreatedUser() {
		// Arrange
		String username = "harry.potter@fgroup.com";
		when(authMock.getName()).thenReturn(username);
		when(userServiceMock.findByUsername(username)).thenReturn(user);
		when(userServiceMock.createUser(user, user)).thenReturn(user);
		// Act
		User actualUser = userController.createUser(user, authMock);
		// Assert
		assertEquals(user, actualUser);
		verify(userServiceMock).createUser(actualUser, actualUser);
	}
	
	@Test
	void test_DeleteUser_ShouldDeleteOneUser_WhenIdProvided() {
		// Arrange
		// Act
		userController.deleteUser(userId);
		// Assert
		verify(userServiceMock).deleteById(userId);
	}
	
	@Test
	void test_FindCurrent_ShouldReturnOneCurrentUser() {
		// Arrange
		String username = "harry.potter@fgroup.com";
		when(authMock.getName()).thenReturn(username);
		when(userServiceMock.findByUsername(username)).thenReturn(user);
		// Act
		User actualUser = userController.findCurrent(authMock);
		// Assert
		assertEquals(user, actualUser);
		verify(authMock).getName();
		verify(userServiceMock).findByUsername(username);
	}

}