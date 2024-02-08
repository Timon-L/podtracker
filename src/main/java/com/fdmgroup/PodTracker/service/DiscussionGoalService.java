package com.fdmgroup.PodTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.PodTracker.dal.*;
import com.fdmgroup.PodTracker.exceptions.NotFoundException;
import com.fdmgroup.PodTracker.model.*;

/**
 * Service for the discussion and goal many-to-many bridging entity.
 * @author yiyanghou
 *
 */
@Service
public class DiscussionGoalService {
	private OpportunityDiscussionRepository odr;
	private DiscussionGoalRepository dgr;
	private GoalRepository gr;
	
	@Autowired
	public DiscussionGoalService(OpportunityDiscussionRepository odr, DiscussionGoalRepository dgr, GoalRepository gr) {
		super();
		this.odr = odr;
		this.dgr = dgr;
		this.gr = gr;
	}
	
	/**
	 * Persistes a new object to the database.
	 * @param discussionGoal
	 * @return
	 */
	public DiscussionGoal save(DiscussionGoal discussionGoal) {
		return dgr.save(discussionGoal);
	} 
	
	/**
	 * Finds object by the compound key.
	 * @param goalId
	 * @param discussionId
	 * @return
	 */
	public DiscussionGoal findDiscussionGoalByDiscussionAndGoal(long goalId, long discussionId) {
		OpportunityDiscussion discussion = odr.findById(discussionId)
				.orElseThrow(() -> new NotFoundException("Unable to find discussion with id " + discussionId));
		Goal goal = gr.findById(goalId)
				.orElseThrow(() -> new NotFoundException("Unable to find goal with id " + goalId));
		return dgr.findDiscussionGoalByDiscussionAndGoal(discussion, goal).get(0);
	}
	
	/**
	 * Finds all the objects in the database.
	 * @return
	 */
	public List<DiscussionGoal> findAll(){
		return dgr.findAll();
	}
	
	/**
	 * Finds all objects by a discussion object.
	 * @param discussion
	 * @return
	 */
	public List<DiscussionGoal> findByDisccussion (OpportunityDiscussion discussion){
		return dgr.findByDiscussion(discussion);
	}
	
	/**
	 * Finds all objects by a goal object.
	 * @param goal
	 * @return
	 */
	public List<DiscussionGoal> findByGoal (Goal goal){
		return dgr.findByGoal(goal);
	}
	
	
	/**
	 * Deletes an object.
	 * @param discussionGoal
	 */
	public void delete(DiscussionGoal discussionGoal) {
		dgr.delete(discussionGoal);
	}
	
	/**
	 * Update an object.
	 * @param discussionGoal
	 * @return
	 */
	public DiscussionGoal update(DiscussionGoal discussionGoal) {
		return dgr.save(discussionGoal);
	}
}
