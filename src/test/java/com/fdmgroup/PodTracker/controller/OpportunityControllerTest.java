package com.fdmgroup.PodTracker.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PodTracker.model.*;
import com.fdmgroup.PodTracker.service.OpportunityService;

@ExtendWith(MockitoExtension.class)
class OpportunityControllerTest {
	
	@Mock
	private OpportunityService osMock;
	@InjectMocks
	private OpportunityController oc;
	private Opportunity opportunity;
	private long opportunityId;
	
	@BeforeEach
	void setUp() throws Exception {
		opportunityId = 2L;
		opportunity = new Opportunity();
		opportunity.setId(opportunityId);
	}

	@Test
	void test_FindAll_ShouldReturnAllOpportunities() {
		// Arrange
		List<Opportunity> expectedOpportunities = new ArrayList<>();
		expectedOpportunities.add(opportunity);
		expectedOpportunities.add(new Opportunity());
		when(osMock.findAll()).thenReturn(expectedOpportunities);
		// Act
		List<Opportunity> actualOpportunities = oc.findAll();
		// Assert
		assertNotNull(actualOpportunities);
		assertEquals(expectedOpportunities, actualOpportunities);
		verify(osMock).findAll();
	}

	@Test
	void test_FindById_ShouldReturnOneInterview_WhenIdProvided() {
		// Arrange
		when(osMock.findById(opportunityId)).thenReturn(opportunity);
		// Act
		Opportunity actualOpportunity = oc.findById(opportunityId);
		// Assert
		assertEquals(opportunity, actualOpportunity);
		verify(osMock).findById(opportunityId);
	}

	@Test
	void test_Save_ShouldReturnOneInterview() {
		// Arrange
		when(osMock.save(opportunity)).thenReturn(opportunity);
		// Act
		Opportunity actualOpportunity = oc.save(opportunity);
		// Assert
		assertEquals(opportunity, actualOpportunity);
		verify(osMock).save(opportunity);
	}

	@Test
	void test_Update_ShouldReturnOneUpdatedInterview_WhenIdProvided() {
		// Arrange
		when(osMock.update(opportunityId, opportunity)).thenReturn(opportunity);
		// Act
		Opportunity actualOpportunity = oc.update(opportunityId, opportunity);
		// Assert
		assertEquals(opportunity, actualOpportunity);
		verify(osMock).update(opportunityId, opportunity);
	}
	
	@Test
	void test_Delete_ShouldDeleteOneClient_WhenIdProvided() {
		// Arrange
		// Act
		oc.delete(opportunityId);
		// Assert
		verify(osMock).removeById(opportunityId);
	}

}
