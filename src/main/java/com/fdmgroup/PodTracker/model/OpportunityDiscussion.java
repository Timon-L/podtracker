package com.fdmgroup.PodTracker.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
public class OpportunityDiscussion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="OPPORTUNITY_DISSCUSSION_ID")
	private long id;
	@ManyToOne
	@JoinColumn(name="OPPORTUNITY_ID")
	private Opportunity opportunity;
	@OneToMany(mappedBy = "discussion", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<DiscussionGoal> discussionGoal = new HashSet<>();
	public OpportunityDiscussion(Opportunity opportunity) {
		super();
		this.opportunity = opportunity;
	}
	public OpportunityDiscussion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Opportunity getOpportunity() {
		return opportunity;
	}
	public void setOpportunity(Opportunity opportunity) {
		this.opportunity = opportunity;
	}
	public Set<DiscussionGoal> getDiscussionGoal() {
		return discussionGoal;
	}
	public void setDiscussionGoal(Set<DiscussionGoal> discussionGoal) {
		this.discussionGoal = discussionGoal;
	}
	@Override
	public String toString() {
		return "OpportunityDiscussion [id=" + id + ", opportunity=" + opportunity + "]";
	}
	
	
}
