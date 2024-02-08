package com.fdmgroup.PodTracker.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fdmgroup.PodTracker.model.DiscussionGoal;
import com.fdmgroup.PodTracker.service.DiscussionGoalService;

/**
 * Controller for the DiscussionGoal Service
 * 
 * @author yiyanghou
 *
 */
@RestController
@RequestMapping("discussion_goal")
public class DiscussionGoalController {
	private DiscussionGoalService dgs;

	@Autowired
	public DiscussionGoalController(DiscussionGoalService dgs) {
		super();
		this.dgs = dgs;
	}
	
	/**
	 * GET request to find the DiscussionGoal object by the compound key in the database.
	 * 
	 * @param goalId
	 * @param discussionId
	 * @return the found DiscussionGoal object
	 */
	@GetMapping("/search")
	public DiscussionGoal findDiscussionGoalByDiscussionAndGoal(@RequestParam long goalId, @RequestParam long discussionId) {
		return dgs.findDiscussionGoalByDiscussionAndGoal(goalId, discussionId);
	}
	
	/**
	 * GET request to find all the DiscussionGoal objects in the database.
	 * 
	 * @return all the DiscussionGoal objects
	 */
	@GetMapping
	public List<DiscussionGoal> findAll(){
		return this.dgs.findAll();
	}
	
	/**
	 * POST request to deserialise the passed in DiscussionGoal JSON and persist to database. 
	 * 
	 * @param discussionGoal
	 * @return the persisted DiscussionGoal object
	 */
	@PostMapping
	public DiscussionGoal save(@RequestBody DiscussionGoal discussionGoal) {
		return this.dgs.save(discussionGoal);
	}
	
	/**
	 * PUT request to deserialise the passed in DiscussionGoal JSON and update with it.
	 * @param discussionGoal
	 * @return the updated DiscussionGoal object
	 */
	@PutMapping
	public DiscussionGoal update(@RequestBody DiscussionGoal discussionGoal) {
		return this.dgs.update(discussionGoal);
	}
	
	/**
	 * DELETE request to delete the DiscussionGoal object from the database by its ID.
	 * 
	 * @param goalId
	 * @param discussionId
	 */
	@DeleteMapping("/search")
	public void delete(@RequestParam long goalId, @RequestParam long discussionId) {
		DiscussionGoal discussionGoal = dgs.findDiscussionGoalByDiscussionAndGoal(goalId, discussionId);
		this.dgs.delete(discussionGoal);
	}
}
