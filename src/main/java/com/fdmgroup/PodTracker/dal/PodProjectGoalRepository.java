package com.fdmgroup.PodTracker.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.PodTracker.model.Goal;
import com.fdmgroup.PodTracker.model.PodProject;
import com.fdmgroup.PodTracker.model.PodProjectGoal;

public interface PodProjectGoalRepository extends JpaRepository<PodProjectGoal, Long> {
	@Query("select ppg from PodProjectGoal ppg where ppg.podProject = :podProject and ppg.goal = :goal")
	List<PodProjectGoal> findPodProjectGoalByProjectAndGoal(@Param("podProject") PodProject podProject, @Param("goal") Goal goal);
	List<PodProjectGoal> findByGoal(Goal goal);
	List<PodProjectGoal> findByPodProject(PodProject podProject);
}
