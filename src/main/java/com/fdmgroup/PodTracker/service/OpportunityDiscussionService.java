package com.fdmgroup.PodTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.PodTracker.dal.OpportunityDiscussionRepository;
import com.fdmgroup.PodTracker.exceptions.NotFoundException;
import com.fdmgroup.PodTracker.model.OpportunityDiscussion;

/**
 * Service for the OpportunityDiscussion entity.
 * 
 * @author yiyanghou
 *
 */
@Service
public class OpportunityDiscussionService {
	private OpportunityDiscussionRepository odr;

	@Autowired
	public OpportunityDiscussionService(OpportunityDiscussionRepository odr) {
		super();
		this.odr = odr;
	}
	
	/**
	 * Finds OpportunityDiscussion object by ID.
	 * 
	 * @param id
	 * @return The found OpportunityDiscussion object.
	 */
	public OpportunityDiscussion findById(long id) {
		return this.odr.findById(id)
				.orElseThrow(()-> new NotFoundException("Opportunity discussion with id " + id + " does not exist."));
	}
	
	/**
	 * Finds all OpportunityDiscussion objects in the database.
	 * 
	 * @return The list of OpportunityDiscussions
	 */
	public List<OpportunityDiscussion> findAll()
	{
		return this.odr.findAll();
	}
	
	/**
	 * Updates with the passed in OpportunityDiscussion object.
	 * 
	 * @param discussion
	 * @return the updated OpportunityDiscussion object.
	 */
	public OpportunityDiscussion update(OpportunityDiscussion discussion) {
		return odr.save(discussion);
	}
	
	/**
	 * Persists the passed in OpportunityDiscussion object.
	 * 
	 * @param discussion
	 * @return The saved OpportunityDiscussion object.
	 */
	public OpportunityDiscussion save(OpportunityDiscussion discussion) {
		return odr.save(discussion);
	} 
	
	/**
	 * Deletes the OpportunityDiscussion object by ID.
	 * 
	 * @param id
	 */
	public void deleteById(long id) {
		if (!odr.existsById(id)) {
			throw new NotFoundException("Opportunity discussion with id " + id + " does not exist.");
		}
		odr.deleteById(id);
	}
}
