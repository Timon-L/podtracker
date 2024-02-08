package com.fdmgroup.PodTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.fdmgroup.PodTracker.model.User;
import com.fdmgroup.PodTracker.service.UserService;

/**
 * Controller for the User entity.
 * 
 * @author yiyanghou
 *
 */
@RestController
@RequestMapping("users")
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	/**
	 * GET request for finding the User object with the specified ID.
	 * 
	 * @param Id
	 * @return the found User object
	 */
	@GetMapping("{userId}")
	public User findById(@PathVariable long userId) {
		return this.userService.findById(userId);
	}

	/**
	 * GET request for finding the User object with the specified username.
	 * 
	 * @param username
	 * @return the found User object
	 */
	@GetMapping("/username")
	public User findUserByUsername(@RequestParam String username) {
		return this.userService.findByUsername(username);
	}

	/**
	 * GET request for finding all User objects in the database.
	 * 
	 * @return List of all User objects
	 */
	@GetMapping
	public List<User> findAll() {
		return this.userService.findAll();
	}

	/**
	 * PUT request for updating the deserialised User object that is passed in.
	 * 
	 * @param id
	 * @param user
	 * @return the updated User object
	 */
	@PutMapping("/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User user) {
		return this.userService.updateUser(id, user);
	}

	/**
	 * POST request for persisting the deserialised Interview object that is passed
	 * in. Only available to Admin users.
	 * 
	 * @param user
	 * @return the persisted User object
	 */
	@PostMapping("/create")
	public User createUser(@RequestBody User user, Authentication auth) {
		return userService.createUser(user, findCurrent(auth));
	}

	/**
	 * DELETE request for deleting the User object from the database with the passed in ID.
	 * 
	 * @param id
	 */
	@DeleteMapping("{userId}")
	public void deleteUser(@PathVariable long userId) {
		this.userService.deleteById(userId);
	}

	/**
	 * GET request for finding the User object that is currently logged in.
	 * 
	 * @param auth
	 * @return the current User object
	 */
	@GetMapping("/current")
	public User findCurrent(Authentication auth) {
		String username = auth.getName();
		User user = userService.findByUsername(username);
		return user;
	}
}
