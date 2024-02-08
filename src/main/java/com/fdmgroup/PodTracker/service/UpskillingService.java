package com.fdmgroup.PodTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.PodTracker.dal.UpskillingRepository;
import com.fdmgroup.PodTracker.exceptions.NotFoundException;
import com.fdmgroup.PodTracker.model.Upskilling;

/**
 * Service for the Upskilling entity.
 * 
 * @author yiyanghou
 *
 */
@Service
public class UpskillingService {
	private UpskillingRepository ur;
	
	@Autowired
	public UpskillingService(UpskillingRepository ur) {
		this.ur = ur;
	}
	
	/**
	 * Persists the Upskilling object in the database.
	 * 
	 * @param skill
	 * @return the saved Upskilling object.
	 */
	public Upskilling save(Upskilling skill) {
		if (skill.getTopic() == null) {
            throw new IllegalArgumentException("Topic cannot be null");
        }
		return this.ur.save(skill); 
	}
	
	/**
	 * Finds the Upskilling object by ID.
	 * 
	 * @param id
	 * @return the found Upskilling object.
	 */
	public Upskilling findById(long id) {
		return ur.findById(id)
				.orElseThrow(() -> new NotFoundException("Unable to find upskilling with id " + id));
	} 
	
	/**
	 * Finds all the Upskilling objects in the database.
	 * 
	 * @return All the Upskilling objects.
	 */
	public List<Upskilling> findAll(){
		return ur.findAll();
	}
	
	/**
	 * Updates the passed in Upskilling object.
	 * 
	 * @param skill
	 * @return the updated Upskilling object.
	 */
	public Upskilling update(Upskilling skill) {
		return ur.save(skill);
	}
	
	/**
	 * Delete the Upskilling object from the database by ID.
	 * @param id
	 */
	public void deleteById(long id) {
		if (!ur.existsById(id)) {
			throw new NotFoundException("Unable to find upskilling with id " + id);
		}
		ur.deleteById(id);
	}
}
