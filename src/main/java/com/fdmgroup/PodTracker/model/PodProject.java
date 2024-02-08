package com.fdmgroup.PodTracker.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class PodProject {
	@GeneratedValue
	@Id
	private long projectId;
	@ManyToOne
	@JoinColumn
	private Pod pod;
	String name;
	String description;
	LocalDate startDate;
	LocalDate completionDate;
	@OneToMany(mappedBy = "podProject", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<PodProjectGoal> podProjectGoal = new HashSet<>();

	public long getProjectId() {
		return projectId;
	}

	public Pod getPod() {
		return pod;
	}

	public void setPod(Pod pod) {
		this.pod = pod;
	}

	public Set<PodProjectGoal> getPodProjectGoal() {
		return podProjectGoal;
	}

	public void setPodProjectGoal(Set<PodProjectGoal> podProjectGoal) {
		this.podProjectGoal = podProjectGoal;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(LocalDate completionDate) {
		this.completionDate = completionDate;
	}

	public PodProject(Pod pod, String name, String description, LocalDate startDate, LocalDate completionDate) {
		super();
		this.pod = pod;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.completionDate = completionDate;
	}

	public PodProject() {
		super();
	}
	
	
}
