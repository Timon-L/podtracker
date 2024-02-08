package com.fdmgroup.PodTracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PodTracker.dal.OpportunityRepository;
import com.fdmgroup.PodTracker.exceptions.NotFoundException;
import com.fdmgroup.PodTracker.model.*;

@ExtendWith(MockitoExtension.class)
class OpportunityServiceTest {
	
	@Mock
	private OpportunityRepository opportunityRepoMock;
	@InjectMocks
	private OpportunityService opportunityService;
	private Opportunity expectedOpportunity;
	private long opportunityId;
	private String expectedMessage;
	private long invalidOpportunityId;

	@BeforeEach
	void setUp() throws Exception {
		opportunityId = 2L;
		invalidOpportunityId = 5L;
		expectedMessage = "Opportunity with id " + invalidOpportunityId + " does not exist.";
		expectedOpportunity = new Opportunity();
		expectedOpportunity.setId(opportunityId);
	}

	@Test
	void test_FindById_ShouldReturnAnOpportunity_WhenIdFound() {
		// Arrange
		when(opportunityRepoMock.findById(opportunityId)).thenReturn(Optional.of(expectedOpportunity));
		// Act
		Opportunity actualOpportunityFound = opportunityService.findById(opportunityId);
		// Assert
		assertEquals(expectedOpportunity, actualOpportunityFound);
		verify(opportunityRepoMock).findById(opportunityId);
	}
	
	@Test
	void test_FindById_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> opportunityService.findById(invalidOpportunityId));
		// Assert
		assertEquals(expectedMessage, actualExceptionThrown.getMessage());
		verify(opportunityRepoMock).findById(invalidOpportunityId);
	}
	
	@Test
	void test_FindAll_ShouldFindAllOpportunities() {
		// Arrange
		Opportunity newOpportunity = new Opportunity();
		List<Opportunity> opportunities = new ArrayList<>();
		opportunities.add(newOpportunity);
		opportunities.add(expectedOpportunity);
		when(opportunityRepoMock.findAll()).thenReturn(opportunities);
		// Act
		List<Opportunity> actualFoundOpportunities = opportunityService.findAll();
		// Assert
		assertNotNull(actualFoundOpportunities);
		assertEquals(opportunities.size(), actualFoundOpportunities.size());
		verify(opportunityRepoMock).findAll();
	}
	
	@Test
	void test_Save_ShouldAddAnOpportunity() {
		// Arrange
		when(opportunityRepoMock.save(expectedOpportunity)).thenReturn(expectedOpportunity);
		// Act
		Opportunity actualReturnedOpportunity= opportunityService.save(expectedOpportunity);
		// Assert
		assertEquals(expectedOpportunity, actualReturnedOpportunity);
		verify(opportunityRepoMock).save(expectedOpportunity);
	}
	
	@Test
	void test_Update_ShouldUpdateAnOpportunity_WhenIdExists() {
		// Arrange
		when(opportunityRepoMock.existsById(opportunityId)).thenReturn(true);
		Opportunity expectedUpdatedOpportunity = new Opportunity();
		expectedUpdatedOpportunity.setStatus(OpportunityStatus.Fulfilled);
		when(opportunityRepoMock.save(expectedUpdatedOpportunity)).thenReturn(expectedUpdatedOpportunity);
		// Act
		Opportunity actualUpdatedOpportunity = opportunityService.update(opportunityId, expectedUpdatedOpportunity);
		// Assert
		assertEquals(expectedUpdatedOpportunity.getStatus(), actualUpdatedOpportunity.getStatus());
	}
	
	@Test
	void test_Update_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> opportunityService.update(invalidOpportunityId, expectedOpportunity));
		// Assert
		assertEquals(expectedMessage, actualExceptionThrown.getMessage());
		verify(opportunityRepoMock).existsById(invalidOpportunityId);
	}
	
	@Test
	void test_RemoveById_ShouldRemoveAnOpportunity_WhenIdFound() {
		// Arrange
		when(opportunityRepoMock.existsById(opportunityId)).thenReturn(true);
		// Act
		opportunityService.removeById(opportunityId);
		// Assert
		verify(opportunityRepoMock).deleteById(opportunityId);
	}
	
	@Test
	void test_RemoveById_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> opportunityService.removeById(invalidOpportunityId));
		// Assert
		assertEquals(expectedMessage, actualExceptionThrown.getMessage());
		verify(opportunityRepoMock).existsById(invalidOpportunityId);
	}

}
