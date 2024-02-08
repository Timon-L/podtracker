package com.fdmgroup.PodTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.PodTracker.dal.OpportunityRepository;
import com.fdmgroup.PodTracker.exceptions.NotFoundException;
import com.fdmgroup.PodTracker.model.Opportunity;

/**
 * Service for the Opportunity entity.
 * 
 * @author yiyanghou
 *
 */
@Service
public class OpportunityService {
	private OpportunityRepository opportunityRepo;

	@Autowired
	public OpportunityService(OpportunityRepository opportunityRepo) {
		super();
		this.opportunityRepo = opportunityRepo;
	}

	/**
	 * Finds the Opportunity object by ID.
	 * 
	 * @param Id
	 * @return the found Opportunity object.
	 */
	public Opportunity findById(long id) {
		return opportunityRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Opportunity with id " + id + " does not exist."));
	}

	/**
	 * Finds all the Opportunity objects in the database.
	 * 
	 * @return The list of Opportunities
	 */
	public List<Opportunity> findAll() {
		return this.opportunityRepo.findAll();
	}

	/**
	 * Persists the Opportunity object to the database.
	 * 
	 * @param opportunity
	 * @return The saved Opportunity object.
	 */
	public Opportunity save(Opportunity opportunity) {
		return opportunityRepo.save(opportunity);
	}

	/**
	 * Update with the passed in Opportunity object.
	 * 
	 * @param id
	 * @param opportunity
	 * @return updated Opportunity object.
	 */
	public Opportunity update(long id, Opportunity opportunity) {
		if (!opportunityRepo.existsById(id)) {
			throw new NotFoundException("Opportunity with id " + id + " does not exist.");
		}
		return opportunityRepo.save(opportunity);
	}

	/**
	 * Removes the Opportunity object by ID.
	 * 
	 * @param Id
	 */
	public void removeById(long Id) {
		if (!opportunityRepo.existsById(Id)) {
			throw new NotFoundException("Opportunity with id " + Id + " does not exist.");
		}
		opportunityRepo.deleteById(Id);
	}
}
