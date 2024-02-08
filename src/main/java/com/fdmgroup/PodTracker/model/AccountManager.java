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
@Table(name="ACCOUNT_MANAGER")
public class AccountManager {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ACCOUNT_MANAGER_ID")
	private long id;
	@Column(name="NAME")
	private String name;
	@Column(name="LOCATION")
	@Enumerated(EnumType.STRING)
	private FDMLocation location;
	public AccountManager() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AccountManager(String name, FDMLocation location) {
		super();
		this.name = name;
		this.location = location;
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
	public FDMLocation getLocation() {
		return location;
	}
	public void setLocation(FDMLocation location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "AccountManager [id=" + id + ", name=" + name + ", location=" + location + "]";
	}
	
	
}
