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
class UpskillingGoalServiceTest {

	@Mock
	private UpskillingGoalRepository upskillingGoalRepoMock;
	@Mock
	private UpskillingRepository upskillingRepoMock;
	@Mock
	private GoalRepository goalRepoMock;

	@InjectMocks
	private UpskillingGoalService upskillingGoalService;
	private UpskillingGoal upskillingGoal;
	private List<UpskillingGoal> upskillingGoalList;
	private Goal goal;
	private Upskilling upskilling;

	@BeforeEach
	void setUp() throws Exception {
		this.upskillingGoal = new UpskillingGoal();
		this.goal = new Goal();
		this.goal.setId(2L);
		this.upskilling = new Upskilling();
		this.upskilling.setId(2L);
		this.upskillingGoal.setId(2L);
		this.upskillingGoal.setUpskilling(upskilling);
		this.upskillingGoal.setGoal(goal);
		this.upskillingGoal.setAllocationHours(4);
		this.upskillingGoalList = new ArrayList<>();
		this.upskillingGoalList.add(upskillingGoal);
	}

	@Test
	void test_Save_ShouldAddUpskillingGoal() {
		// Arrange
		when(upskillingGoalRepoMock.save(upskillingGoal)).thenReturn(upskillingGoal);
		// Act
		UpskillingGoal result = upskillingGoalService.save(upskillingGoal);
		// Assert
		assertNotNull(result);
		verify(upskillingGoalRepoMock, times(1)).save(upskillingGoal);
	}

	@Test
	void test_FindUpskillingGoalBySkillAndGoal_ShouldReturnUpskillingGoal_WhenGoalIdAndSkillIdFound() {
		// Arrange
		when(upskillingRepoMock.findById(2L)).thenReturn(Optional.of(upskilling));
		when(goalRepoMock.findById(2L)).thenReturn(Optional.of(goal));
		when(upskillingGoalRepoMock.findUpskillingGoalBySkillAndGoal(upskilling, goal)).thenReturn(upskillingGoalList);
		// Act
		UpskillingGoal result = upskillingGoalService.findUpskillingGoalBySkillAndGoal(2L, 2L);
		// Assert
		assertNotNull(upskillingGoalList.get(0));
		assertEquals(upskillingGoalList.get(0), result);
	}

	@Test
	void test_FindUpskillingGoalBySkillAndGoal_ShouldThrowNotFoundExcpetion_WhenGoalIdNotFound() {
		// Arrange
		long invalidGoalId = 4L;
		long skillId = 2L;
		String expectedExceptionMsg = "Unable to find goal with id " + invalidGoalId;
		Upskilling skill = new Upskilling();
		when(upskillingRepoMock.findById(skillId)).thenReturn(Optional.of(skill));
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class,
				() -> upskillingGoalService.findUpskillingGoalBySkillAndGoal(invalidGoalId, skillId ));
		// Assert
		assertEquals(expectedExceptionMsg, actualExceptionThrown.getMessage());
		verify(upskillingRepoMock).findById(skillId);
	}
	
	@Test 
	void test_FindUpskillingGoalBySkillAndGoal_ShouldThrowNotFoundExcpetion_WhenSkillIdNotFound() {
		// Arrange
		long invalidSkillId = 4L;
		String expectedExceptionMsg = "Unable to find upskilling with id " + invalidSkillId;
		long goalId = 2L;
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class,
				() -> upskillingGoalService.findUpskillingGoalBySkillAndGoal(goalId , invalidSkillId));
		// Assert
		assertEquals(expectedExceptionMsg, actualExceptionThrown.getMessage());
		verify(upskillingRepoMock).findById(invalidSkillId);
	}

	@Test
	void test_FindByUpskilling_ShouldReturnUpskillingGoalList_WhenUpskillingFound() {
		// Arrange
		when(upskillingGoalRepoMock.findByUpskilling(upskilling)).thenReturn(upskillingGoalList);
		// Act
		List<UpskillingGoal> result = upskillingGoalService.findByUpskilling(upskilling);
		// Assert
		assertEquals(upskillingGoalList, result);
		verify(upskillingGoalRepoMock, times(1)).findByUpskilling(upskilling);
	}

	@Test
	void test_FindByGoal_ShouldReturnUpskillingGoalList_WhenGoalFound() {
		// Arrange
		when(upskillingGoalRepoMock.findByGoal(goal)).thenReturn(upskillingGoalList);
		// Act
		List<UpskillingGoal> result = upskillingGoalService.findByGoal(goal);
		// Assert
		assertEquals(upskillingGoalList, result);
		verify(upskillingGoalRepoMock, times(1)).findByGoal(goal);
	}

	@Test
	void test_FindAll_ShouldReturnAllUpskillingGoal() {
		// Arrange
		UpskillingGoal upskillingGoal2 = new UpskillingGoal();
		upskillingGoalList.add(upskillingGoal2);
		when(upskillingGoalRepoMock.findAll()).thenReturn(upskillingGoalList);
		// Act
		List<UpskillingGoal> result = upskillingGoalService.findAll();
		// Assert
		assertNotNull(upskillingGoalList);
		assertEquals(upskillingGoalList.size(), result.size());
		verify(upskillingGoalRepoMock, times(1)).findAll();
	}
	
	@Test
	void test_Delete_ShouldDeleteUpskllingGoal_WhenIdFound() {
		// Arrange
		// Act
		upskillingGoalService.delete(upskillingGoal);
		// Assert
		verify(upskillingGoalRepoMock, times(1)).delete(upskillingGoal);
	}
	
	@Test
	void test_Update_ShouldUpdateUpskillingGoal_WhenIdFound() {
		// Arrange
		UpskillingGoal updatedUpskillingGoal = new UpskillingGoal();
		updatedUpskillingGoal.setAllocationHours(2);
		when(upskillingGoalRepoMock.save(updatedUpskillingGoal)).thenReturn(updatedUpskillingGoal);
		// Act
		UpskillingGoal result = upskillingGoalService.update(updatedUpskillingGoal);
		// Assert
		assertEquals(2, result.getAllocationHours());
	}

}
