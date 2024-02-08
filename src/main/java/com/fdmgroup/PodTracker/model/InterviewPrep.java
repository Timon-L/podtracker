package com.fdmgroup.PodTracker.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="INTERVIEWPREP")
public class InterviewPrep {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="INTERVIEWPREP_ID")
	private long id;
	@ManyToOne
	@JoinColumn(name="FK_TRAINEE_ID")
	private Trainee trainee;
	@Column(name="PREP_TYPE")
	private InterviewPrepType prepType;
	@Column(name="DATE", columnDefinition = "DATE")
	private LocalDate date;
	@ManyToOne
	@JoinColumn(name="FK_INTERVIEW_ID")
	private Interview interview;
	@ManyToOne
	@JoinColumn(name="FK_GOAL_ID")
	private Goal goal;
	@Column(name="ALLOCATION_HOURS")
	private int allocationHours;
	public InterviewPrep() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InterviewPrep(Trainee trainee, InterviewPrepType prepType, LocalDate date, Goal goal) {
		super();
		this.trainee = trainee;
		this.prepType = prepType;
		this.date = date;
		this.goal = goal;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Trainee getTrainee() {
		return trainee;
	}
	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}
	public InterviewPrepType getPrepType() {
		return prepType;
	}
	public void setPrepType(InterviewPrepType prepType) {
		this.prepType = prepType;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Interview getInterview() {
		return interview;
	}
	public void setInterview(Interview interview) {
		this.interview = interview;
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
	@Override
	public String toString() {
		return "InterviewPrep [id=" + id + ", trainee=" + trainee + ", prepType=" + prepType + ", date=" + date
				+ ", interview=" + interview + ", goal=" + goal + ", allocationHours=" + allocationHours + "]";
	}
	
}
