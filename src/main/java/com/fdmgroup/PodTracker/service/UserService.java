package com.fdmgroup.PodTracker.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.PodTracker.dal.UserRepository;
import com.fdmgroup.PodTracker.exceptions.ForbiddenException;
import com.fdmgroup.PodTracker.exceptions.NotFoundException;
import com.fdmgroup.PodTracker.model.User;
import com.fdmgroup.PodTracker.model.User.Role;
import com.fdmgroup.PodTracker.exceptions.InvalidInputException;

/**
 * Service for the User entity.
 * 
 * @author yiyanghou
 *
 */
@Service
public class UserService {
	private UserRepository ur;
	private PasswordEncoder encoder;

	@Autowired
	public UserService(UserRepository ur, PasswordEncoder encoder) 
	{
		super();
		this.ur = ur;
		this.encoder = encoder;
	}
	
	/**
	 * Finds the User object by ID.
	 * 
	 * @param userId
	 * @return the found User object.
	 */
	public User findById(long userId)
	{
		return this.ur.findById(userId)
				.orElseThrow(()-> new NotFoundException("Unable to find user with ID " + userId));
	}
	
	/**
	 * Finds all the User objects in the database.
	 * 
	 * @return All the User objects
	 */
	public List<User> findAll()
	{
		return this.ur.findAll();
	}
	
	/**
	 * Finds the User object by Username.
	 * 
	 * @param username
	 * @return the found User object.
	 */
	public User findByUsername(String username)
	{
		return this.ur.findByUsername(username)
				.orElseThrow(() -> new NotFoundException("Unable to find user with username " + username));
	}
	
	/**
	 * Update the passed in User object.
	 * 
	 * @param id
	 * @param user
	 * @return the updated User object.
	 */
	public User updateUser(Long id, User user) 
	{
		validation(user);
		if (!ur.existsById(id)) {
			throw new NotFoundException("User ID does not exist.");
		}
		return this.ur.save(user);
	}
	
	/**
	 * Register a new User to the database, including encodes the password.
	 * 
	 * @param user
	 * @return the newly registered User object
	 */
	public User registerUser(User user)
	{
		validation(user);
		user.setPassword(encoder.encode(user.getPassword()));
		return ur.save(user);
	}
	
	/**
	 * Only available to Admin User. The Admin User can create a new User.
	 * 
	 * @param userToCreate
	 * @param currentUser
	 * @return the newly created User
	 */
	public User createUser(User userToCreate, User currentUser)
	{
		if(currentUser.getRole().equals(Role.ADMIN)) {
			return registerUser(userToCreate);
		} else {
			throw new ForbiddenException("Only Admin is allowed to create new users");
		}
	}
	/**
	 * Validates the deserialized User object. InvalidInputException will be thrown if the user is invalid. 
	 * 
	 * @param user
	 */
	public void validation(User user) {
		Boolean isValid=true;
		String username = user.getUsername();
		long id = user.getUserId();
		String password = user.getPassword();
		List<User> users = findAll();
		Boolean usernameExists = false;
		String errorMsg = "";
		for(User eachUser : users) {
			if(eachUser.getUsername().equals(username) && eachUser.getUserId()!=id) {
				usernameExists=true;
				break;
			}
		}
		if(username.equals(""))  {
			isValid = false;
			errorMsg = "Username cannot be empty";
		}else if(usernameExists) {
			isValid = false;
			errorMsg = "Username already taken";
		} else if(password.equals("")) {
			isValid = false;
			errorMsg = "password cannot be empty";
		}
			
		if(!isValid) {
			throw new InvalidInputException(errorMsg);
		}
	}
	
	/**
	 * Deletes the User object by ID.
	 * 
	 * @param id
	 */
	public void deleteById(long id) {
		ur.deleteById(id);
	}

}
