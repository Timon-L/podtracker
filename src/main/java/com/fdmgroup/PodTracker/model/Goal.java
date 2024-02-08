package com.fdmgroup.PodTracker.model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Goal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	
	private LocalDate date;
	
	@OneToMany(mappedBy = "goal", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<UpskillingGoal> upskillingGoal = new HashSet<>();
	
	@OneToMany(mappedBy = "goal", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<DiscussionGoal> discussionGoal = new HashSet<>();
	
	@OneToMany(mappedBy = "goal", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<PodProjectGoal> podProjectGoal = new HashSet<>();
	
	@Column
	private String comment;
	@Column
	private String feedback1;
	@Column
	private String feedback2;
	@Column
	private String adminFeedback;
	@Column
	private boolean isAbsence;
	
	@OneToMany(mappedBy = "goal", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
	private List<Interview> interviews = new ArrayList<>();
	
	@OneToMany(mappedBy = "goal", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
	private List<Opportunity> opportunities = new ArrayList<>();
	
	public Goal() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Goal(LocalDate date, boolean isAbsence) {
		super();
		this.date = date;
		this.isAbsence = isAbsence;
	}

	public String getAdminFeedback() {
		return adminFeedback;
	}

	public void setAdminFeedback(String adminFeedback) {
		this.adminFeedback = adminFeedback;
	}

	public boolean getIsAbsence() {
		return isAbsence;
	}


	public void setIsAbsence(boolean isAbsence) {
		this.isAbsence = isAbsence;
	}


	public List<Interview> getInterviews() {
		return interviews;
	}


	public void setInterviews(List<Interview> interviews) {
		this.interviews = interviews;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<UpskillingGoal> getUpskillingGoal() {
		return upskillingGoal;
	}

	public void setUpskillingGoal(Set<UpskillingGoal> upskillingGoal) {
		this.upskillingGoal = upskillingGoal;
	}

	public Set<DiscussionGoal> getDiscussionGoal() {
		return discussionGoal;
	}

	public void setDiscussionGoal(Set<DiscussionGoal> discussionGoal) {
		this.discussionGoal = discussionGoal;
	}

	public Set<PodProjectGoal> getPodProjectGoal() {
		return podProjectGoal;
	}

	public void setPodProjectGoal(Set<PodProjectGoal> podProjectGoal) {
		this.podProjectGoal = podProjectGoal;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFeedback1() {
		return feedback1;
	}

	public void setFeedback1(String feedback1) {
		this.feedback1 = feedback1;
	}

	public String getFeedback2() {
		return feedback2;
	}

	public void setFeedback2(String feedback2) {
		this.feedback2 = feedback2;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
}
