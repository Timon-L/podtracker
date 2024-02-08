package com.fdmgroup.PodTracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="CLIENT")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CLIENT_ID")
	private long id;
	@Column(name="NAME")
	private String name;
	@Column(name="STATE")
	@Enumerated(EnumType.STRING)
	private State state;
	@Column(name="CITY")
	private String city;
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Client(String name, State state, String city) {
		super();
		this.name = name;
		this.state = state;
		this.city = city;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", state=" + state + ", city=" + city + "]";
	}
	
	

}
