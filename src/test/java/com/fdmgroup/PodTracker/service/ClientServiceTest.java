package com.fdmgroup.PodTracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PodTracker.dal.ClientRepository;
import com.fdmgroup.PodTracker.exceptions.NotFoundException;
import com.fdmgroup.PodTracker.model.Client;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
	
	@Mock
	private ClientRepository clientRepoMock;
	
	@InjectMocks
	private ClientService clientService;
	private Client client;
	private long clientId;
	private Long invalidClientId;

	@BeforeEach
	void setUp() throws Exception {
		this.clientId = 2L;
		this.client = new Client();
		this.client.setId(clientId);
		this.client.setName("NAB");
		this.invalidClientId = 5L;
	}

	@Test
	void test_FindById_ShouldReturnsAClient_WhenIdFound() {
		// Arrange
		when(clientRepoMock.findById(clientId)).thenReturn(Optional.of(this.client));
		// Act
		Client foundClient = clientService.findById(clientId);
		// Assert
		assertEquals(client, foundClient);
		verify(clientRepoMock).findById(clientId);
	}
	
	@Test
	void test_FindById_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		String message = "Unable to find client with Id " + invalidClientId;
		// Act
		Exception expectedExceptionThrown = assertThrows(NotFoundException.class, () -> clientService.findById(invalidClientId));		
		// Assert
		assertEquals(message, expectedExceptionThrown.getMessage());
		verify(clientRepoMock).findById(invalidClientId);
	}
	
	@Test
	void test_FindAll_ShouldReturnAllClients() {
		// Arrange
		List<Client> clients = new ArrayList<>();
		clients.add(client);
		clients.add(new Client());
		when(clientRepoMock.findAll()).thenReturn(clients);
		// Act
		List<Client> foundClients = clientService.findAll();
		// Assert
		assertNotNull(clients);
		assertEquals(clients.size(), foundClients.size());
		verify(clientRepoMock).findAll();
	}
	
	@Test
	void test_Save_ShouldAddOneClient() {
		// Arrange
		when(clientRepoMock.save(client)).thenReturn(client);
		// Act
		Client savedClient = clientService.save(client);
		// Assert
		assertEquals(client, savedClient);
		verify(clientRepoMock).save(client);
	}
	
	@Test
	void test_Update_ShouldUpdateClient_WhenIdFound() {
		// Arrange
		Client updatedClient = new Client();
		updatedClient.setName("CBA");
		when(clientRepoMock.existsById(clientId)).thenReturn(true);
		when(clientRepoMock.save(updatedClient)).thenReturn(updatedClient);
		// Act
		Client actualUpdatedClient = clientService.update(clientId, updatedClient);
		// Assert
		assertEquals(updatedClient.getName(), actualUpdatedClient.getName());
		verify(clientRepoMock).existsById(clientId);
		verify(clientRepoMock).save(updatedClient);
	}
	
	@Test
	void test_Update_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		String message = "Client with id " + invalidClientId + " does not exist.";
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> clientService.update(invalidClientId, client));
		// Assert
		assertEquals(message, actualExceptionThrown.getMessage());
		verify(clientRepoMock).existsById(invalidClientId);
	}
	
	@Test
	void test_removeById_ShouldRemoveOneClient_WhenIdFound() {
		// Arrange
		when(clientRepoMock.existsById(clientId)).thenReturn(true);
		// Act
		clientService.removeById(clientId);
		// Assert
		verify(clientRepoMock).deleteById(clientId);
	}
	
	@Test
	void test_removeById_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		String message = "Client with id " + invalidClientId + " does not exist.";
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> clientService.removeById(invalidClientId));
		// Assert
		assertEquals(message, actualExceptionThrown.getMessage());
		verify(clientRepoMock).existsById(invalidClientId);
	}

}
