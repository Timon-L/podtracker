package com.fdmgroup.PodTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.PodTracker.dal.PodProjectRepository;
import com.fdmgroup.PodTracker.exceptions.NotFoundException;
import com.fdmgroup.PodTracker.model.PodProject;

/**
 * Service for the PodProject entity.
 * 
 * @author yiyanghou
 *
 */
@Service
public class PodProjectService {
	private PodProjectRepository ppr;

	@Autowired
	public PodProjectService(PodProjectRepository ppr) {
		this.ppr = ppr;
	}

	/**
	 * Finds the PodProject object by ID.
	 * @param id
	 * @return the found PodProject object.
	 */
	public PodProject findById(long id) {
		return ppr.findById(id)
				.orElseThrow(() -> new NotFoundException("Pod project with id " + id + " does not exist."));
	}

	
	/**
	 * Finds all the PodProject objects in the database.
	 * 
	 * @return the list of all found PodProject objects.
	 */
	public List<PodProject> findAll() {
		return this.ppr.findAll();
	}

	/**
	 * Updates the object with the passed in PodProject object.
	 * 
	 * @param project
	 * @return the updated PodProject object.
	 */
	public PodProject update(PodProject project) {
		return ppr.save(project);
	}

	/**
	 * Persists the PodProject object to the database.
	 * 
	 * @param project
	 * @return PodProject object
	 */
	public PodProject save(PodProject project) {
		return ppr.save(project);
	}

	/**
	 * Delete the PopProject object by ID.
	 * 
	 * @param id
	 */
	public void deleteById(long id) {
		if (!ppr.existsById(id)) {
			throw new NotFoundException("Pod project with id " + id + " does not exist.");
		}
		ppr.deleteById(id);
	}
}
