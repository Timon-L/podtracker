package com.fdmgroup.PodTracker.model;

import jakarta.persistence.*;

@Entity
@Table(name="UPSKILLING_GOAL")
public class UpskillingGoal{
	@Id
	@GeneratedValue
	@Column(name ="ID")
	private long id;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="UPSKILLING_ID")
	private Upskilling upskilling;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="GOAL_ID")
	private Goal goal;
	
	@Column(name="ALLOCATION_HOURS")
	private int allocationHours;

	public UpskillingGoal(Upskilling upskilling, Goal goal, int allocationHours) {
		super();
		this.upskilling = upskilling;
		this.goal = goal;
		this.allocationHours = allocationHours;
	}

	public UpskillingGoal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Upskilling getUpskilling() {
		return upskilling;
	}

	public void setUpskilling(Upskilling upskilling) {
		this.upskilling = upskilling;
	}

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	public int getAllocationHours() {
		return allocationHours;
	}

	public void setAllocationHours(int allocationHours) {
		this.allocationHours = allocationHours;
	}

	
}
