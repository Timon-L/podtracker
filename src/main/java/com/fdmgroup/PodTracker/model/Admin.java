package com.fdmgroup.PodTracker.model;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User{
	private final Role role = User.Role.ADMIN;
	

	public Role getRole() {
		return role;
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Admin(String username, String password, String name) {
		super();
		setUsername(username);
		setPassword(password);
		setName(name);
		setEmail(username + "@fdmgroup.com");
	}
}
