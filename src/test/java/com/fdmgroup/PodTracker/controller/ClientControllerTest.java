package com.fdmgroup.PodTracker.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PodTracker.model.*;
import com.fdmgroup.PodTracker.service.ClientService;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {
	
	@Mock
	private ClientService clientServiceMock;
	@InjectMocks
	private ClientController clientController;
	private Client client;
	private long clientId;

	@BeforeEach
	void setUp() throws Exception {
		clientId = 2L;
		client = new Client();
		client.setId(clientId);
	}

	@Test
	void test_FindAll_ShouldReturnAllClients() {
		// Arrange
		List<Client> expectedClients = new ArrayList<>();
		expectedClients.add(client);
		expectedClients.add(new Client());
		when(clientServiceMock.findAll()).thenReturn(expectedClients);
		// Act
		List<Client> actualClients = clientController.findAll();
		// Assert
		assertNotNull(actualClients);
		assertEquals(expectedClients.size(), actualClients.size());
		verify(clientServiceMock).findAll();
	}

	@Test
	void test_FindById_ShouldReturnOneClient_WhenIdProvided() {
		// Arrange
		when(clientServiceMock.findById(clientId)).thenReturn(client);
		// Act
		Client actualClient = clientController.findById(clientId);
		// Assert
		assertEquals(client, actualClient);
		verify(clientServiceMock).findById(clientId);
	}

	@Test
	void test_Save_ShouldReturnOneClient() {
		// Arrange
		when(clientServiceMock.save(client)).thenReturn(client);
		// Act
		Client actualClient = clientController.save(client);
		// Assert
		assertEquals(client, actualClient);
		verify(clientServiceMock).save(client);
	}

	@Test
	void test_Update_ShouldReturnOneUpdatedClient_WhenIdProvided() {
		// Arrange
		when(clientServiceMock.update(clientId, client)).thenReturn(client);
		// Act
		Client actualClient = clientController.update(clientId, client);
		// Assert
		assertEquals(client, actualClient);
		verify(clientServiceMock).update(clientId, client);
	}
	
	@Test
	void test_Delete_ShouldDeleteOneClient_WhenIdProvided() {
		// Arrange
		// Act
		clientController.delete(clientId);
		// Assert
		verify(clientServiceMock).removeById(clientId);
	}

}
