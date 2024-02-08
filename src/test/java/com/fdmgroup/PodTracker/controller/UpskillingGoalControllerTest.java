package com.fdmgroup.PodTracker.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PodTracker.model.*;
import com.fdmgroup.PodTracker.service.UpskillingGoalService;

@ExtendWith(MockitoExtension.class)
class UpskillingGoalControllerTest {

	@Mock
	private UpskillingGoalService ugsMock;
	@InjectMocks
	private UpskillingGoalController ugc;
	private UpskillingGoal ug;
	private long goalId;
	private long skillId;
	
	@BeforeEach
	void setUp() throws Exception {
		goalId = 2L;
		skillId = 4L;
		ug = new UpskillingGoal();
	}

	@Test
	void test_FindAll_ShouldReturnAllUpskillingGoals() {
		// Arrange
		List<UpskillingGoal> expectedUGs = new ArrayList<>();
		expectedUGs.add(ug);
		expectedUGs.add(new UpskillingGoal());
		when(ugsMock.findAll()).thenReturn(expectedUGs);
		// Act
		List<UpskillingGoal> actualUGs = ugc.findAll();
		// Assert
		assertNotNull(actualUGs);
		assertEquals(expectedUGs, actualUGs);
		verify(ugsMock).findAll();
	}

	@Test
	void test_FindUpskillingGoalBySkillAndGoal_ShouldReturnOneUpskillingGoal_WhenGoalIdAndSkillIdProvided() {
		// Arrange
		when(ugsMock.findUpskillingGoalBySkillAndGoal(goalId, skillId)).thenReturn(ug);
		// Act
		UpskillingGoal actualUG = ugc.findUpskillingGoalBySkillAndGoal(goalId, skillId);
		// Assert
		assertEquals(ug, actualUG);
		verify(ugsMock).findUpskillingGoalBySkillAndGoal(goalId, skillId);
	}

	@Test
	void test_Save_ShouldReturnOneUpskillingGoal() {
		// Arrange
		when(ugsMock.save(ug)).thenReturn(ug);
		// Act
		UpskillingGoal actualUG = ugc.save(ug);
		// Assert
		assertEquals(ug, actualUG);
		verify(ugsMock).save(ug);
	}

	@Test
	void test_Update_ShouldReturnOneUpdatedUpskillingGoal() {
		// Arrange
		when(ugsMock.update(ug)).thenReturn(ug);
		// Act
		UpskillingGoal actualUG = ugc.update(ug);
		// Assert
		assertEquals(ug, actualUG);
		verify(ugsMock).update(ug);
	}
	
	@Test
	void test_Delete_ShouldDeleteOneUpskillingGoal_WhenGoalIdAndSkillIdProvided() {
		// Arrange
		when(ugsMock.findUpskillingGoalBySkillAndGoal(goalId, skillId)).thenReturn(ug);
		// Act
		ugc.delete(goalId, skillId);
		// Assert
		verify(ugsMock).findUpskillingGoalBySkillAndGoal(goalId, skillId);
		verify(ugsMock).delete(ug);
	}

}
