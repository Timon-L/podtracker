package com.fdmgroup.PodTracker.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PodTracker.model.AccountManager;
import com.fdmgroup.PodTracker.service.AccountManagerService;

@ExtendWith(MockitoExtension.class)
class AccountManagerControllerTest {

	@Mock
	private AccountManagerService amServiceMock;
	@InjectMocks
	private AccountManagerController amController;
	private AccountManager am;
	private long amId;

	@BeforeEach
	void setUp() throws Exception {
		amId = 2L;
		am = new AccountManager();
		am.setId(amId);
	}

	@Test
	void test_FindAll_ShouldReturnAllAccountManagers() {
		// Arrange
		List<AccountManager> expectedAMs = new ArrayList<>();
		expectedAMs.add(am);
		expectedAMs.add(new AccountManager());
		when(amServiceMock.findAll()).thenReturn(expectedAMs);
		// Act
		List<AccountManager> actualAMs = amController.findAll();
		// Assert
		assertNotNull(actualAMs);
		assertEquals(expectedAMs.size(), actualAMs.size());
		verify(amServiceMock).findAll();
	}

	@Test
	void test_FindById_ShouldReturnOneAccountManager_WhenIdProvided() {
		// Arrange
		when(amServiceMock.findById(amId)).thenReturn(am);
		// Act
		AccountManager actualAM = amController.findById(amId);
		// Assert
		assertEquals(am, actualAM);
		verify(amServiceMock).findById(amId);
	}

	@Test
	void test_Save_ShouldReturnOneAccountManager() {
		// Arrange
		when(amServiceMock.save(am)).thenReturn(am);
		// Act
		AccountManager actualAM = amController.save(am);
		// Assert
		assertEquals(am, actualAM);
		verify(amServiceMock).save(am);
	}

	@Test
	void test_Update_ShouldReturnOneUpdatedAccountManager_WhenIdProvided() {
		// Arrange
		when(amServiceMock.update(amId, am)).thenReturn(am);
		// Act
		AccountManager actualAM = amController.update(amId, am);
		// Assert
		assertEquals(am, actualAM);
		verify(amServiceMock).update(amId, am);
	}
	
	@Test
	void test_Delete_ShouldDeleteOneAccountManager_WhenIdProvided() {
		// Arrange
		// Act
		amController.delete(amId);
		// Assert
		verify(amServiceMock).removeById(amId);
	}

}
