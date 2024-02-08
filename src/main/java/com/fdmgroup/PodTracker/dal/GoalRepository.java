package com.fdmgroup.PodTracker.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.PodTracker.model.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {
	List<Goal> findByIsAbsenceFalse();
    List<Goal> findByIsAbsenceTrue();
}
