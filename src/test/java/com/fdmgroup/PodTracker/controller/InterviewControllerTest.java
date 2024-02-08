package com.fdmgroup.PodTracker.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PodTracker.model.*;
import com.fdmgroup.PodTracker.service.InterviewService;

@ExtendWith(MockitoExtension.class)
class InterviewControllerTest {
	
	@Mock
	private InterviewService isMock;
	@InjectMocks
	private InterviewController interviewController;
	private Interview interview;
	private long interviewId;

	@BeforeEach
	void setUp() throws Exception {
		interviewId = 2L;
		interview = new Interview();
		interview.setId(interviewId);
	}

	@Test
	void test_FindAll_ShouldReturnAllInterviews() {
		// Arrange
		List<Interview> expectedInterviews = new ArrayList<>();
		expectedInterviews.add(interview);
		expectedInterviews.add(new Interview());
		when(isMock.findAll()).thenReturn(expectedInterviews);
		// Act
		List<Interview> actualClients = interviewController.findAll();
		// Assert
		assertNotNull(actualClients);
		assertEquals(expectedInterviews, actualClients);
		verify(isMock).findAll();
	}

	@Test
	void test_FindById_ShouldReturnOneInterview_WhenIdProvided() {
		// Arrange
		when(isMock.findById(interviewId)).thenReturn(interview);
		// Act
		Interview actualInterview = interviewController.findById(interviewId);
		// Assert
		assertEquals(interview, actualInterview);
		verify(isMock).findById(interviewId);
	}

	@Test
	void test_Save_ShouldReturnOneInterview() {
		// Arrange
		when(isMock.save(interview)).thenReturn(interview);
		// Act
		Interview actualInterview = interviewController.save(interview);
		// Assert
		assertEquals(interview, actualInterview);
		verify(isMock).save(interview);
	}

	@Test
	void test_Update_ShouldReturnOneUpdatedInterview_WhenIdProvided() {
		// Arrange
		when(isMock.update(interviewId, interview)).thenReturn(interview);
		// Act
		Interview actualInterview = interviewController.update(interviewId, interview);
		// Assert
		assertEquals(interview, actualInterview);
		verify(isMock).update(interviewId, interview);
	}
	
	@Test
	void test_Delete_ShouldDeleteOneClient_WhenIdProvided() {
		// Arrange
		// Act
		interviewController.delete(interviewId);
		// Assert
		verify(isMock).removeById(interviewId);
	}


}
