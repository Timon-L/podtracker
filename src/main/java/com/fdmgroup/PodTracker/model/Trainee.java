
package com.fdmgroup.PodTracker.model;

import java.time.LocalDate;
import java.util.*;

import jakarta.persistence.*;

@Entity
public class Trainee extends User {
	private final Role role = User.Role.TRAINEE;
	
	LocalDate pondStartDate;
	String stream;
	
	@ManyToMany
	@JoinTable(name="TRAINEE_POD",
	joinColumns=
	@JoinColumn(name="FK_TRAINEE_ID"),
	inverseJoinColumns=
	@JoinColumn(name="FK_POD_ID")
			)
	private List<Pod> pods = new ArrayList<>();


	public Role getRole() {
		return role;
	}

	public LocalDate getPondStartDate() {
		return pondStartDate;
	}

	public void setPondStartDate(LocalDate pondStartDate) {
		this.pondStartDate = pondStartDate;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public Trainee(String username, String password, String name, LocalDate pondStartDate, String stream) {

		super();
		this.setUsername(username);
		this.setPassword(password);
		this.setName(name);
		this.setEmail(username + "@fdmgroup.com");
		this.stream = stream;
		this.pondStartDate = pondStartDate;
	}

	public Trainee() {
		super();
		// TODO Auto-generated constructor stub
	}
}
