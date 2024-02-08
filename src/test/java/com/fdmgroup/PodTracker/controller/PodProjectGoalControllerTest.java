package com.fdmgroup.PodTracker.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PodTracker.model.PodProjectGoal;
import com.fdmgroup.PodTracker.service.PodProjectGoalService;

@ExtendWith(MockitoExtension.class)
class PodProjectGoalControllerTest {
	
	@Mock
	private PodProjectGoalService ppgsMock;
	@InjectMocks
	private PodProjectGoalController ppgc;
	private PodProjectGoal ppg;
	private long goalId;
	private long podProjectId;

	@BeforeEach
	void setUp() throws Exception {
		goalId = 2L;
		podProjectId = 4L;
		ppg = new PodProjectGoal();
	}
	
	@Test
	void test_findUpskillingGoalBySkillAndGoal_ShouldReturnOnePodProjectGoal_WhenGoalIdAndPodProjectIdProvided() {
		// Arrange
		when(ppgsMock.findPodProjectGoalByProjectAndGoal(goalId, podProjectId)).thenReturn(ppg);
		// Act
		PodProjectGoal actualPpg = ppgc.findUpskillingGoalBySkillAndGoal(goalId, podProjectId);
		// Assert
		assertEquals(ppg, actualPpg);
		verify(ppgsMock).findPodProjectGoalByProjectAndGoal(goalId, podProjectId);
	}

	@Test
	void test_FindAll_ShouldReturnAllPodProjectGoals() {
		// Arrange
		List<PodProjectGoal> expectedPPGs = new ArrayList<>();
		expectedPPGs.add(ppg);
		expectedPPGs.add(new PodProjectGoal());
		when(ppgsMock.findAll()).thenReturn(expectedPPGs);
		// Act
		List<PodProjectGoal> actualPods = ppgc.findAll();
		// Assert
		assertNotNull(actualPods);
		assertEquals(expectedPPGs, actualPods);
		verify(ppgsMock).findAll();
	}

	@Test
	void test_Save_ShouldReturnOnePodProjectGoal() {
		// Arrange
		when(ppgsMock.save(ppg)).thenReturn(ppg);
		// Act
		PodProjectGoal actualPpg = ppgc.save(ppg);
		// Assert
		assertEquals(ppg, actualPpg);
		verify(ppgsMock).save(ppg);
	}

	@Test
	void test_Update_ShouldReturnOneUpdatedPodProject() {
		// Arrange
		when(ppgsMock.update(ppg)).thenReturn(ppg);
		// Act
		PodProjectGoal actualPpg = ppgc.update(ppg);
		// Assert
		assertEquals(ppg, actualPpg);
		verify(ppgsMock).update(ppg);
	}
	
	@Test
	void test_Delete_ShouldDeleteOnePodProjectGoal_WhenGoalIdAndPodProjectIdProvided() {
		// Arrange
		when(ppgsMock.findPodProjectGoalByProjectAndGoal(goalId, podProjectId)).thenReturn(ppg);
		// Act
		ppgc.delete(goalId, podProjectId);
		// Assert
		verify(ppgsMock).delete(ppg);
	}

}
