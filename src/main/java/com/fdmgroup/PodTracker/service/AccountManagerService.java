package com.fdmgroup.PodTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.PodTracker.dal.AccountManagerRepository;

import com.fdmgroup.PodTracker.exceptions.NotFoundException;
import com.fdmgroup.PodTracker.model.AccountManager;

/**
 * This is the service class for account manager entity.
 * 
 * @author yiyanghou
 *
 */
@Service
public class AccountManagerService {
	private AccountManagerRepository accountManagerRepo;

	@Autowired
	public AccountManagerService(AccountManagerRepository accountManagerRepo) { 
		super();
		this.accountManagerRepo = accountManagerRepo;
	} 

	/**
	 * Finds the account manager object by ID.
	 * 
	 * @param Id
	 * @return the found AccountManager object
	 */
	public AccountManager findById(long Id) {
		return accountManagerRepo.findById(Id)
				.orElseThrow(() -> new NotFoundException("Unable to find account manager with Id " + Id));
	}

	/**
	 * Finds all the account manager objects there are in the database.
	 * 
	 * @return All the AccountManager objects in the database.
	 */
	public List<AccountManager> findAll() {
		return this.accountManagerRepo.findAll();
	}

	/**
	 * Persists a new account manager object.
	 * 
	 * @param accountManage
	 * @return the saved AccountManager object
	 */
	public AccountManager save(AccountManager accountManage) {
		return accountManagerRepo.save(accountManage);
	}

	/**
	 * Update the account manager object.
	 * 
	 * @param id
	 * @param accountManager
	 * @return the updated AccountManager object
	 */
	public AccountManager update(long id, AccountManager accountManager) {
		if (!accountManagerRepo.existsById(id)) {
			throw new NotFoundException("Account manager with id " + id + " does not exist.");
		}
		return accountManagerRepo.save(accountManager);
	}

	/**
	 * Removes object by id.
	 * 
	 * @param Id
	 */
	public void removeById(long id) {
		if (!accountManagerRepo.existsById(id)) {
			throw new NotFoundException("Account manager with id " + id + " does not exist.");
		}
		accountManagerRepo.deleteById(id);
	}

}
