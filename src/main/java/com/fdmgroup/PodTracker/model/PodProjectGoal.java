package com.fdmgroup.PodTracker.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PodProjectGoal")
public class PodProjectGoal{
	@Id
	@GeneratedValue
	@Column(name ="ID")
	private long id;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "podProjectId")
	private PodProject podProject;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "goalId")
	private Goal goal;
	
	private int allocationHours;

	private String skills;
	private String tasksCompleted;
	
	public PodProjectGoal() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public PodProjectGoal(PodProject podProject, Goal goal, int allocationHours) {
		super();
		this.podProject = podProject;
		this.goal = goal;
		this.allocationHours = allocationHours;
	}

	public String getSkills() {
		return skills;
	}



	public void setSkills(String skills) {
		this.skills = skills;
	}



	public String getTasksCompleted() {
		return tasksCompleted;
	}



	public void setTasksCompleted(String tasksCompleted) {
		this.tasksCompleted = tasksCompleted;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PodProject getPodProject() {
		return podProject;
	}

	public void setPodProject(PodProject podProject) {
		this.podProject = podProject;
	}

	public Goal getGoal() {
		return goal;
	}

	public int getAllocationHours() {
		return allocationHours;
	}

	public void setAllocationHours(int allocationHours) {
		this.allocationHours = allocationHours;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}


	
}
