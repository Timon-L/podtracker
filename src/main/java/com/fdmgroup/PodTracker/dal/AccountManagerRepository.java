package com.fdmgroup.PodTracker.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.PodTracker.model.AccountManager;

public interface AccountManagerRepository extends JpaRepository<AccountManager, Long> {

}
