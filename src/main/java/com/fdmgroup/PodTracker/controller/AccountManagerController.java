package com.fdmgroup.PodTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.PodTracker.service.AccountManagerService;
import com.fdmgroup.PodTracker.model.AccountManager;

/**
 * Controller for account manager service
 * 
 * @author yiyanghou
 *
 */
@RestController
@RequestMapping("accountmanagers")
public class AccountManagerController {
	private AccountManagerService accountManagerService;
	
	@Autowired
	public AccountManagerController(AccountManagerService accountManagerService)
	{
		super();
		this.accountManagerService = accountManagerService;
	}
	
	/**
	 * GET request for finding all account managers.
	 * 
	 * @return all found account managers.
	 */
	@GetMapping
	public List<AccountManager> findAll()
	{
		return this.accountManagerService.findAll();
	}
	
	/**
	 * GET request for finding account manager by ID.
	 * 
	 * @param Id
	 * @return the found account manager
	 */
	@GetMapping("{Id}")
	public AccountManager findById(@PathVariable long Id)
	{
		return this.accountManagerService.findById(Id);
	}
	
	
	/**
	 * POST request to save the passed in account manager.
	 * 
	 * @param accountManager
	 * @return the saved account manager
	 */
	@PostMapping
	public AccountManager save(@RequestBody AccountManager accountManager) {
		return accountManagerService.save(accountManager);
	}
	
	/** 
	 * PUT request to update an account manager with the passed in account manager.
	 * 
	 * @param id
	 * @param accountManager
	 * @return updated account manager.
	 */
	@PutMapping("/{id}")
	public AccountManager update(@PathVariable Long id, @RequestBody AccountManager accountManager) {
		return this.accountManagerService.update(id, accountManager);
	}
	
	/**
	 * DELETE request to delete an account manager by ID.
	 * 
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		this.accountManagerService.removeById(id);
	}
}
