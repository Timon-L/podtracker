package com.fdmgroup.PodTracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="OPPORTUNITY")
public class Opportunity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="OPPORTUNITY_ID")
	private long id;
	@ManyToOne
	@JoinColumn(name="FK_TRAINEE_ID")
	private Trainee trainee;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="POSITION")
	private String position;
	@Column(name="STATUS")
	@Enumerated(EnumType.STRING)
	private OpportunityStatus status;
	@ManyToOne
	@JoinColumn(name="FK_ACCOUNT_MANAGER_ID")
	private AccountManager ac;
	@ManyToOne
	@JoinColumn(name="FK_CLIENT_ID")
	private Client client;
	@ManyToOne
	@JoinColumn(name="FK_GOAL_ID")
	private Goal goal;
	public Opportunity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Opportunity(Trainee trainee, String description, OpportunityStatus status, AccountManager ac, Client client,
			Goal goal, String position) {
		super();
		this.trainee = trainee;
		this.description = description;
		this.status = status;
		this.ac = ac;
		this.client = client;
		this.goal = goal;
		this.position = position;
	}
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Goal getGoal() {
		return goal;
	}
	public void setGoal(Goal goal) {
		this.goal = goal;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public Trainee getTrainee() {
		return trainee;
	}
	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public OpportunityStatus getStatus() {
		return status;
	}
	public void setStatus(OpportunityStatus status) {
		this.status = status;
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
	@Override
	public String toString() {
		return "Opportunity [id=" + id + ", trainee=" + trainee + ", description=" + description + ", status=" + status
				+ ", ac=" + ac + ", client=" + client + "]";
	}
	
	
}
