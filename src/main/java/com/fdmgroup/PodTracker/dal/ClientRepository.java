package com.fdmgroup.PodTracker.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.PodTracker.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
