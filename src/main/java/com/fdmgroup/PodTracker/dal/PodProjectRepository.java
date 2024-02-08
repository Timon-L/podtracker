package com.fdmgroup.PodTracker.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.PodTracker.model.PodProject;

public interface PodProjectRepository extends JpaRepository<PodProject, Long> {

}
