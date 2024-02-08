package com.fdmgroup.PodTracker.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fdmgroup.PodTracker.model.PodProjectGoal;
import com.fdmgroup.PodTracker.service.PodProjectGoalService;


/**
 * The controller for the bridging entity between PodProject and Goal entities.
 * 
 * @author yiyanghou
 *
 */
@RestController
@RequestMapping("podProject_goal")
public class PodProjectGoalController {
	private PodProjectGoalService ppgs;

	@Autowired
	public PodProjectGoalController(PodProjectGoalService ppgs) {
		super();
		this.ppgs = ppgs;
	}
	
	/**
	 * GET request for finding the PodProjectGoal object with the compound key.
	 * 
	 * @param Id
	 * @return the found Interview object
	 */
	@GetMapping("/search")
	public PodProjectGoal findUpskillingGoalBySkillAndGoal(@RequestParam long goalId, @RequestParam long projectId) {
		return ppgs.findPodProjectGoalByProjectAndGoal(goalId, projectId);
	}
	
	/**
	 * GET request for finding all PodProjectGoal objects in the database.
	 * 
	 * @return List of all PodProjectGoal objects
	 */
	@GetMapping
	public List<PodProjectGoal> findAll(){
		return this.ppgs.findAll();
	}
	
	/**
	 * POST request for persisting the deserialised PodProjectGoal object that is passed in.
	 * 
	 * @param podProjectGoal
	 * @return the persisted PodProjectGoal object
	 */
	@PostMapping
	public PodProjectGoal save(@RequestBody PodProjectGoal podProjectGoal) {
		return this.ppgs.save(podProjectGoal);
	}
	
	/**
	 * PUT request for updating the deserialised PodProjectGoal object that is passed in.
	 * 
	 * @param id
	 * @param podProjectGoal
	 * @return the updated PodProjectGoal object
	 */
	@PutMapping
	public PodProjectGoal update(@RequestBody PodProjectGoal podProjectGoal) {
		return this.ppgs.update(podProjectGoal);
	}
	
	/**
	 * DELETE request for deleting the PodProjectGoal object from the database with the passed in ID.
	 * 
	 * @param id
	 */
	@DeleteMapping("/search")
	public void delete(@RequestParam long goalId, @RequestParam long projectId) {
		PodProjectGoal podProjectGoal = ppgs.findPodProjectGoalByProjectAndGoal(goalId, projectId);
		this.ppgs.delete(podProjectGoal);
	}
}
