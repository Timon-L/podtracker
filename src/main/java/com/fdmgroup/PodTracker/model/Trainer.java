package com.fdmgroup.PodTracker.model;

import java.util.List;

import com.fdmgroup.PodTracker.model.User.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Trainer extends User{
	private final Role role = User.Role.TRAINER;
	
	public Role getRole() {
		return role;
	}
	
	public Trainer(String username, String password, String name) {
		super();
		this.setUsername(username);
		this.setPassword(password);
		this.setName(name);
		this.setEmail(username + "@fdmgroup.com");
	}

	public Trainer() {
		super();
		// TODO Auto-generated constructor stub
	}
}
