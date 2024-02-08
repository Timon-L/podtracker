package com.fdmgroup.PodTracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PodTracker.dal.OpportunityDiscussionRepository;
import com.fdmgroup.PodTracker.exceptions.NotFoundException;
import com.fdmgroup.PodTracker.model.*;

@ExtendWith(MockitoExtension.class)
class OpportunityDiscussionServiceTest {

	@Mock
	private OpportunityDiscussionRepository odrMock;

	@InjectMocks
	private OpportunityDiscussionService ods;
	private OpportunityDiscussion expectedOd;
	private long odId;

	private long invalidOdId;
	private String expectedMessage;

	@BeforeEach
	void setUp() throws Exception {
		odId = 2L;
		expectedMessage = "Opportunity discussion with id " + invalidOdId + " does not exist.";

		expectedOd = new OpportunityDiscussion();
		expectedOd.setId(odId);
	}

	@Test
	void test_FindById_ShouldReturnOneOpportunityDiscussion_WhenIdFound() {
		// Arrange
		when(odrMock.findById(odId)).thenReturn(Optional.of(expectedOd));
		// Act
		OpportunityDiscussion actualOd = ods.findById(odId);
		// Assert
		assertEquals(expectedOd, actualOd);
		verify(odrMock).findById(odId);
	}

	@Test
	void test_FindById_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class,
				() -> ods.findById(invalidOdId));
		// Assert
		assertEquals(expectedMessage, actualExceptionThrown.getMessage());
		verify(odrMock).findById(invalidOdId);
	}

	@Test
	void test_FindAll_ShouldReturnAllOpportunityDiscussions() {
		// Arrange
		List<OpportunityDiscussion> expectedOds = new ArrayList<>();
		expectedOds.add(expectedOd);
		expectedOds.add(new OpportunityDiscussion());
		when(odrMock.findAll()).thenReturn(expectedOds);
		// Act
		List<OpportunityDiscussion> actualOds = ods.findAll();
		// Assert
		assertNotNull(actualOds);
		assertEquals(expectedOds.size(), actualOds.size());
		verify(odrMock).findAll();
	}

	@Test
	void test_Save_ShouldReturnOneInterview() {
		// Arrange
		when(odrMock.save(expectedOd)).thenReturn(expectedOd);
		// Act
		OpportunityDiscussion actualOd = ods.save(expectedOd);
		// Assert
		assertEquals(expectedOd, actualOd);
		verify(odrMock).save(expectedOd);
	}

	@Test
	void test_Update_ShouldReturnOneUpdatedOpportunityDiscussion() {
		// Arrange
		OpportunityDiscussion expectedUpdatedOd = new OpportunityDiscussion();
		Opportunity opportunity = new Opportunity();
		expectedUpdatedOd.setOpportunity(opportunity);
		when(odrMock.save(expectedUpdatedOd)).thenReturn(expectedUpdatedOd);
		// Act
		OpportunityDiscussion actualUpdatedOd = ods.update(expectedUpdatedOd);
		// Assert
		assertEquals(expectedUpdatedOd, actualUpdatedOd);
		verify(odrMock).save(expectedUpdatedOd);
	}

	@Test
	void test_DeleteById_ShouldRemoveOneOpportunityDiscussion_WhenIdFound() {
		// Arrange
		when(odrMock.existsById(odId)).thenReturn(true);
		// Act
		ods.deleteById(odId);
		// Assert
		verify(odrMock).deleteById(odId);
	}

	@Test
	void test_DeleteById_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> ods.deleteById(invalidOdId));
		// Assert
		assertEquals(expectedMessage, actualExceptionThrown.getMessage());
		verify(odrMock).existsById(invalidOdId);
	}

}
