package com.fdmgroup.PodTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.PodTracker.dal.ClientRepository;
import com.fdmgroup.PodTracker.exceptions.NotFoundException;
import com.fdmgroup.PodTracker.model.Client;

/**
 * It is the service for client entity.
 * 
 * @author yiyanghou
 *
 */
@Service
public class ClientService {
	private ClientRepository clientRepo;

	@Autowired
	public ClientService(ClientRepository clientRepo) {
		super();
		this.clientRepo = clientRepo;
	}

	/**
	 * Finds client by Id.
	 * 
	 * @param Id
	 * @return
	 */
	public Client findById(long Id) {
		return clientRepo.findById(Id)
				.orElseThrow(() -> new NotFoundException("Unable to find client with Id " + Id));
	} 

	/**
	 * Finds all clients in the database.
	 * 
	 * @return
	 */
	public List<Client> findAll() {
		return this.clientRepo.findAll();
	}

	/**
	 * Persists the client object.
	 * 
	 * @param client
	 * @return
	 */
	public Client save(Client client) {
		return clientRepo.save(client);
	}

	/**
	 * Update the client object.
	 * 
	 * @param id
	 * @param client
	 * @return
	 */
	public Client update(long id, Client client) {
		if (!clientRepo.existsById(id)) {
			throw new NotFoundException("Client with id " + id + " does not exist.");
		}
		return clientRepo.save(client);
	}

	/**
	 * Remove a client object from the database by ID.
	 * @param Id
	 * @return
	 */
	public void removeById(long Id) {
		if (!clientRepo.existsById(Id)) {
			throw new NotFoundException("Client with id " + Id + " does not exist.");
		}
		clientRepo.deleteById(Id);
	}
}
