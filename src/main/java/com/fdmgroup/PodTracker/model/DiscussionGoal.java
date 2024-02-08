package com.fdmgroup.PodTracker.model;

import jakarta.persistence.*;

@Entity
@Table(name="DISCUSSION_GOAL")
public class DiscussionGoal{
	
	@Id
	@GeneratedValue
	@Column(name ="ID")
	private long id;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="DISCUSSION_ID")
	private OpportunityDiscussion discussion;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="GOAL_ID")
	private Goal goal;
	
	@Column(name="ALLOCATION_HOURS")
	private int allocationHours;

	public DiscussionGoal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DiscussionGoal(OpportunityDiscussion discussion, Goal goal, int allocationHours) {
		super();
		this.discussion = discussion;
		this.goal = goal;
		this.allocationHours = allocationHours;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public OpportunityDiscussion getDiscussion() {
		return discussion;
	}

	public void setDiscussion(OpportunityDiscussion discussion) {
		this.discussion = discussion;
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
