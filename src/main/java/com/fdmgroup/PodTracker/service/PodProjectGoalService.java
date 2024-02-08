package com.fdmgroup.PodTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.PodTracker.dal.*;
import com.fdmgroup.PodTracker.exceptions.NotFoundException;
import com.fdmgroup.PodTracker.model.*;

/**
 * Service for the bridging entity between PodProject and Goal.
 * 
 * @author yiyanghou
 *
 */
@Service
public class PodProjectGoalService {
	private PodProjectGoalRepository ppgr;
	private PodProjectRepository ppr;
	private GoalRepository gr;

	@Autowired
	public PodProjectGoalService(PodProjectGoalRepository ppgr, PodProjectRepository ppr, GoalRepository gr) {
		super();
		this.ppgr = ppgr;
		this.ppr = ppr;
		this.gr = gr;
	}
	
	/**
	 * Persists the PodProjectGoal object to the database.
	 * 
	 * @param podProjectGoal
	 * @return the saved PodProjectGoal object
	 */
	public PodProjectGoal save(PodProjectGoal podProjectGoal) {
		return ppgr.save(podProjectGoal);
	}
	
	/**
	 * Finds the PodProjectGoal object by the compound key.
	 * 
	 * @param goalId
	 * @param projectId
	 * @return the found PodProjectGoal object
	 */
	public PodProjectGoal findPodProjectGoalByProjectAndGoal(long goalId, long projectId) {
		PodProject project = ppr.findById(projectId)
				.orElseThrow(() -> new NotFoundException("Unable to find project with id " + projectId));
		Goal goal = gr.findById(goalId)
				.orElseThrow(() -> new NotFoundException("Unable to find goal with id " + goalId));
		return ppgr.findPodProjectGoalByProjectAndGoal(project, goal).get(0);
	}
	/**
	 * Finds all the PodProjectGoal object in the database.
	 * 
	 * @return
	 */
	public List<PodProjectGoal> findAll(){
		return ppgr.findAll();
	}
	
	/**
	 * Finds all the PodProjectGoal objects with the passed in Goal.
	 * 
	 * @param goal
	 * @return The list of found PodProjectGoal objects
	 */
	public List<PodProjectGoal> findByGoal(Goal goal) {
		return ppgr.findByGoal(goal);
	}
	
	/**
	 * Finds all the PodProjectGoal objects with the passed in PodProject.
	 * 
	 * @param project
	 * @return
	 */
	public List<PodProjectGoal> findByPodProject(PodProject project) {
		return ppgr.findByPodProject(project);
	}
	
	/**
	 * Deletes the PodProjectGoal object.
	 * 
	 * @param podProjectGoal
	 */
	public void delete(PodProjectGoal podProjectGoal) {
		ppgr.delete(podProjectGoal);
	}
	
	/**
	 * Updates the PodProjectGoal object.
	 * 
	 * @param podProjectGoal
	 * @return
	 */
	public PodProjectGoal update(PodProjectGoal podProjectGoal) {
		return ppgr.save(podProjectGoal);
	}
	
}
