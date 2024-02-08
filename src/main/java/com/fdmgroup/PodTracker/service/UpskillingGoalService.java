package com.fdmgroup.PodTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.PodTracker.dal.GoalRepository;
import com.fdmgroup.PodTracker.dal.UpskillingGoalRepository;
import com.fdmgroup.PodTracker.dal.UpskillingRepository;
import com.fdmgroup.PodTracker.exceptions.NotFoundException;
import com.fdmgroup.PodTracker.model.Goal;
import com.fdmgroup.PodTracker.model.Upskilling;
import com.fdmgroup.PodTracker.model.UpskillingGoal;

/**
 * Service for the bridging entity between Upskilling and Goal entities.
 * 
 * @author yiyanghou
 *
 */
@Service
public class UpskillingGoalService {
	private UpskillingGoalRepository ugr;
	private UpskillingRepository ur;
	private GoalRepository gr;

	@Autowired
	public UpskillingGoalService(UpskillingGoalRepository ugr, UpskillingRepository ur, GoalRepository gr) {
		super();
		this.ugr = ugr;
		this.ur = ur;
		this.gr = gr;
	}
	
	/**
	 * Persists the UpskillingGoal object to the database.
	 * 
	 * @param upskillingGoal
	 * @return the persisted UpskillingGoal object
	 */
	public UpskillingGoal save(UpskillingGoal upskillingGoal) {
		return ugr.save(upskillingGoal); 
	}
	
	/**
	 * Finds UpskillingGoal object by the compound key.
	 * 
	 * @param goalId
	 * @param skillId
	 * @return the found UpskillingGoal object
	 */
	public UpskillingGoal findUpskillingGoalBySkillAndGoal(long goalId, long skillId) {
		Upskilling skill = ur.findById(skillId)
				.orElseThrow(() -> new NotFoundException("Unable to find upskilling with id " + skillId));
		Goal goal = gr.findById(goalId)
				.orElseThrow(() -> new NotFoundException("Unable to find goal with id " + goalId));
		return ugr.findUpskillingGoalBySkillAndGoal(skill, goal).get(0);
	}
	
	/**
	 * Finds all the UpskillingGoal objects by the skill object.
	 * 
	 * @param skill
	 * @return all found UpskillingGoal objects
	 */
	public List<UpskillingGoal> findByUpskilling(Upskilling skill){
		return ugr.findByUpskilling(skill); 
	}
	
	/**
	 * Finds all the UpskillingGoal objects by the goal object.
	 * 
	 * @param goal
	 * @return all found UpskillingGoal objects
	 */
	public List<UpskillingGoal> findByGoal(Goal goal){
		return ugr.findByGoal(goal);
	}
	
	/**
	 * Finds all the UpskillingGoal objects in the database.
	 * 
	 * @return all the UpskillingGoal objects
	 */
	public List<UpskillingGoal> findAll(){
		return ugr.findAll();
	}
	
	/**
	 * Deletes the passed in UpskillingGoal object from the database.
	 * 
	 * @param upskillingGoal
	 */
	public void delete(UpskillingGoal upskillingGoal) {
		ugr.delete(upskillingGoal);
	}
	
	/**
	 * Updates the passed in UpskillingGoal object.
	 * 
	 * @param upskillingGoal
	 * @return the updated UpskillingGoal object.
	 */
	public UpskillingGoal update(UpskillingGoal upskillingGoal) {
		return ugr.save(upskillingGoal);
	}
	
}
