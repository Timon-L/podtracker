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
class PodServiceTest {
	
	@Mock
	private PodRepository podRepoMock;
	@Mock
	private UserRepository userRepoMock;
	
	@InjectMocks
	private PodService podService;
	private Pod pod;
	private Trainer primaryTrainer;
	private Trainer secondaryTrainer;
	private List<Trainee> trainees;
	private Trainee trainee;
	private PodProject project;
	private Long podId;
	private Long invalidPodId;
	private Long primaryTrainerId;
	private Long secondaryTrainerId;
	private Long traineeId;

	@BeforeEach
	void setUp() throws Exception {
		this.primaryTrainerId = 1L;
		this.secondaryTrainerId = 2L;
		this.traineeId = 3L;
		this.primaryTrainer = new Trainer();
		this.primaryTrainer.setUserId(primaryTrainerId);
		this.secondaryTrainer = new Trainer();
		this.secondaryTrainer.setUserId(secondaryTrainerId);
		this.trainees = new ArrayList<Trainee>();
		this.trainee = new Trainee();
		this.trainee.setUserId(traineeId);
		this.trainees.add(trainee);
		this.project = new PodProject();
		
		this.podId = 2L;
		this.invalidPodId = 5L;
		this.pod = new Pod();
		this.pod.setPodId(podId);
		this.pod.setPodName("pod001");
		this.pod.setPrimaryTrainerId(primaryTrainer);
		this.pod.setSecondaryTrainerId(secondaryTrainer);
		this.pod.setProject(project);
		this.pod.setTrainees(trainees);
		this.pod.setCapacity(10);
	}

	@Test
	void test_FindPodById_ShouldReturnPod_WhenIdFound() {
		// Arrange
		when(podRepoMock.findById(podId)).thenReturn(Optional.of(pod));
		// Act
		Pod result = podService.findPodById(podId);
		// Assert
		assertEquals(pod, result);
		verify(podRepoMock).findById(podId);
	}
	
