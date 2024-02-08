package com.fdmgroup.PodTracker.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.PodTracker.model.Pod;

public interface PodRepository extends JpaRepository<Pod, Long> {

}
