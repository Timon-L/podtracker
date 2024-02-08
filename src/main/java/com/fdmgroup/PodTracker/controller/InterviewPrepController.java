package com.fdmgroup.PodTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fdmgroup.PodTracker.model.InterviewPrep;
import com.fdmgroup.PodTracker.service.InterviewPrepService;

/**
 * Controller for the InterviewPrep service.
 * 
 */
@RestController
@RequestMapping("interviewprep")
public class InterviewPrepController {
	private InterviewPrepService interviewPrepService;
	
	@Autowired
	public InterviewPrepController(InterviewPrepService interviewPrepService)
	{
		super();
		this.interviewPrepService = interviewPrepService;
	}
	
	/**
	 * GET request for finding all InterviewPrep objects in the database.
	 * 
	 * @return List of all InterviewPrep objects
	 */
	@GetMapping
	public List<InterviewPrep> findAll()
	{
		return this.interviewPrepService.findAll();
	}
	
	/**
	 * GET request for finding the InterviewPrep object with the specified ID.
	 * 
	 * @param Id
	 * @return the found InterviewPrep object
	 */
	@GetMapping("{Id}")
	public InterviewPrep findById(@PathVariable long Id)
	{
		return this.interviewPrepService.findById(Id);
	}
	
	/**
	 * POST request for persisting the deserialised InterviewPrep object that is passed in.
	 * 
	 * @param interviewPrep
	 * @return the persisted InterviewPrep object
	 */
	@PostMapping
	public InterviewPrep save(@RequestBody InterviewPrep interviewPrep) {
		return interviewPrepService.save(interviewPrep);
	}
	
	/**
	 * PUT request for updating the deserialised InterviewPrep object that is passed in.
	 * 
	 * @param id
	 * @param interview
	 * @return the updated Interview object
	 */
	@PutMapping("/{id}")
	public InterviewPrep update(@PathVariable Long id, @RequestBody InterviewPrep interviewPrep) {
		return this.interviewPrepService.update(id, interviewPrep);
	}
	
	/**
	 * DELETE request for deleting the InterviewPrep object from the database with the passed in ID.
	 * 
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		this.interviewPrepService.removeById(id);
	}
}
