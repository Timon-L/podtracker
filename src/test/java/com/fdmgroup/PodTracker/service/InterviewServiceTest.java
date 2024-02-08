package com.fdmgroup.PodTracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PodTracker.dal.InterviewRepository;
import com.fdmgroup.PodTracker.exceptions.NotFoundException;
import com.fdmgroup.PodTracker.model.Interview;

@ExtendWith(MockitoExtension.class)
class InterviewServiceTest {
	
	@Mock
	private InterviewRepository interviewRepoMock;
	
	@InjectMocks
	private InterviewService interviewService;
	private Interview expectedInterview;
	private long interview1Id;
	private String expectedMessage;
	private long invalidInterviewId;
	private String feedback;

	@BeforeEach
	void setUp() throws Exception {
		interview1Id = 2L;
		invalidInterviewId = 10L;
		expectedMessage = "Interview with id " + invalidInterviewId + " does not exist.";
		feedback = "answer the questions directly";
		
		expectedInterview = new Interview();
		expectedInterview.setId(interview1Id);
		expectedInterview.setFeedback(feedback);
	}

	@Test
	void test_FindById_ShouldReturnOneInterview_WhenIdFound() {
		// Arrange
		when(interviewRepoMock.findById(interview1Id)).thenReturn(Optional.of(expectedInterview));
		// Act
		Interview interviewFound = interviewService.findById(interview1Id);
		// Assert
		assertEquals(expectedInterview, interviewFound);
		verify(interviewRepoMock).findById(interview1Id);
	}
	
	@Test
	void test_FindById_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> interviewService.findById(invalidInterviewId));
		// Assert
		assertEquals(expectedMessage, actualExceptionThrown.getMessage());
		verify(interviewRepoMock).findById(invalidInterviewId);
	}
	
	@Test
	void test_FindAll_ShouldReturnAllInterviews() {
		// Arrange
		List<Interview> expectedInterviews = new ArrayList<>();
		expectedInterviews.add(expectedInterview);
		expectedInterviews.add(new Interview());
		when(interviewRepoMock.findAll()).thenReturn(expectedInterviews);
		// Act
		List<Interview> actualInterviews = interviewService.findAll();
		// Assert
		assertNotNull(actualInterviews);
		assertEquals(expectedInterviews.size(), actualInterviews.size());
		verify(interviewRepoMock).findAll();
	}
	
	@Test
	void test_Save_ShouldReturnOneInterview() {
		// Arrange
		when(interviewRepoMock.save(expectedInterview)).thenReturn(expectedInterview);
		// Act
		Interview actualInterview = interviewService.save(expectedInterview);
		// Assert
		assertEquals(expectedInterview, actualInterview);
		assertEquals(expectedInterview.getFeedback(), actualInterview.getFeedback());
		verify(interviewRepoMock).save(expectedInterview);
	}
	
	@Test
	void test_Update_ShouldReturnOneUpdatedInterview_WhenIdFound() {
		// Arrange
		String updatedFeedback = "work on problem solving skills";
		when(interviewRepoMock.existsById(interview1Id)).thenReturn(true);
		Interview expectedUpdatedInterview = new Interview();
		expectedUpdatedInterview.setId(interview1Id);
		expectedUpdatedInterview.setFeedback(updatedFeedback);
		when(interviewRepoMock.save(expectedUpdatedInterview)).thenReturn(expectedUpdatedInterview);
		// Act
		Interview actualUpdatedInterview = interviewService.update(interview1Id, expectedUpdatedInterview);
		// Assert
		assertEquals(expectedUpdatedInterview.getFeedback(), actualUpdatedInterview.getFeedback());
		verify(interviewRepoMock).save(expectedUpdatedInterview);
	}
	
	@Test
	void test_Update_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> interviewService.update(invalidInterviewId, expectedInterview));
		// Assert
		assertEquals(expectedMessage, actualExceptionThrown.getMessage());
		verify(interviewRepoMock).existsById(invalidInterviewId);
	}
	
	@Test
	void test_RemoveById_ShouldRemoveOneInterview_WhenIdFound() {
		// Arrange
		when(interviewRepoMock.existsById(interview1Id)).thenReturn(true);
		// Act
		interviewService.removeById(interview1Id);
		// Assert
		verify(interviewRepoMock).deleteById(interview1Id);
	}
	
	@Test
	void test_RemoveById_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> interviewService.removeById(invalidInterviewId));
		// Assert
		assertEquals(expectedMessage, actualExceptionThrown.getMessage());
		verify(interviewRepoMock).existsById(invalidInterviewId);
	}


}
