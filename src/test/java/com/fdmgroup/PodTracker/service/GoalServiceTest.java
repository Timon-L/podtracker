package com.fdmgroup.PodTracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PodTracker.dal.GoalRepository;
import com.fdmgroup.PodTracker.exceptions.NotFoundException;
import com.fdmgroup.PodTracker.model.Goal;

@ExtendWith(MockitoExtension.class)
class GoalServiceTest {
	
	@Mock
	private GoalRepository goalRepoMock;
	
	@InjectMocks
	private GoalService	goalService;
	private Goal goal;
	private Goal newGoal;
	private Goal absentGoal1;
	private Goal absentGoal2;
	private List<Goal> goals;
	private List<Goal> absentGoals;
	private List<Goal> presentGoals;

	private long goalId;
	private long invalidGoalId;
	private String message;

	@BeforeEach
	void setUp() throws Exception {
		goalId = 2L;
		invalidGoalId = 5L;
		message = "No goals found";
		
		goal = new Goal();
		goal.setId(goalId);
		goal.setFeedback1("pending");
		newGoal = new Goal();
		newGoal.setId(goalId+1L);
		goals = new ArrayList<>();
		goals.add(goal);
		goals.add(newGoal);
		
		absentGoal1 = new Goal();
		absentGoal2 = new Goal();
		absentGoal1.setId(10L);
		absentGoal2.setId(11L);
		absentGoal1.setIsAbsence(true);
		absentGoal2.setIsAbsence(true);
		goals.add(absentGoal1);
		goals.add(absentGoal2);
		
		absentGoals = new ArrayList<>();
		absentGoals.add(absentGoal1);
		absentGoals.add(absentGoal2);
		presentGoals = new ArrayList<>();
		presentGoals.add(goal);
		presentGoals.add(newGoal);
	}

	@Test
	void test_FindById_ShouldReturnOneGoal_WhenIdFound() {
		// Arrange
		when(goalRepoMock.findById(goalId)).thenReturn(Optional.of(goal));
		// Act
		Goal goalFound = goalService.findById(goalId);
		// Assert
		assertEquals(goal, goalFound);
		verify(goalRepoMock).findById(goalId);
	}
	
	@Test
	void test_FindById_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		String messageExpected = "Unable to find goal with id " + invalidGoalId;
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> goalService.findById(invalidGoalId));
		// Assert
		assertEquals(messageExpected, actualExceptionThrown.getMessage());
		verify(goalRepoMock).findById(invalidGoalId);
	}
	
	@Test
	void test_FindAll_ShouldReturnAllGoals() {
		// Arrange
		when(goalRepoMock.findAll()).thenReturn(goals);
		// Act
		List<Goal> goalsFound = goalService.findAll();
		// Assert
		assertNotNull(goalsFound);
		assertEquals(goals.size(), goalsFound.size());
		verify(goalRepoMock).findAll();
	}
	
	@Test
	void test_FindAll_ShouldThrowNotFoundException_IfNoGoalsFound() {
		// Arrange
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> goalService.findAll());
		// Assert
		assertEquals(message, actualExceptionThrown.getMessage());
		verify(goalRepoMock).findAll();
	}
	
	@Test
	void test_FindAllAbsent_ShouldReturnAllAbsentGoals() {
		// Arrange
		when(goalRepoMock.findAll()).thenReturn(goals);
		// Act
		List<Goal> absentGoalsFound = goalService.findAllAbsent();
		// Assert
		assertNotNull(absentGoalsFound);
		assertEquals(absentGoals.size(), absentGoalsFound.size());	
		verify(goalRepoMock).findAll();
	}
	
	@Test
	void test_FindAllAbsent_ShouldThrowNotFoundException_IfNoGoalsFound() {
		// Arrange
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> goalService.findAllAbsent());
		// Assert
		assertEquals(message, actualExceptionThrown.getMessage());
		verify(goalRepoMock).findAll();
	}
	
	
	@Test 
	void test_FindAllPresent_ShouldReturnAllPresentGoals() {
		// Arrange
		when(goalRepoMock.findAll()).thenReturn(goals);
		// Act
		List<Goal> presentGoalsFound = goalService.findAllPresent();
		// Assert
		assertNotNull(presentGoalsFound);
		assertEquals(presentGoals.size(), presentGoalsFound.size());	
		verify(goalRepoMock).findAll();
	}
	
	@Test
	void test_FindAllPresent_ShouldThrowNotFoundException_IfNoGoalsFound() {
		// Arrange
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> goalService.findAllPresent());
		// Assert
		assertEquals(message, actualExceptionThrown.getMessage());
		verify(goalRepoMock).findAll();
	}
	
	@Test
	void test_Update_ShouldUpdateOneExistingGoal() {
		// Arrange
		Goal goalUpdated = new Goal();
		goalUpdated.setId(goalId);
		goalUpdated.setFeedback1("good work");
		when(goalRepoMock.save(goalUpdated)).thenReturn(goalUpdated);
		// Act
		Goal goalActual = goalService.update(goalUpdated);
		// Assert
		assertEquals(goalUpdated, goalActual);
		verify(goalRepoMock).save(goalUpdated);
	}
	
	@Test
	void test_Save_ShouldAddOneGoal() {
		// Arrange
		when(goalRepoMock.save(goal)).thenReturn(goal);
		// Act
		Goal goalAdded = goalService.save(goal);
		// Assert
		assertEquals(goal, goalAdded);
		verify(goalRepoMock).save(goal);
	}
	
	@Test
	void test_DeleteById_ShouldDeleteOneGoal_WhenIdFound() {
		// Arrange
		when(goalRepoMock.existsById(goalId)).thenReturn(true);
		// Act
		goalService.deleteById(goalId);
		// Assert
		verify(goalRepoMock).deleteById(goalId);
	}
	
	@Test
	void test_DeleteById_ShouldThrowNotFoundException_IfIdNotFound() {
		// Arrange
		String messageExpected = "Unable to find goal with id " + invalidGoalId;
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> goalService.deleteById(invalidGoalId));
		// Assert
		assertEquals(messageExpected, actualExceptionThrown.getMessage());
		verify(goalRepoMock).existsById(invalidGoalId);
	}
	

}