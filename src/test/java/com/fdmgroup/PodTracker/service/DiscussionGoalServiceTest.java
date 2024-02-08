package com.fdmgroup.PodTracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PodTracker.dal.*;
import com.fdmgroup.PodTracker.exceptions.NotFoundException;
import com.fdmgroup.PodTracker.model.*;

@ExtendWith(MockitoExtension.class)
class DiscussionGoalServiceTest {
	
	@Mock
	private DiscussionGoalRepository dgRepoMock;
	@Mock
	private OpportunityDiscussionRepository odRepoMock;
	@Mock
	private GoalRepository goalRepoMock;
	
	@InjectMocks
	private DiscussionGoalService dgService;
	private List<DiscussionGoal> discussionGoals;
	private DiscussionGoal discussionGoal;
	private OpportunityDiscussion discussion;
	private Goal goal;
	private Long dgId;
	private Long odId;
	private Long goalId;
	private Long invalidOdId;
	private Long invalidGoalId;

	@BeforeEach
	void setUp() throws Exception {
		this.dgId = 2L;
		this.odId = 3L;
		this.goalId = 4L;
		this.invalidOdId = 33L;
		this.invalidGoalId = 44L;
		
		this.goal = new Goal();
		this.goal.setId(goalId);
		this.discussion = new OpportunityDiscussion();
		this.discussion.setId(odId);
		
		this.discussionGoal = new DiscussionGoal();
		this.discussionGoal.setId(dgId);
		this.discussionGoal.setDiscussion(discussion);
		this.discussionGoal.setGoal(goal);
		this.discussionGoal.setAllocationHours(5);
		this.discussionGoals = new ArrayList<>();
		this.discussionGoals.add(discussionGoal);
	}

	@Test
	void test_Save_ShouldAddDiscussionGoal() {
		// Arrange
		when(dgRepoMock.save(discussionGoal)).thenReturn(discussionGoal);
		// Act
		DiscussionGoal dgSaved = dgService.save(discussionGoal);
		// Assert
		assertEquals(discussionGoal, dgSaved);
		verify(dgRepoMock).save(discussionGoal);
	}
	
	@Test
	void test_FindDiscussionGoalByDiscussionAndGoal_ShouldReturnOneDiscussionGoal_WhenOpportunityDiscussionIdAndGoalIdFound() {
		// Arrange
		when(odRepoMock.findById(odId)).thenReturn(Optional.of(discussion));
		when(goalRepoMock.findById(goalId)).thenReturn(Optional.of(goal));
		when(dgRepoMock.findDiscussionGoalByDiscussionAndGoal(discussion, goal))
			.thenReturn(discussionGoals);
		DiscussionGoal dgExpected = discussionGoals.get(0);
		// Act
		DiscussionGoal dgFound = dgService.findDiscussionGoalByDiscussionAndGoal(goalId, odId);
		// Assert
		assertEquals(dgExpected, dgFound);
		verify(dgRepoMock).findDiscussionGoalByDiscussionAndGoal(discussion, goal);
	}
	
	@Test
	void test_FindDiscussionGoalByDiscussionAndGoal_ShouldThrowNotFoundException_WhenOpportunityDiscussionIdNotFound() {
		// Arrange
		String message = "Unable to find discussion with id " + invalidOdId;
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> dgService.findDiscussionGoalByDiscussionAndGoal(goalId, invalidOdId));
		// Assert
		assertEquals(message, actualExceptionThrown.getMessage());
		verify(odRepoMock).findById(invalidOdId);
	}
	
	@Test
	void test_FindDiscussionGoalByDiscussionAndGoal_ShouldThrowNotFoundException_WhenGoalIdNotFound() {
		// Arrange
		String message = "Unable to find goal with id " + invalidGoalId;
		when(odRepoMock.findById(odId)).thenReturn(Optional.of(discussion));
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> dgService.findDiscussionGoalByDiscussionAndGoal(invalidGoalId, odId));
		// Assert
		assertEquals(message, actualExceptionThrown.getMessage());
		verify(goalRepoMock).findById(invalidGoalId); 
	}
	
	@Test
	void test_FindAll_ShouldReturnAllDiscussionGoals() {
		// Arrange
		discussionGoals.add(new DiscussionGoal());
		when(dgRepoMock.findAll()).thenReturn(discussionGoals);
		// Act
		List<DiscussionGoal> foundDiscussionGoals = dgService.findAll();
		// Assert
		assertNotNull(discussionGoals);
		assertEquals(discussionGoals.size(), foundDiscussionGoals.size());
		verify(dgRepoMock).findAll();
	}
	
	@Test
	void test_FindByDiscussion_ShouldReturnDiscussionGoalsAssociatedWithOneOpportunityDiscussion_WhenOpportunityDiscussionExists() {
		// Arrange
		when(dgRepoMock.findByDiscussion(discussion)).thenReturn(discussionGoals);
		// Act
		List<DiscussionGoal> foundDiscussionGoals = dgService.findByDisccussion(discussion);
		// Assert
		assertEquals(discussionGoals, foundDiscussionGoals);
		verify(dgRepoMock).findByDiscussion(discussion);
	}
	
	@Test
	void test_FindByGoal_ShouldReturnDiscussionGoalsAssociatedWithOneGoal_WhenGoalExists() {
		// Arrange
		when(dgRepoMock.findByGoal(goal)).thenReturn(discussionGoals);
		// Act
		List<DiscussionGoal> foundDiscussionGoals = dgService.findByGoal(goal);
		// Assert
		assertEquals(discussionGoals, foundDiscussionGoals);
		verify(dgRepoMock).findByGoal(goal);
	}
	
	@Test
	void test_Delete_ShouldDeleteOneDiscussionGoal() {
		// Arrange
		// Act
		dgService.delete(discussionGoal);
		// Assert
		verify(dgRepoMock).delete(discussionGoal);
	}
	
	@Test
	void test_Update_ShouldUpdateOneDiscussionGoal() {
		// Arrange
		when(dgRepoMock.save(discussionGoal)).thenReturn(discussionGoal);
		// Act
		DiscussionGoal dgUpdated = dgService.update(discussionGoal);
		// Assert
		assertEquals(discussionGoal, dgUpdated);
	}

}
