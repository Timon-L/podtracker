package com.fdmgroup.PodTracker.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PodTracker.model.*;
import com.fdmgroup.PodTracker.service.*;

@ExtendWith(MockitoExtension.class)
class GoalControllerTest {
	
	@Mock
	private GoalService gsMock;
	@Mock
	private PodProjectGoalService ppgsMock;
	@InjectMocks
	private GoalController goalController;
	private Goal goal;
	private long goalId;
	
	@BeforeEach
	void setUp() throws Exception {
		goalId = 2L;
		goal = new Goal();
		goal.setId(goalId);
	}

	@Test
	void test_FindAll_ShouldReturnAllGoals() {
		// Arrange
		List<Goal> expectedGoals = new ArrayList<>();
		expectedGoals.add(goal);
		expectedGoals.add(new Goal());
		when(gsMock.findAll()).thenReturn(expectedGoals);
		// Act
		List<Goal> actualGoals = goalController.findAll();
		// Assert
		assertNotNull(actualGoals);
		assertEquals(expectedGoals.size(), actualGoals.size());
		verify(gsMock).findAll();
	}
	
	@Test
	void test_FindAllAbsent_ShouldReturnAllGoalsWithAbsentCondition() {
		// Arrange
		List<Goal> expectedAbsentGoals = new ArrayList<>();
		goal.setIsAbsence(true);
		expectedAbsentGoals.add(goal);
		when(gsMock.findAllAbsent()).thenReturn(expectedAbsentGoals);
		// Act
		List<Goal> actualAbsentGoals = goalController.findAllAbsent();
		// Assert
		assertNotNull(actualAbsentGoals);
		assertEquals(expectedAbsentGoals.size(), actualAbsentGoals.size());
		verify(gsMock).findAllAbsent();
	}
	
	@Test
	void test_FindAllPresent_ShouldReturnAllGoalsWithoutAbsentCondition() {
		// Arrange
		List<Goal> expectedPresentGoals = new ArrayList<>();
		goal.setIsAbsence(false);
		Goal presentGoal = new Goal();
		presentGoal.setIsAbsence(false);
		expectedPresentGoals.add(goal);
		expectedPresentGoals.add(presentGoal);
		when(gsMock.findAllPresent()).thenReturn(expectedPresentGoals);
		// Act
		List<Goal> actualPresentGoals = goalController.findAllPresent();
		// Assert
		assertNotNull(actualPresentGoals);
		assertEquals(expectedPresentGoals.size(), actualPresentGoals.size());
		verify(gsMock).findAllPresent();
	}

	@Test
	void test_FindById_ShouldReturnOneGoal_WhenIdProvided() {
		// Arrange
		when(gsMock.findById(goalId)).thenReturn(goal);
		// Act
		Goal actualClient = goalController.findById(goalId);
		// Assert
		assertEquals(goal, actualClient);
		verify(gsMock).findById(goalId);
	}
	
	@Test
	void test_FindAllPodProjects_ShouldReturnAllPodProjectAssociatedWithGoalId() {
		// Arrange
		List<PodProjectGoal> ppgList = new ArrayList<>();
		ppgList.add(new PodProjectGoal());
		ppgList.add(new PodProjectGoal());
		when(gsMock.findById(goalId)).thenReturn(goal);
		when(ppgsMock.findByGoal(goal)).thenReturn(ppgList);
		// Act
		List<PodProjectGoal> actualPpgList = goalController.findAllPodProjects(goalId);
		// Assert
		assertNotNull(actualPpgList);
		assertEquals(ppgList.size(), actualPpgList.size());
		verify(gsMock).findById(goalId);
		verify(ppgsMock).findByGoal(goal);
	}

	@Test
	void test_Save_ShouldReturnOneGoal() {
		// Arrange
		when(gsMock.save(goal)).thenReturn(goal);
		// Act
		Goal actualGoal = goalController.save(goal);
		// Assert
		assertEquals(goal, actualGoal);
		verify(gsMock).save(goal);
	}

	@Test
	void test_Update_ShouldReturnOneUpdatedGoal() {
		when(gsMock.update(goal)).thenReturn(goal);
		// Act
		Goal actualGoal = goalController.update(goal);
		// Assert
		assertEquals(goal, actualGoal);
		verify(gsMock).update(goal);
	}
	
	@Test
	void test_Delete_ShouldDeleteOneGoal_WhenIdProvided() {
		// Arrange
		// Act
		goalController.delete(goalId);
		// Assert
		verify(gsMock).deleteById(goalId);;
	}

}
