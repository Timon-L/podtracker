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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.PodTracker.model.Pod;
import com.fdmgroup.PodTracker.service.PodService;

/**
 * Controller for Pod service
 * 
 * @author yiyanghou
 *
 */
@RestController
@RequestMapping("pods")
public class PodController {
	private PodService podService;
	
	@Autowired
	public PodController(PodService podService)
	{
		super();
		this.podService = podService;
	}
	
	/**
	 * GET request for finding all Pod objects in the database.
	 * 
	 * @return List of all Pod objects
	 */
	@GetMapping
	public List<Pod> findAll()
	{
		return this.podService.findAll();
	}
	
	/**
	 * GET request for finding the Pod object with the specified ID.
	 * 
	 * @param Id
	 * @return the found Pod object
	 */
	@GetMapping("{Id}")
	public Pod findById(@PathVariable long Id)
	{
		return this.podService.findPodById(Id);
	}
	
	/**
	 * POST request for persisting the deserialised Pod object that is passed in.
	 * 
	 * @param pod
	 * @return the persisted Pod object
	 */
	@PostMapping
	public Pod savePod(@RequestBody Pod pod) {
		return podService.save(pod);
	}
	
	/**
	 * PUT request for updating the deserialised Pod object that is passed in.
	 * 
	 * @param id
	 * @param pod
	 * @return the updated Pod object
	 */
	@PutMapping("/{id}")
	public Pod updatePod(@PathVariable Long id, @RequestBody Pod pod) {
		return this.podService.update(id, pod);
	}
	
	/**
	 * DELETE request for deleting the Pod object from the database with the passed in ID.
	 * 
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public void deletePod(@PathVariable Long id) {
		this.podService.removePodById(id);
	}
	
	/**
	 * PUT request that adds a specified new trainee to the specified pod.
	 * 
	 * @param podId
	 * @param userId
	 * @return the updated Pod object
	 */
	@PutMapping("/addByIds")
	public Pod addUserToPodByIds(@RequestParam long podId, @RequestParam long userId) {
		return this.podService.addUserToPodByIds(podId, userId);
	}
	
	/**
	 * PUT request that removes a specified trainee from the specified pod.
	 * @param podId
	 * @param userId
	 * @return the updated Pod object 
	 */
	@PutMapping("/removeByIds")
	public Pod removeUserFromPodByIds(@RequestParam long podId, @RequestParam long userId) {
		return this.podService.removeUserFromPodByIds(podId, userId);
	}
}
