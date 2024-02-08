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

import com.fdmgroup.PodTracker.model.Interview;
import com.fdmgroup.PodTracker.service.InterviewService;

/**
 * Controller for the Interview entity.
 * 
 * @author yiyanghou
 *
 */
@RestController
@RequestMapping("interviews")
public class InterviewController {
	private InterviewService interviewService;
	
	@Autowired
	public InterviewController(InterviewService interviewService)
	{
		super();
		this.interviewService = interviewService;
	}
	
	/**
	 * GET request for finding all Interview objects in the database.
	 * 
	 * @return List of all Interview objects
	 */
	@GetMapping
	public List<Interview> findAll()
	{
		return this.interviewService.findAll();
	}
	
	/**
	 * GET request for finding the Interview object with the specified ID.
	 * 
	 * @param Id
	 * @return the found Interview object
	 */
	@GetMapping("{Id}")
	public Interview findById(@PathVariable long Id)
	{
		return this.interviewService.findById(Id);
	}
	
	/**
	 * POST request for persisting the deserialised Interview object that is passed in.
	 * 
	 * @param interview
	 * @return the persisted Interview object
	 */
	@PostMapping
	public Interview save(@RequestBody Interview interview) {
		return interviewService.save(interview);
	}
	
	/**
	 * PUT request for updating the deserialised Interview object that is passed in.
	 * 
	 * @param id
	 * @param interview
	 * @return the updated Interview object
	 */
	@PutMapping("/{id}")
	public Interview update(@PathVariable Long id, @RequestBody Interview interview) {
		return this.interviewService.update(id, interview);
	}
	
	/**
	 * DELETE request for deleting the Interview object from the database with the passed in ID.
	 * 
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		this.interviewService.removeById(id);
	}
}
