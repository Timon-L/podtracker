package com.fdmgroup.PodTracker.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.PodTracker.model.DiscussionGoal;
import com.fdmgroup.PodTracker.model.Goal;
import com.fdmgroup.PodTracker.model.OpportunityDiscussion;

public interface DiscussionGoalRepository extends JpaRepository<DiscussionGoal, Long> {
	@Query("select dg from DiscussionGoal dg where dg.discussion = :discussion and dg.goal = :goal")
	List<DiscussionGoal> findDiscussionGoalByDiscussionAndGoal(@Param("discussion") OpportunityDiscussion discussion, @Param("goal") Goal goal);
	List<DiscussionGoal> findByDiscussion(OpportunityDiscussion discussion);
	List<DiscussionGoal> findByGoal(Goal goal);
}
