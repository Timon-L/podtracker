package com.fdmgroup.PodTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.PodTracker.dal.PodRepository;
import com.fdmgroup.PodTracker.dal.UserRepository;
import com.fdmgroup.PodTracker.exceptions.NotFoundException;
import com.fdmgroup.PodTracker.model.Pod;
import com.fdmgroup.PodTracker.model.Trainee;
import com.fdmgroup.PodTracker.model.User;

/**
 * Service for the Pod entity.
 * 
 * @author yiyanghou
 *
 */
@Service
public class PodService {
	private PodRepository podRepo;
	private UserRepository userRepo;

	@Autowired
	public PodService(PodRepository podRepo, UserRepository userRepo) {
		super();
		this.podRepo = podRepo;
		this.userRepo = userRepo;
	} 

	/**
	 * Finds Pod object by ID.
	 * 
	 * @param Id
	 * @return The found Pod object.
	 */
	public Pod findPodById(long Id) {
		return podRepo.findById(Id)
				.orElseThrow(() -> new NotFoundException("Unable to find Pod with ID " + Id));
	}

	/**
	 * Finds all the Pod objects in the database.
	 * 
	 * @return The found pod Objects.
	 */
	public List<Pod> findAll() {
		return this.podRepo.findAll();
	}

	/**
	 * Persists the Pod object to the database.
	 * 
	 * @param pod
	 * @return The persisted Pod object.
	 */
	public Pod save(Pod pod) {
		return podRepo.save(pod);
	}

	/**
	 * Update the Pod object that has the same ID as the passed in ID, updated with the passed in object.
	 * 
	 * @param id
	 * @param pod
	 * @return The updated Pod object.
	 */
	public Pod update(long id, Pod pod) {
		if (!podRepo.existsById(id)) {
			throw new NotFoundException("Pod with id " + id + " does not exist.");
		}
		return podRepo.save(pod);
	}

	/**
	 * Delete the Pod object that has the same ID as the passed in ID.
	 * 
	 * @param Id
	 */
	public void removePodById(long id) {
		if (!podRepo.existsById(id)) {
			throw new NotFoundException("Pod with id " + id + " does not exist.");
		}
		podRepo.deleteById(id);
	}

	/**
	 * Add a User to the Pod by ID.
	 * 
	 * @param podId
	 * @param userId
	 * @return The Pod object.
	 */
	
	public Pod addUserToPodByIds(long podId, long userId) {
		Pod pod = podRepo.findById(podId)
				.orElseThrow(() -> new NotFoundException("Unable to find Pod with ID " + podId));
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new NotFoundException("Unable to find User with ID " + userId));

		if (user.getClass() == Trainee.class) {
			Trainee trainee = (Trainee) user;
			List<Trainee> trainees = pod.getUsers();
			trainees.add(trainee);
			pod.setTrainees(trainees); 
		} else
		{
			throw new NotFoundException("Unable to find Trainee with ID " + userId);
		}
		return pod;
	}

	/**
	 * Remove a user with certain ID from a selected pod.
	 * 
	 * @param podId
	 * @param userId
	 * @return	The Pod object.
	 */
	public Pod removeUserFromPodByIds(long podId, long userId) {
		Pod pod = podRepo.findById(podId)
				.orElseThrow(() -> new NotFoundException("Unable to find Pod with ID " + podId));
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new NotFoundException("Unable to find User with ID " + userId));

		List<Trainee> trainees = pod.getUsers();
		trainees.remove(user);
		pod.setTrainees(trainees); 
		return pod;
	}
}
