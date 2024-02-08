package com.fdmgroup.PodTracker.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PodTracker.model.*;
import com.fdmgroup.PodTracker.service.OpportunityDiscussionService;

@ExtendWith(MockitoExtension.class)
class OpportunityDiscussionControllerTest {

	@Mock
	private OpportunityDiscussionService odsMock;
	@InjectMocks
	private OpportunityDiscussionController odc;
	private OpportunityDiscussion od;
	private long odId;
	
	@BeforeEach
	void setUp() throws Exception {
		odId = 2L;
		od = new OpportunityDiscussion();
		od.setId(odId);
	}

	@Test
	void test_FindAll_ShouldReturnAllOpportunities() {
		// Arrange
		List<OpportunityDiscussion> expectedODs = new ArrayList<>();
		expectedODs.add(od);
		expectedODs.add(new OpportunityDiscussion());
		when(odsMock.findAll()).thenReturn(expectedODs);
		// Act
		List<OpportunityDiscussion> actualODs = odc.findAll();
		// Assert
		assertNotNull(actualODs);
		assertEquals(expectedODs, actualODs);
		verify(odsMock).findAll();
	}

	@Test
	void test_FindById_ShouldReturnOneInterview_WhenIdProvided() {
		// Arrange
		when(odsMock.findById(odId)).thenReturn(od);
		// Act
		OpportunityDiscussion actualOD = odc.findById(odId);
		// Assert
		assertEquals(od, actualOD);
		verify(odsMock).findById(odId);
	}

	@Test
	void test_Save_ShouldReturnOneInterview() {
		// Arrange
		when(odsMock.save(od)).thenReturn(od);
		// Act
		OpportunityDiscussion actualOD = odc.save(od);
		// Assert
		assertEquals(od, actualOD);
		verify(odsMock).save(od);
	}

	@Test
	void test_Update_ShouldReturnOneUpdatedInterview_WhenIdProvided() {
		// Arrange
		when(odsMock.update(od)).thenReturn(od);
		// Act
		OpportunityDiscussion actualOD = odc.update(od);
		// Assert
		assertEquals(od, actualOD);
		verify(odsMock).update(od);
	}
	
	@Test
	void test_Delete_ShouldDeleteOneClient_WhenIdProvided() {
		// Arrange
		// Act
		odc.delete(odId);
		// Assert
		verify(odsMock).deleteById(odId);
	}

}
