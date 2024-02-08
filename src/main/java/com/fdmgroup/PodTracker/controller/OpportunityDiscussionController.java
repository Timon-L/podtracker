package com.fdmgroup.PodTracker.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fdmgroup.PodTracker.model.OpportunityDiscussion;
import com.fdmgroup.PodTracker.service.OpportunityDiscussionService;

/**
 * Controller for OpportunityDiscussion Service
 * 
 * @author yiyanghou
 *
 */
@RestController
@RequestMapping("discussions")
public class OpportunityDiscussionController {
	private OpportunityDiscussionService ods;

	@Autowired
	public OpportunityDiscussionController(OpportunityDiscussionService ods) {
		super();
		this.ods = ods;
	}
	
	/**
	 * GET request for finding the OpportunityDiscussion object with the specified ID.
	 * 
	 * @param Id
	 * @return the found OpportunityDiscussion object
	 */
	@GetMapping("/search")
	public OpportunityDiscussion findById(@RequestParam long id) {
		return this.ods.findById(id);
	}
	
	/**
	 * GET request for finding all OpportunityDiscussion objects in the database.
	 * 
	 * @return List of all OpportunityDiscussion objects
	 */
	@GetMapping
	public List<OpportunityDiscussion> findAll(){
		return this.ods.findAll();
	}
	
	/**
	 * POST request for persisting the deserialised OpportunityDiscussion object that is passed in.
	 * 
	 * @param discussion
	 * @return the persisted OpportunityDiscussion object
	 */
	@PostMapping
	public OpportunityDiscussion save(@RequestBody OpportunityDiscussion discussion) {
		return this.ods.save(discussion);
	}
	
	/**
	 * PUT request for updating the deserialised OpportunityDiscussion object that is passed in.
	 * 
	 * @param id
	 * @param discussion
	 * @return the updated OpportunityDiscussion object
	 */
	@PutMapping
	public OpportunityDiscussion update(@RequestBody OpportunityDiscussion discussion) {
		return this.ods.update(discussion);
	}
	
	/**
	 * DELETE request for deleting the OpportunityDiscussion object from the database with the passed in ID.
	 * 
	 * @param id
	 */
	@DeleteMapping("/search")
	public void delete(@RequestParam long id) {
		this.ods.deleteById(id);
	}
}
