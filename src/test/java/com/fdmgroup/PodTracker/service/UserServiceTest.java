package com.fdmgroup.PodTracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fdmgroup.PodTracker.dal.UserRepository;
import com.fdmgroup.PodTracker.exceptions.*;
import com.fdmgroup.PodTracker.model.User;
import com.fdmgroup.PodTracker.model.User.Role;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository userRepoMock;
	@Mock
	private PasswordEncoder encoder;
	private User user;
	@InjectMocks
	private UserService userService;
	private long userId;
	private long invalidUserId;
	private String username;
	private String name;
	private String exceptionMessage;

	@BeforeEach
	void setUp() throws Exception {
		userId = 2L;
		invalidUserId = 10L;
		username = "harry.potter@fgroup.com";
		name = "Harry Potter";
		exceptionMessage = "Unable to find user with ID " + invalidUserId;
		this.user = new User();
		this.user.setUserId(userId);
		this.user.setName(name);
		this.user.setUsername(username);
		this.user.setPassword(encoder.encode("password"));
	}

	@Test
	void test_FindById_ShouldReturnUser_WhenIdFound() {
		// Arrange
		when(userRepoMock.findById(userId)).thenReturn(Optional.of(user));
		// Act
		User actualUser = userService.findById(userId);
		// Assert
		assertEquals(actualUser, user);
		verify(userRepoMock).findById(userId);
	}

	@Test
	void test_FindById_ShouldThrowCustomException_WhenNoIdFound() {
		// Arrange
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class,
				() -> userService.findById(invalidUserId));
		// Assert
		assertEquals(exceptionMessage, actualExceptionThrown.getMessage());
		verify(userRepoMock).findById(invalidUserId);
	}

	@Test
	void test_FindAll_ShouldReturnAllUsers() {
		// Arrange
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		when(userRepoMock.findAll()).thenReturn(users);
		// Act
		List<User> actualUsers = userService.findAll();
		// Assert
		assertNotNull(actualUsers);
		assertEquals(users, actualUsers);
		verify(userRepoMock).findAll();
	}

	@Test
	void test_FindByUsername_ShouldReturnUser_WhenUsernameFound() {
		// Arrange
		when(userRepoMock.findByUsername(username)).thenReturn(Optional.of(user));
		// Act
		User actualUser = userService.findByUsername(username);
		// Assert
		assertEquals(user, actualUser);
		verify(userRepoMock).findByUsername(username);
	}

	@Test
	void test_FindByUsername_ShouldThrowCustomException_WhenNoIdFound() {
		// Arrange
		String invalidUsername = "not valid";
		String expectedExceptionMessage = "Unable to find user with username " + invalidUsername;
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class,
				() -> userService.findByUsername(invalidUsername));
		// Assert
		assertEquals(expectedExceptionMessage, actualExceptionThrown.getMessage());
		verify(userRepoMock).findByUsername(invalidUsername);
	}

	@Test 
	void test_UpdateUser_ShouldReturnOneUpdatedUser_WhenUserIdFoundAndUserValidated() {
		// Arrange
		User expectedUpdatedUser = new User();
		expectedUpdatedUser.setUserId(userId);
		String newUsername = "101Hp@h.com";
		String newPassword = "newPassword1";
		expectedUpdatedUser.setUsername(newUsername);
		expectedUpdatedUser.setPassword(newPassword);

		when(userRepoMock.existsById(userId)).thenReturn(true);
		when(userRepoMock.save(expectedUpdatedUser)).thenReturn(expectedUpdatedUser);
		// Act
		User actualUpdatedUser = userService.updateUser(userId, expectedUpdatedUser);
		// Assert
		assertEquals(expectedUpdatedUser, actualUpdatedUser);
		verify(userRepoMock).save(expectedUpdatedUser);
	}

	@Test 
	void test_UpdatedUser_ShouldThrowNotFoundException_WhenUserIdNotFound() {
		// Arrange
		String message = "User ID does not exist.";
		User expectedUpdatedUser = new User();
		String newUsername = "101Hp@h.com";
		String newPassword = "newPassword1";
		expectedUpdatedUser.setUsername(newUsername);
		expectedUpdatedUser.setPassword(newPassword);
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class,
				() -> userService.updateUser(invalidUserId, expectedUpdatedUser));
		// Assert
		assertEquals(message, actualExceptionThrown.getMessage());
		verify(userRepoMock).existsById(invalidUserId);
	} 
	
	@Test
	void test_RegisterUser_ShouldReturnNewUser_WhenUserIsValidated() {
		// Arrange
		String encodedPasswordMock = "has8sdfh(AasJ*%sd";
;		when(userRepoMock.save(user)).thenReturn(user);
		when(encoder.encode("password")).thenReturn(encodedPasswordMock);
		user.setPassword(encoder.encode("password"));
		// Act
		User actualRegisteredUser = userService.registerUser(user);
		// Assert
		assertEquals(user, actualRegisteredUser);
		verify(userRepoMock).save(user);
	}
	
	@Test
	void test_CreateUser_ShouldReturnCreatedUser_WhenUserRoleIsAdmin() {
		// Arrange
		user.setRole(Role.ADMIN);
		User newUser = new User();
		newUser.setUsername("user4500");
		String encodedPasswordMock = "has8sdfh(AasJ*%sd";
		when(userRepoMock.save(newUser)).thenReturn(newUser);
		when(encoder.encode("password")).thenReturn(encodedPasswordMock);
		newUser.setPassword(encoder.encode("password"));
		// Act
		User actualCreatedUser = userService.createUser(newUser, user);
		// Assert
		assertEquals(newUser, actualCreatedUser);
		verify(userRepoMock).save(newUser);
	}
	
	@Test
	void test_CreateUser_ShouldThrowForbiddenException_WhenUserIsNotAdmin() {
		// Arrange
		user.setRole(Role.TRAINEE);
		String expectedExceptionMessage = "Only Admin is allowed to create new users";
		User newUser = new User();
		newUser.setUsername("user4500");
		String encodedPasswordMock = "has8sdfh(AasJ*%sd";
		when(encoder.encode("password")).thenReturn(encodedPasswordMock);
		newUser.setPassword(encoder.encode("password"));
		// Act
		Exception actualExceptionThrown = assertThrows(ForbiddenException.class,
				() -> userService.createUser(newUser, user));
		// Assert
		assertEquals(expectedExceptionMessage, actualExceptionThrown.getMessage());
	}
	
	@Test
	void test_DeleteById_ShouldDeleteOneUser_WhenIdFound() {
		// Arrange
		// Act
		userService.deleteById(userId);
		// Assert
		verify(userRepoMock).deleteById(userId);
	}
	
	@Test
	void test_Validation_ShouldThrowInvalidInputException_WhenUsernameIsEmpty() {
		// Arrange
		String expectedErrorMsg = "Username cannot be empty";
		List<User> users = new ArrayList<>();
		User invalidUser = new User();
		String invalidUsername = "";
		invalidUser.setUsername(invalidUsername);
		users.add(user);
		users.add(invalidUser);
		when(userRepoMock.findAll()).thenReturn(users);
		// Act
		Exception actualExceptionThrown = assertThrows(InvalidInputException.class, 
				() -> userService.validation(invalidUser));
		// Assert
		assertEquals(expectedErrorMsg, actualExceptionThrown.getMessage());
		verify(userRepoMock).findAll();
	}
	
	@Test 
	void test_Validation_ShouldThrowInvalidInputException_WhenUsernameAlreadyExists() {
		// Arrange
		String expectedErrorMsg = "Username already taken";
		List<User> users = new ArrayList<>();
		User invalidUser = new User();
		String invalidUsername = username;
		invalidUser.setUsername(invalidUsername);
		users.add(user);
		users.add(invalidUser);
		when(userRepoMock.findAll()).thenReturn(users);
		// Act
		Exception actualExceptionThrown = assertThrows(InvalidInputException.class, 
				() -> userService.validation(invalidUser));
		// Assert
		assertEquals(expectedErrorMsg, actualExceptionThrown.getMessage());
		verify(userRepoMock).findAll();
	}
	
	@Test 
	void test_Validation_ShouldThrowInvalidInputException_WhenPasswordIsEmpty() {
		// Arrange
		String expectedErrorMsg = "password cannot be empty";
		List<User> users = new ArrayList<>();
		User invalidUser = new User();
		invalidUser.setUsername("user101");
		invalidUser.setPassword("");
		users.add(user);
		users.add(invalidUser);
		when(userRepoMock.findAll()).thenReturn(users);
		// Act
		Exception actualExceptionThrown = assertThrows(InvalidInputException.class, 
				() -> userService.validation(invalidUser));
		// Assert
		assertEquals(expectedErrorMsg, actualExceptionThrown.getMessage());
		verify(userRepoMock).findAll();
	}

}
