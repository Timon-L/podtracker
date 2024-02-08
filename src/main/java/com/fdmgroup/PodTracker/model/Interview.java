package com.fdmgroup.PodTracker.model;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Interview")
public class Interview {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="INTERVIEW_ID")
	private long id;
	@ManyToOne
	@JoinColumn(name="FK_TRAINEE_ID")
	private Trainee trainee;
	@ManyToOne
	@JoinColumn(name="FK_ACCOUNT_MANAGER_ID")
	private AccountManager ac;
	@ManyToOne
	@JoinColumn(name="FK_CLIENT_ID")
	private Client client;
	@Column(name="OUTCOME")
	private InterviewOutcome outcome;
	@Column(name="DATE", columnDefinition = "DATE")
	private LocalDate date;
	@Column(name="FEEDBACK")
	private String feedback;
	@ManyToOne
	@JoinColumn(name="FK_GOAL_ID")
	private Goal goal;
	public Interview() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Interview(Trainee trainee, AccountManager ac, Client client) {
		super();
		this.trainee = trainee;
		this.ac = ac;
		this.client = client;
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
	public AccountManager getAc() {
		return ac;
	}
	public void setAc(AccountManager ac) {
		this.ac = ac;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public InterviewOutcome getOutcome() {
		return outcome;
	}
	public void setOutcome(InterviewOutcome outcome) {
		this.outcome = outcome;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public Goal getGoal() {
		return goal;
	}
	public void setGoal(Goal goal) {
		this.goal = goal;
	}
	@Override
	public String toString() {
		return "Interview [id=" + id + ", trainee=" + trainee + ", ac=" + ac + ", client=" + client + ", outcome="
				+ outcome + ", date=" + date + ", feedback=" + feedback + ", goal=" + goal + "]";
	}
	
	
	
}
