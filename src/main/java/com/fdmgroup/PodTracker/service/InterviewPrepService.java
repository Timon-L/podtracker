package com.fdmgroup.PodTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.PodTracker.dal.InterviewPrepRepository;
import com.fdmgroup.PodTracker.exceptions.NotFoundException;
import com.fdmgroup.PodTracker.model.InterviewPrep;

/**
 * The service for the InterviewPrep entity.
 * 
 * @author yiyanghou
 *
 */
@Service
public class InterviewPrepService {
	private InterviewPrepRepository interviewPrepRepo;

	@Autowired
	public InterviewPrepService(InterviewPrepRepository interviewPrepRepo) {
		super();
		this.interviewPrepRepo = interviewPrepRepo;
	}

	/**
	 * Finds InterviewPrep object by ID.
	 * 
	 * @param Id
	 * @return a single InterviewPrep object.
	 */
	public InterviewPrep findById(long id) {
		return interviewPrepRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("InterviewPrep with id " + id + " does not exist."));
	}

	/**
	 * Finds all the InterviewPrep object in the database.
	 * 
	 * @return all InterviewPrep objects in an ArrayList.
	 */
	public List<InterviewPrep> findAll() {
		return this.interviewPrepRepo.findAll();
	}

	/**
	 * Persists the InterviewPrep object to the database.
	 * 
	 * @param interviewPrep
	 * @return a new InterviewPrep object
	 */
	public InterviewPrep save(InterviewPrep interviewPrep) {
		return interviewPrepRepo.save(interviewPrep);
	}

	/**
	 * Updates the with the passed in InterviewPrep object.
	 * 
	 * @param id
	 * @param interviewPrep
	 * @return an updated InterviewPrep object
	 */
	public InterviewPrep update(long id, InterviewPrep interviewPrep) {
		if (!interviewPrepRepo.existsById(id)) {
			throw new NotFoundException("InterviewPrep with id " + id + " does not exist.");
		}
		return interviewPrepRepo.save(interviewPrep);
	}

	/**
	 * Remove the passed in InterviewPrep object.
	 * 
	 * @param id
	 */
	public void removeById(long id) {
		if (!interviewPrepRepo.existsById(id)) {
			throw new NotFoundException("InterviewPrep with id " + id + " does not exist.");
		}
		interviewPrepRepo.deleteById(id);
	}
}
