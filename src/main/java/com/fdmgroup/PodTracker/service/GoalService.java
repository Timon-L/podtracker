package com.fdmgroup.PodTracker.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.PodTracker.dal.GoalRepository;
import com.fdmgroup.PodTracker.exceptions.NotFoundException;
import com.fdmgroup.PodTracker.model.Goal;

/**
 * Service for Goal entity.
 * 
 * @author yiyanghou
 *
 */
@Service
public class GoalService {
	private GoalRepository gr;

	@Autowired
	public GoalService(GoalRepository gr) {
		super();
		this.gr = gr;
	}

	/**
	 * Finds Goal by Id.
	 * 
	 * @param id
	 * @return
	 */
	public Goal findById(long id) {
		return gr.findById(id)
				.orElseThrow(() -> new NotFoundException("Unable to find goal with id " + id));
	}

	/**
	 * Finds all Goal that are in the database.
	 * 
	 * @return
	 */
	public List<Goal> findAll() {
		List<Goal> goals = gr.findAll();
		if (goals.isEmpty()) {
			throw new NotFoundException("No goals found");
		}
		return goals;
	}

	/**
	 * Finds all goals with absent status.
	 * 
	 * @return
	 */
	public List<Goal> findAllAbsent() {
		List<Goal> goals = gr.findAll();
		if (goals.isEmpty()) {
			throw new NotFoundException("No goals found");
		}
		List<Goal> absences = new ArrayList<>();
		for (Goal goal : goals) {
			if (goal.getIsAbsence()) {
				absences.add(goal);
			}
		}
		return absences;
	}

	/**
	 * Finds all goals with present status.
	 * 
	 * @return
	 */
	public List<Goal> findAllPresent() {
		List<Goal> goals = gr.findAll();
		if (goals.isEmpty()) {
			throw new NotFoundException("No goals found");
		}
		List<Goal> presents = new ArrayList<>();
		for (Goal goal : goals) {
			if (!goal.getIsAbsence()) {
				presents.add(goal);
			}
		}
		return presents;
	}

	/**
	 * Updates goal object with the provided goal.
	 * @param goal
	 * @return updated goal
	 */
	public Goal update(Goal goal) {
		return gr.save(goal);
	}

	/**
	 * Persists/Add passed in goal.
	 * @param goal
	 * @return goal
	 */
	public Goal save(Goal goal) {
		return gr.save(goal);
	}

	/**
	 * Delete a Goal object from database by ID.
	 * @param id
	 */
	public void deleteById(long id) {
		if (!gr.existsById(id)) {
			throw new NotFoundException("Unable to find goal with id " + id);
		}
		gr.deleteById(id);
	}
	
}