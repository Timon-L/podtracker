package com.fdmgroup.PodTracker.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fdmgroup.PodTracker.model.PodProject;
import com.fdmgroup.PodTracker.service.PodProjectService;


/**
 * Controller for PodProject service
 * 
 * @author yiyanghou
 *
 */
@RestController
@RequestMapping("projects")
public class PodProjectController {
	private PodProjectService pps;

	@Autowired
	public PodProjectController(PodProjectService pps) {
		super();
		this.pps = pps;
	}
	
	/**
	 * GET request for finding the PodProject object with the specified ID.
	 * 
	 * @param Id
	 * @return the found PodProject object
	 */
	@GetMapping("/search")
	public PodProject findById(@RequestParam long id) {
		return this.pps.findById(id);
	}
	
	/**
	 * GET request for finding all PodProject objects in the database.
	 * 
	 * @return List of all PodProject objects
	 */
	@GetMapping
	public List<PodProject> findAll(){
		return this.pps.findAll();
	}
	
	/**
	 * POST request for persisting the deserialised PodProject object that is passed in.
	 * 
	 * @param podProject
	 * @return the persisted PodProject object
	 */
	@PostMapping
	public PodProject save(@RequestBody PodProject podProject) {
		return this.pps.save(podProject);
	}
	
	/**
	 * PUT request for updating the deserialised PodProject object that is passed in.
	 * 
	 * @param id
	 * @param podProject
	 * @return the updated PodProject object
	 */
	@PutMapping
	public PodProject update(@RequestBody PodProject podProject) {
		return this.pps.update(podProject);
	}
	
	/**
	 * DELETE request for deleting the PodProject object from the database with the passed in ID.
	 * 
	 * @param id
	 */
	@DeleteMapping("/search")
	public void deleteById(@RequestParam long id) {
		this.pps.deleteById(id);
	}
}
