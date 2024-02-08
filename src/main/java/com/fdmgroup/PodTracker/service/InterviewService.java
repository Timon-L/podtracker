package com.fdmgroup.PodTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.PodTracker.dal.InterviewRepository;
import com.fdmgroup.PodTracker.exceptions.NotFoundException;
import com.fdmgroup.PodTracker.model.Interview;

/**
 * Service for Interview entity.
 * @author yiyanghou
 *
 */
@Service
public class InterviewService {
	private InterviewRepository interviewRepo;

	@Autowired
	public InterviewService(InterviewRepository interviewRepo) {
		super();
		this.interviewRepo = interviewRepo;
	}

	/**
	 * Finds the Interview object by ID.
	 * @param Id
	 * @return an Interview object.
	 */
	public Interview findById(long id) {
		return interviewRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Interview with id " + id + " does not exist."));
	}

	/**
	 * Finds all Interview objects that are in the database.
	 * @return all Interview objects.
	 */
	public List<Interview> findAll() {
		return this.interviewRepo.findAll();
	}

	/**
	 * Persists the passed in Interview object to the database.
	 * 
	 * @param interview
	 * @return a new Interview object
	 */
	public Interview save(Interview interview) {
		return interviewRepo.save(interview);
	}

	/**
	 * Updates the passed in Interview object.
	 * 
	 * @param id
	 * @param interview
	 * @return updated Interview object
	 */
	public Interview update(long id, Interview interview) {
		if (!interviewRepo.existsById(id)) {
			throw new NotFoundException("Interview with id " + id + " does not exist.");
		}
		return interviewRepo.save(interview);
	}

	/**
	 * Delete the object from the database by ID.
	 * 
	 * @param Id
	 */
	public void removeById(long Id) {
		if (!interviewRepo.existsById(Id)) {
			throw new NotFoundException("Interview with id " + Id + " does not exist.");
		}
		interviewRepo.deleteById(Id);
	}
}