	@Test
	void test_FindPodById_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		String message = "Unable to find Pod with ID " + invalidPodId;
		// Act
		NotFoundException result = assertThrows(NotFoundException.class, () -> podService.findPodById(invalidPodId));
		// Assert
		assertEquals(message, result.getMessage());
		verify(podRepoMock).findById(invalidPodId);
	}
	
	@Test
	void test_FindAll_ShouldReturnAllPods() {
		// Arrange
		List<Pod> pods = new ArrayList<>();
		Pod pod2 = new Pod();
		pods.add(pod);
		pods.add(pod2);
		when(podRepoMock.findAll()).thenReturn(pods);
		// Act
		List<Pod> result = podService.findAll();
		// Assert
		assertNotNull(result);
		assertEquals(2, result.size());
		verify(podRepoMock).findAll();
	}
	
	@Test
	void test_Save_ShouldAddPod() {
		// Arrange
		when(podRepoMock.save(pod)).thenReturn(pod);
		// Act
		Pod actual = podService.save(pod);
		// Assert
		assertEquals(pod, actual);
		verify(podRepoMock).save(pod);
	}
	
	@Test
	void test_Update_ShouldUpdateOnePod_WhenIdFound() {
		// Arrange
		Pod updatedPod = new Pod();
		updatedPod.setPodName("pod002");
		when(podRepoMock.existsById(podId)).thenReturn(true);
		when(podRepoMock.save(updatedPod)).thenReturn(updatedPod);
		// Act
		Pod actualUpdatedPod = podService.update(podId, updatedPod);
		// Assert
		assertEquals(updatedPod.getPodName(), actualUpdatedPod.getPodName());
		verify(podRepoMock).save(updatedPod);
	}
	
	@Test
	void test_Update_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		String message = "Pod with id " + invalidPodId + " does not exist.";
		// Act
		Exception expected = assertThrows(NotFoundException.class, () -> podService.update(invalidPodId, pod));
		// Assert
		assertEquals(message, expected.getMessage());
		verify(podRepoMock).existsById(invalidPodId);
	}
	
	@Test 
	void test_RemovePodById_ShouldRemovePod_WhenIdFound() {
		// Arrange
		when(podRepoMock.existsById(podId)).thenReturn(true);
		// Act
		podService.removePodById(podId);
		// Assert
		verify(podRepoMock).deleteById(podId);
	}
	
	@Test
	void test_RemovePodById_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		String message = "Pod with id " + invalidPodId + " does not exist.";
		// Act
		Exception expected = assertThrows(NotFoundException.class, () -> podService.removePodById(invalidPodId));
		// Assert
		assertEquals(message, expected.getMessage());
		verify(podRepoMock).existsById(invalidPodId);
	}
	
	@Test 
	void test_AddUserToPodByIds_ShouldReturnUpdatedPodWithNewUsersAdded_WhenPodIdAndUserIdFoundAndIfUserTypeEqualsToTrainee() {
		// Arrange
		when(podRepoMock.findById(podId)).thenReturn(Optional.of(pod));
		when(userRepoMock.findById(traineeId)).thenReturn(Optional.of(trainee));
		// Act
		Pod expected = podService.addUserToPodByIds(podId, traineeId);
		// Assert
		assertEquals(pod, expected);
	}
	
	@Test
	void test_AddUserToPodByIds_ShouldThrowNotFoundException_WhenPodIdNotFound() {
		// Arrange
		String expectedMessage = "Unable to find Pod with ID " + invalidPodId;
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> podService.addUserToPodByIds(invalidPodId, primaryTrainerId));
		// Assert
		assertEquals(expectedMessage, actualExceptionThrown.getMessage());
		verify(podRepoMock).findById(invalidPodId);
	}
	
	@Test
	void test_AddUserToPodByIds_ShouldThrowNotFoundException_WhenUserIdNotFound() {
		// Arrange
		long invalidUserId = 10L;
		String expectedMessage = "Unable to find User with ID " + invalidUserId;
		when(podRepoMock.findById(podId)).thenReturn(Optional.of(pod));
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> podService.addUserToPodByIds(podId, invalidUserId));
		// Assert
		assertEquals(expectedMessage, actualExceptionThrown.getMessage());
		verify(userRepoMock).findById(invalidUserId);
	}
	
	@Test
	void test_AddUserToPodByIds_ShouldThrowNotFoundException_WhenUserTypeIsNotTrainee() {
		// Arrange
		String expectedMessage = "Unable to find Trainee with ID " + primaryTrainerId ;
		when(podRepoMock.findById(podId)).thenReturn(Optional.of(pod));
		when(userRepoMock.findById(primaryTrainerId)).thenReturn(Optional.of(primaryTrainer));
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> podService.addUserToPodByIds(podId, primaryTrainerId));
		// Assert
		assertEquals(expectedMessage, actualExceptionThrown.getMessage());
		verify(podRepoMock).findById(podId);
		verify(userRepoMock).findById(primaryTrainerId);
	}
	
	@Test
	void test_RemoveUserFromPodByIds_ShouldRemoveAUserFromAPod_WhenPodIdAndUserIdFound() {
		// Arrange
		when(podRepoMock.findById(podId)).thenReturn(Optional.of(pod));
		User user = new User();
		long userId = 5L;
		when(userRepoMock.findById(userId)).thenReturn(Optional.of(user));
		// Act
		Pod actualReturnedPod = podService.removeUserFromPodByIds(podId, userId);
		// Assert
		assertEquals(pod, actualReturnedPod);
	}
	
	@Test
	void test_RemoveUserFromPodsByIds_ShouldThrowNotFoundException_WhenPodIdNotFound() {
		// Arrange
		String expectedExceptionMsg = "Unable to find Pod with ID " + invalidPodId;
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, 
				() -> podService.removeUserFromPodByIds(invalidPodId, traineeId));
		// Arrange
		assertEquals(expectedExceptionMsg, actualExceptionThrown.getMessage());
		verify(podRepoMock).findById(invalidPodId);
	}
	
	@Test
	void test_RemoveUserFromPodsByIds_ShouldThrowNotFoundException_WhenUserIdNotFound() {
		// Arrange
		long invalidTraineeId = 24L;
		String expectedExceptionMsg = "Unable to find User with ID " + invalidTraineeId;
		when(podRepoMock.findById(podId)).thenReturn(Optional.of(pod));
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, 
				() -> podService.removeUserFromPodByIds(podId, invalidTraineeId));
		// Arrange
		assertEquals(expectedExceptionMsg, actualExceptionThrown.getMessage());
		verify(userRepoMock).findById(invalidTraineeId);
	}

}
