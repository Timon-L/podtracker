package com.fdmgroup.PodTracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PodTracker.dal.AccountManagerRepository;
import com.fdmgroup.PodTracker.exceptions.NotFoundException;
import com.fdmgroup.PodTracker.model.*;

@ExtendWith(MockitoExtension.class)
class AccountManagerServiceTest {
	
	@Mock
	private AccountManagerRepository amRepoMock;
	
	@InjectMocks
	private AccountManagerService amService;
	private AccountManager accountManager;
	private List<AccountManager> accountManagers;
	private Long amId;
	private Long invalidAmId;

	@BeforeEach
	void setUp() throws Exception {
		this.amId = 2L;
		this.accountManager = new AccountManager(); 
		this.accountManager.setId(amId);
		this.accountManager.setName("Severus Snape");
		this.accountManager.setLocation(FDMLocation.NSW_Sydney);
		this.invalidAmId = 4L;
		this.accountManagers = new ArrayList<>();
		this.accountManagers.add(accountManager);
	}

	@Test
	void test_FindById_ShouldReturnAnAccountManager_WhenIdFound() {
		// Arrange
		when(amRepoMock.findById(amId)).thenReturn(Optional.of(accountManager));
		// Act
		AccountManager foundAccountManager = amService.findById(amId);
		// Assert
		assertEquals(accountManager, foundAccountManager);
		verify(amRepoMock).findById(amId);
	}
	
	@Test
	void test_FindById_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		String message = "Unable to find account manager with Id " + invalidAmId;
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> amService.findById(invalidAmId));
		// Assert
		assertEquals(message, actualExceptionThrown.getMessage());
		verify(amRepoMock).findById(invalidAmId);
	}
	
	@Test
	void test_FindAll_ShouldFindAllAccountManagers() {
		// Arrange
		AccountManager accountManager2 = new AccountManager();
		accountManagers.add(accountManager2);
		when(amRepoMock.findAll()).thenReturn(accountManagers);
		// Act
		List<AccountManager> foundAccountManagers = amService.findAll();
		// Assert
		assertNotNull(foundAccountManagers);
		assertEquals(accountManagers.size(), foundAccountManagers.size());
		verify(amRepoMock).findAll();
	}
	
	@Test
	void test_Save_ShouldAddAnAccountManager() {
		// Arrange
		when(amRepoMock.save(accountManager)).thenReturn(accountManager);
		// Act
		AccountManager returnedAccountManager = amService.save(accountManager);
		// Assert
		assertEquals(accountManager, returnedAccountManager);
		verify(amRepoMock).save(accountManager);
	}
	
	@Test
	void test_Update_ShouldUpdateAnAccountManager_WhenIdExists() {
		// Arrange
		when(amRepoMock.existsById(amId)).thenReturn(true);
		AccountManager updatedAccountManager = new AccountManager();
		updatedAccountManager.setLocation(FDMLocation.VIC_Melbourne);
		when(amRepoMock.save(updatedAccountManager)).thenReturn(updatedAccountManager);
		// Act
		AccountManager actualUpdatedAm = amService.update(amId, updatedAccountManager);
		// Assert
		assertEquals(updatedAccountManager.getLocation(), actualUpdatedAm.getLocation());
	}
	
	@Test
	void test_Save_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		String message = "Account manager with id " + invalidAmId + " does not exist.";
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> amService.update(invalidAmId, accountManager));
		// Assert
		assertEquals(message, actualExceptionThrown.getMessage());
		verify(amRepoMock).existsById(invalidAmId);
	}
	
	@Test
	void test_RemoveById_ShouldRemoveAnAccountManager_WhenIdFound() {
		// Arrange
		when(amRepoMock.existsById(amId)).thenReturn(true);
		// Act
		amService.removeById(amId);
		// Assert
		verify(amRepoMock).deleteById(amId);
	}
	
	@Test
	void test_RemoveById_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		String message = "Account manager with id " + invalidAmId + " does not exist.";
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> amService.removeById(invalidAmId));
		// Assert
		assertEquals(message, actualExceptionThrown.getMessage());
		verify(amRepoMock).existsById(invalidAmId);
	}

}