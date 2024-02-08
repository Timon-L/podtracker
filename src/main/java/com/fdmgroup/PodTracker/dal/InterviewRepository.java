package com.fdmgroup.PodTracker.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.PodTracker.model.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Long> {

}
