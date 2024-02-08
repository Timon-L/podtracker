package com.fdmgroup.PodTracker.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Pod {
	@GeneratedValue
	@Id
	@Column
	private long podId;
	private String podName;
	private int capacity;
	@OneToOne
	@JoinColumn
	private PodProject project;
	@ManyToOne
	@JoinColumn
	private Trainer primaryTrainerId;
	@ManyToOne
	@JoinColumn
	private Trainer secondaryTrainerId;
	@OneToMany
	@JoinColumn(name="FK_Trainee_id")
	private List<Trainee> trainees = new ArrayList<>();
	
	public String getPodName() {
		return podName;
	}

	public void setPodName(String podName) {
		this.podName = podName;
	}

	public long getPodId() {
		return podId;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Trainer getPrimaryTrainerId() {
		return primaryTrainerId;
	}

	public void setPrimaryTrainerId(Trainer primaryTrainerId) {
		this.primaryTrainerId = primaryTrainerId;
	}

	public Trainer getSecondaryTrainerId() {
		return secondaryTrainerId;
	}

	public void setSecondaryTrainerId(Trainer secondaryTrainerId) {
		this.secondaryTrainerId = secondaryTrainerId;
	}

	public List<Trainee> getUsers() {
		return trainees;
	}

	public void setUsers(List<Trainee> trainees) {
		this.trainees = trainees;
	}

	public PodProject getProject() {
		return project;
	}

	public void setProject(PodProject project) {
		this.project = project;
	}

	public List<Trainee> getTrainees() {
		return trainees;
	}

	public void setTrainees(List<Trainee> trainees) {
		this.trainees = trainees;
	}

	public void setPodId(long podId) {
		this.podId = podId;
	}

	
	public Pod(String podName, int capacity, PodProject project, Trainer primaryTrainerId, Trainer secondaryTrainerId,
			List<Trainee> trainees) {
		super();
		this.podName = podName;
		this.capacity = capacity;
		this.project = project;
		this.primaryTrainerId = primaryTrainerId;
		this.secondaryTrainerId = secondaryTrainerId;
		this.trainees = trainees;
	}

	public Pod() {
		super();
	}

}