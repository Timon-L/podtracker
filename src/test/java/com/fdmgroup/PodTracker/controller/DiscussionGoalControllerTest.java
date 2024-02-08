package com.fdmgroup.PodTracker.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PodTracker.model.*;
import com.fdmgroup.PodTracker.service.DiscussionGoalService;

@ExtendWith(MockitoExtension.class)
class DiscussionGoalControllerTest {
	
	@Mock
	private DiscussionGoalService dgsMock;
	@InjectMocks
	private DiscussionGoalController dgc;
	private DiscussionGoal dg;
	private long goalId;
	private long discussionId;

	@BeforeEach
	void setUp() throws Exception {
		goalId = 2L;
		discussionId = 3L;
		dg = new DiscussionGoal();
	}

	@Test
	void test_FindAll_ShouldReturnAllDiscussionGoals() {
		// Arrange
		List<DiscussionGoal> expectedDGs = new ArrayList<>();
		expectedDGs.add(dg);
		expectedDGs.add(new DiscussionGoal());
		when(dgsMock.findAll()).thenReturn(expectedDGs);
		// Act
		List<DiscussionGoal> actualDGs = dgc.findAll();
		// Assert
		assertNotNull(actualDGs);
		assertEquals(expectedDGs.size(), actualDGs.size());
		verify(dgsMock).findAll();
	}

	@Test
	void test_findDiscussionGoalByDiscussionAndGoal_ShouldReturnOneDiscussionGoal_WhenGoalIdAndDiscussionIdProvided() {
		// Arrange
		when(dgsMock.findDiscussionGoalByDiscussionAndGoal(goalId, discussionId)).thenReturn(dg);
		// Act
		DiscussionGoal actualDg = dgc.findDiscussionGoalByDiscussionAndGoal(goalId, discussionId);
		// Assert
		assertEquals(dg, actualDg);
		verify(dgsMock).findDiscussionGoalByDiscussionAndGoal(goalId, discussionId);
	}

	@Test
	void test_Save_ShouldReturnOneDiscussionGoal() {
		// Arrange
		when(dgsMock.save(dg)).thenReturn(dg);
		// Act
		DiscussionGoal actualDg = dgc.save(dg);
		// Assert
		assertEquals(dg, actualDg);
		verify(dgsMock).save(dg);
	}

	@Test
	void test_Update_ShouldReturnOneUpdatedDiscussionGoal() {
		// Arrange
		when(dgsMock.update(dg)).thenReturn(dg);
		// Act
		DiscussionGoal actualDg = dgc.update(dg);
		// Assert
		assertEquals(dg, actualDg);
		verify(dgsMock).update(dg);
	}
	
	@Test
	void test_Delete_ShouldDeleteOneDiscussionGoal_WhenIdProvided() {
		// Arrange
		when(dgsMock.findDiscussionGoalByDiscussionAndGoal(goalId, discussionId)).thenReturn(dg);
		// Act
		dgc.delete(goalId, discussionId);
		// Assert
		verify(dgsMock).delete(dg);
	}

}
