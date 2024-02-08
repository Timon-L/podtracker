package com.fdmgroup.PodTracker.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.PodTracker.model.AccountManager;
import com.fdmgroup.PodTracker.model.Opportunity;

public interface OpportunityRepository extends JpaRepository<Opportunity, Long>{

}
