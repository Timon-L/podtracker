package com.fdmgroup.PodTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.PodTracker.model.Opportunity;
import com.fdmgroup.PodTracker.service.OpportunityService;

/**
 * Controller for Opportunity Service.
 * 
 * @author yiyanghou
 *
 */
@RestController
@RequestMapping("opportunities")
public class OpportunityController {
	private OpportunityService opportunityService;
	
	@Autowired
	public OpportunityController(OpportunityService opportunityService)
	{
		super();
		this.opportunityService = opportunityService;
	}
	
	/**
	 * GET request for finding all Opportunity objects in the database.
	 * 
	 * @return List of all Interview objects
	 */
	@GetMapping
	public List<Opportunity> findAll()
	{
		return this.opportunityService.findAll();
	}
	
	/**
	 * GET request for finding the Opportunity object with the specified ID.
	 * 
	 * @param Id
	 * @return the found Opportunity object
	 */
	@GetMapping("{Id}")
	public Opportunity findById(@PathVariable long Id)
	{
		return this.opportunityService.findById(Id);
	}
	
	/**
	 * POST request for persisting the deserialised Opportunity object that is passed in.
	 * 
	 * @param Opportunity
	 * @return the persisted Opportunity object
	 */
	@PostMapping
	public Opportunity save(@RequestBody Opportunity opportunity) {
		return opportunityService.save(opportunity);
	}
	
	/**
	 * PUT request for updating the deserialised Opportunity object that is passed in.
	 * 
	 * @param id
	 * @param Opportunity
	 * @return the updated Opportunity object
	 */
	@PutMapping("/{id}")
	public Opportunity update(@PathVariable Long id, @RequestBody Opportunity opportunity) {
		return this.opportunityService.update(id, opportunity);
	}
	
	/**
	 * DELETE request for deleting the Interview object from the database with the passed in ID.
	 * 
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		this.opportunityService.removeById(id);
	}
}
