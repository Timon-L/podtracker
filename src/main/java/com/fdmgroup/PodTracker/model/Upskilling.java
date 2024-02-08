package com.fdmgroup.PodTracker.model;

import java.time.LocalDate;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Upskilling {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="UPSKILLING_ID")
	private long id;
	@ManyToOne
	@JoinColumn(name="FK_TRAINEE_ID")
	private Trainee trainee;
	@Column(name="START_DATE", columnDefinition = "DATE")
	private LocalDate startDate;
	@Column(name="COMPLETION_ETA", columnDefinition = "DATE")
	private LocalDate completionETA;
	@Column(name="FINISH_DATE", columnDefinition = "DATE")
	private LocalDate finishDate;
	@Column(name="TOPIC")
	private String topic;
	@Column(name="CONTENT")
	private String content;
	@Column(name="OBJECTIVE")
	private String objective;
	@Column(name="STATUS")
	@Enumerated(EnumType.STRING)
	private TaskStatus status;
	@OneToMany(mappedBy = "upskilling", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<UpskillingGoal> upskillingGoal = new HashSet<>();
	public Upskilling() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Upskilling(Trainee trainee, LocalDate startDate, LocalDate completionETA, String topic, String content,
			String objective, TaskStatus status) {
		super();
		this.trainee = trainee;
		this.startDate = startDate;
		this.completionETA = completionETA;
		this.topic = topic;
		this.content = content;
		this.objective = objective;
		this.status = status;
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
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getCompletionETA() {
		return completionETA;
	}
	public void setCompletionETA(LocalDate completionETA) {
		this.completionETA = completionETA;
	}
	public LocalDate getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(LocalDate finishDate) {
		this.finishDate = finishDate;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
	public TaskStatus getStatus() {
		return status;
	}
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	
	public Set<UpskillingGoal> getUpskillingGoal() {
		return upskillingGoal;
	}
	public void setUpskillingGoal(Set<UpskillingGoal> upskillingGoal) {
		this.upskillingGoal = upskillingGoal;
	}
	@Override
	public String toString() {
		return "Upskilling [id=" + id + ", trainee=" + trainee + ", startDate=" + startDate + ", completionETA="
				+ completionETA + ", finishDate=" + finishDate + ", topic=" + topic + ", content=" + content
				+ ", objective=" + objective + ", status=" + status + "]";
	}
	
	
	
}
