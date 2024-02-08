package com.fdmgroup.PodTracker.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.PodTracker.model.Goal;
import com.fdmgroup.PodTracker.model.Upskilling;
import com.fdmgroup.PodTracker.model.UpskillingGoal;

public interface UpskillingGoalRepository extends JpaRepository<UpskillingGoal, Long> {
	@Query("select ug from UpskillingGoal ug where ug.upskilling = :upskilling and ug.goal = :goal")
	List<UpskillingGoal> findUpskillingGoalBySkillAndGoal(@Param("upskilling") Upskilling skill, @Param("goal") Goal goal);
	List<UpskillingGoal> findByUpskilling(Upskilling skill);
	List<UpskillingGoal> findByGoal(Goal goal);
}
