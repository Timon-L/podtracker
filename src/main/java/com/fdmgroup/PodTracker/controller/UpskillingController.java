package com.fdmgroup.PodTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fdmgroup.PodTracker.model.Upskilling;
import com.fdmgroup.PodTracker.service.UpskillingService;

/**
 * The controller for Upskilling service.
 * 
 * @author yiyanghou
 *
 */
@RestController
@RequestMapping("upskillings")
public class UpskillingController {
	private UpskillingService us;

	@Autowired
	public UpskillingController(UpskillingService us) {
		super();
		this.us = us;
	}

	/**
	 * GET request for finding the Upskilling object with the specified ID.
	 * 
	 * @param Id
	 * @return the found Upskilling object
	 */
	@GetMapping("/search")
	public Upskilling findById(@RequestParam long id) {
		return this.us.findById(id);
	}

	/**
	 * GET request for finding all Upskilling objects in the database.
	 * 
	 * @return List of all Upskilling objects
	 */
	@GetMapping
	public List<Upskilling> findAll() {
		return this.us.findAll();
	}

	/**
	 * POST request for persisting the deserialised Upskilling object that is passed in.
	 * 
	 * @param skill
	 * @return the persisted Upskilling object
	 */
	@PostMapping
	public Upskilling save(@RequestBody Upskilling skill) {
		return us.save(skill);
	}
	
	/**
	 * PUT request for updating the deserialised Upskilling object that is passed in.
	 * 
	 * @param skill
	 * @return the updated Upskilling object
	 */
	@PutMapping
	public Upskilling update(@RequestBody Upskilling skill) {
		return us.update(skill);
	}

	/**
	 * DELETE request for deleting the Upskilling object from the database with the passed in ID.
	 * 
	 * @param id
	 */
	@DeleteMapping("/search")
	public void delete(@RequestParam long id) {
		us.deleteById(id);
	}

}
