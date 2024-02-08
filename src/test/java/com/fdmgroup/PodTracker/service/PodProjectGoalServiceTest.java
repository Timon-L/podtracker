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
class PodProjectGoalServiceTest {
	
	@Mock
	private PodProjectGoalRepository ppgr;
	@Mock
	private PodProjectRepository ppr;
	@Mock
	private GoalRepository gr;
	@InjectMocks
	private PodProjectGoalService ppgs;
	private PodProjectGoal ppg;
	private PodProject podProject;
	private long podProjectId = 2L;
	private long goalId = 3L;
	private Goal goal;
	private List<PodProjectGoal> expectedPpgList;

	@BeforeEach
	void setUp() throws Exception {
		ppg = new PodProjectGoal();
		podProject = new PodProject();
		podProject.setProjectId(podProjectId);
		goal = new Goal();
		goal.setId(goalId);
		expectedPpgList = new ArrayList<>();
	}

	@Test
	void test_Save_ShouldSaveOnePodProjectGoal() {
		// Arrange
		when(ppgr.save(ppg)).thenReturn(ppg);
		// Act
		PodProjectGoal actualPpg = ppgs.save(ppg);
		// Assert
		assertEquals(ppg, actualPpg);
		verify(ppgr).save(ppg);
	}
	
	@Test
	void test_FindPodProjectGoalByProjectAndGoal_ShouldReturnOnePodProjectGoal_WhenGoalIdAndPodProjectIdFound() {
		// Arrange
		expectedPpgList.add(ppg);
		when(ppr.findById(podProjectId)).thenReturn(Optional.of(podProject));
		when(gr.findById(goalId)).thenReturn(Optional.of(goal));
		when(ppgr.findPodProjectGoalByProjectAndGoal(podProject, goal)).thenReturn(expectedPpgList);
		PodProjectGoal expectedPpg = expectedPpgList.get(0);
		// Act
		PodProjectGoal actualPpg = ppgs.findPodProjectGoalByProjectAndGoal(goalId, podProjectId);
		// Assert
		assertEquals(expectedPpg, actualPpg);
		verify(ppgr).findPodProjectGoalByProjectAndGoal(podProject, goal);
	}
	
	@Test
	void test_FindPodProjectGoalByProjectAndGoal_ShouldThrowNotFoundException_WhenGoalIdNotFound() {
		// Arrange
		long invalidGoalId = 20L;
		String expectedMessage = "Unable to find goal with id " + invalidGoalId;
		when(ppr.findById(podProjectId)).thenReturn(Optional.of(podProject));
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> ppgs.findPodProjectGoalByProjectAndGoal(invalidGoalId, podProjectId));
		// Assert
		assertEquals(expectedMessage, actualExceptionThrown.getMessage());
		verify(gr).findById(invalidGoalId);
	}
	
	@Test
	void test_FindPodProjectGoalByProjectAndGoal_ShouldThrowNotFoundException_WhenPodProjectIdNotFound() {
		// Arrange
		long invalidPodProjectId = 20L;
		String expectedMessage = "Unable to find project with id " + invalidPodProjectId;
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> ppgs.findPodProjectGoalByProjectAndGoal(goalId, invalidPodProjectId));
		// Assert
		assertEquals(expectedMessage, actualExceptionThrown.getMessage());
		verify(ppr).findById(invalidPodProjectId);
	}
	
	@Test
	void test_FindAll_ShouldReturnAllPodProjectGoals() { 
		// Arrange
		expectedPpgList.add(ppg);
		expectedPpgList.add(new PodProjectGoal());
		when(ppgr.findAll()).thenReturn(expectedPpgList);
		// Act
		List<PodProjectGoal> actualPpgList = ppgs.findAll();
		// Assert
		assertNotNull(actualPpgList);
		assertEquals(expectedPpgList.size(), actualPpgList.size());
		verify(ppgr).findAll();
	}
	
	@Test
	void test_FindByGoal_ShouldReturnAllPodProjectGoalsAssociatedWithOneGoal() {
		// Arrange
		when(ppgr.findByGoal(goal)).thenReturn(expectedPpgList);
		// Act
		List<PodProjectGoal> actualPpgList = ppgs.findByGoal(goal);
		// Assert
		assertNotNull(actualPpgList);
		assertEquals(expectedPpgList, actualPpgList);
		verify(ppgr).findByGoal(goal);
	}
	
	@Test
	void test_FindByPodProject_ShouldReturnAllPodProjectGoalsAssociatedWithOnePodProject() {
		// Arrange
		when(ppgr.findByPodProject(podProject)).thenReturn(expectedPpgList);
		// Act
		List<PodProjectGoal> actualPpgList = ppgs.findByPodProject(podProject);
		// Assert
		assertNotNull(actualPpgList);
		assertEquals(expectedPpgList, actualPpgList);
		verify(ppgr).findByPodProject(podProject);
	}
	
	@Test
	void test_Delete_ShouldDeleteOnePodProjectGoal() {
		// Arrange
		// Act
		ppgs.delete(ppg);
		// Assert
		verify(ppgr).delete(ppg);
	}
	
	@Test
	void test_Update_ShouldUpdateOnePodProjectGoal() {
		// Arrange
		PodProjectGoal expectedUpdatedPpg = new PodProjectGoal();
		Goal newGoal = new Goal();
		expectedUpdatedPpg.setGoal(newGoal);
		when(ppgr.save(expectedUpdatedPpg)).thenReturn(expectedUpdatedPpg);
		// Act
		PodProjectGoal actualUpdatedPpg = ppgs.update(expectedUpdatedPpg);
		// Assert
		assertEquals(expectedUpdatedPpg, actualUpdatedPpg);
		verify(ppgr).save(expectedUpdatedPpg);
	}

}
