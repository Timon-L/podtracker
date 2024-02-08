package com.fdmgroup.PodTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fdmgroup.PodTracker.model.Client;
import com.fdmgroup.PodTracker.service.ClientService;

/**
 * Controller for client service
 * 
 * @author yiyanghou
 *
 */
@RestController
@RequestMapping("clients")
public class ClientController {
	private ClientService clientService;
	
	@Autowired
	public ClientController(ClientService clientService)
	{
		super();
		this.clientService = clientService;
	}
	
	/**
	 * GET request to find all the clients in the database.
	 * 
	 * @return List of all clients
	 */
	@GetMapping
	public List<Client> findAll()
	{
		return this.clientService.findAll();
	}
	
	/**
	 * GET request to find the client with a specific ID.
	 * 
	 * @param Id
	 * @return the found client
	 */
	@GetMapping("{Id}")
	public Client findById(@PathVariable long Id)
	{
		return this.clientService.findById(Id);
	}
	
	/**
	 * POST request to deserialize the passed in client object and persist to the database.
	 * 
	 * @param client
	 * @return the persisted client
	 */
	@PostMapping
	public Client save(@RequestBody Client client) {
		return clientService.save(client);
	}
	
	/**
	 * PUT request to update a client object with a passed in client object.
	 * 
	 * @param id
	 * @param client
	 * @return the updated client
	 */
	@PutMapping("/{id}")
	public Client update(@PathVariable Long id, @RequestBody Client client) {
		return this.clientService.update(id, client);
	}
	
	/**
	 * DELETE request to delete a client from the database by ID.
	 * 
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		this.clientService.removeById(id);
	}
}
