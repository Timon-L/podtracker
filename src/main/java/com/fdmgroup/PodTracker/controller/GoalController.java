package com.fdmgroup.PodTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fdmgroup.PodTracker.model.*;
import com.fdmgroup.PodTracker.service.*;

/**
 * Controller for the Goal service
 * 
 * @author yiyanghou
 *
 */
@RestController
@RequestMapping("goals")
public class GoalController {
	private GoalService gs;
	private PodProjectGoalService ppgs;
	private UpskillingGoalService ugs;
	private DiscussionGoalService dgs;

	@Autowired
	public GoalController(GoalService gs, PodProjectGoalService ppgs, UpskillingGoalService ugs,
			DiscussionGoalService dgs) {
		super();
		this.gs = gs;
		this.ppgs = ppgs;
		this.ugs = ugs;
		this.dgs = dgs;
	}
	
	/**
	 * GET request to find the Goal by it's ID.
	 * 
	 * @param id
	 * @return the found Goal
	 */
	@GetMapping("/search")
	public Goal findById(@RequestParam long id) {
		return this.gs.findById(id);
	}
	
	/**
	 * GET request to find all the Goal objects in the database.
	 * 
	 * @return the list of all Goal objects
	 */
	@GetMapping
	public List<Goal> findAll(){
		return this.gs.findAll();
	}
	
	/**
	 * GET request to find all the Goals with the absent condition in the database.
	 * 
	 * @return the list of all the Goal objects with the absent condition
	 */
	@GetMapping("absence")
	public List<Goal> findAllAbsent(){
		return this.gs.findAllAbsent();
	}
	
	/**
	 * GET request to find all the Goals with non absent condition in the database.
	 * 
	 * @return the list of all the Goal objects with non absent condition 
	 */
	@GetMapping("present")
	public List<Goal> findAllPresent(){
		return this.gs.findAllPresent();
	}
	
	/**
	 * GET request to find all the PodProjectGoal objects by the passed in Goal ID.
	 * 
	 * @param id
	 * @return The found PodProjectGoal objects
	 */
	@GetMapping("podProjectGoal")
	public List<PodProjectGoal> findAllPodProjects(@RequestParam long id) {
		return ppgs.findByGoal(gs.findById(id));
	}
	
	/**
	 * GET request to find all the UpskillingGoal objects by the passed in Goal ID.
	 * 
	 * @param id
	 * @return The found UpskillingGoal objects
	 */
	@GetMapping("upskillingGoal")
	public List<UpskillingGoal> findAllUpskillings(@RequestParam long id) {
		return ugs.findByGoal(gs.findById(id));
	}
	
	/**
	 * GET request to find all the DiscussionGoal objects by the passed in Goal ID.
	 * 
	 * @param id
	 * @return The found DiscussionGoal objects
	 */
	@GetMapping("discussionGoal")
	public List<DiscussionGoal> findAllDiscussions(@RequestParam long id) {
		return dgs.findByGoal(gs.findById(id));
	}
	/**
	 * POST request to save the passed in Goal object.
	 * 
	 * @param goal
	 * @return The saved Goal object
	 */
	@PostMapping
	public Goal save(@RequestBody Goal goal) {
		return this.gs.save(goal);
	}
	
	/**
	 * PUT request to update the passed in Goal object.
	 * 
	 * @param goal
	 * @return the updated Goal object
	 */
	@PutMapping
	public Goal update(@RequestBody Goal goal) {
		return this.gs.update(goal);
	}
	
	/**
	 * DELETE request to delete the Goal object by ID.
	 * 
	 * @param id
	 */
	@DeleteMapping("/search")
	public void delete(@RequestParam long id) {
		this.gs.deleteById(id);
	}
}
