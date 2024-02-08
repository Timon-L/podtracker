package com.fdmgroup.PodTracker.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fdmgroup.PodTracker.model.UpskillingGoal;
import com.fdmgroup.PodTracker.service.UpskillingGoalService;

/**
 * Controller for the bridging entity in between Upskilling entity and Goal entity.
 * 
 * @author yiyanghou
 *
 */
@RestController
@RequestMapping("upskilling_goal")
public class UpskillingGoalController {
	private UpskillingGoalService ugs;

	@Autowired
	public UpskillingGoalController(UpskillingGoalService ugs) {
		super();
		this.ugs = ugs;
	}
	
	/**
	 * GET request for finding the UpskillingGoal object with the specified compound key.
	 * 
	 * @param Id
	 * @return the found UpskillingGoal object
	 */
	@GetMapping("/search")
	public UpskillingGoal findUpskillingGoalBySkillAndGoal(@RequestParam long goalId, @RequestParam long skillId) {
		return ugs.findUpskillingGoalBySkillAndGoal(goalId, skillId);
	}
	
	/**
	 * GET request for finding all UpskillingGoal objects in the database.
	 * 
	 * @return List of all UpskillingGoal objects
	 */
	@GetMapping
	public List<UpskillingGoal> findAll(){
		return this.ugs.findAll();
	}
	
	/**
	 * POST request for persisting the deserialised UpskillingGoal object that is passed in.
	 * 
	 * @param upskillingGoal
	 * @return the persisted UpskillingGoal object
	 */
	@PostMapping
	public UpskillingGoal save(@RequestBody UpskillingGoal upskillingGoal) {
		return this.ugs.save(upskillingGoal);
	}
	
	/**
	 * PUT request for updating the deserialised UpskillingGoal object that is passed in.
	 * 
	 * @param id
	 * @param upskillingGoal
	 * @return the updated UpskillingGoal object
	 */
	@PutMapping
	public UpskillingGoal update(@RequestBody UpskillingGoal upskillingGoal) {
		return this.ugs.update(upskillingGoal);
	}
	
	/**
	 * DELETE request for deleting the UpskillingGoal object from the database with the passed in ID.
	 * 
	 * @param id
	 */
	@DeleteMapping("/search")
	public void delete(@RequestParam long goalId, @RequestParam long skillId) {
		UpskillingGoal upskillingGoal = ugs.findUpskillingGoalBySkillAndGoal(goalId, skillId);
		this.ugs.delete(upskillingGoal);
	}

}
