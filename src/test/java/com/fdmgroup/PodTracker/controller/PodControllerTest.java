package com.fdmgroup.PodTracker.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PodTracker.model.Pod;
import com.fdmgroup.PodTracker.service.PodService;

@ExtendWith(MockitoExtension.class)
class PodControllerTest {

	@Mock
	private PodService psMock;
	@InjectMocks
	private PodController pc;
	private Pod pod;
	private long podId;
	private long userId;
	
	@BeforeEach
	void setUp() throws Exception {
		podId = 2L;
		userId = 4L;
		pod = new Pod();
		pod.setPodId(podId);
	}

	@Test
	void test_FindAll_ShouldReturnAllPods() {
		// Arrange
		List<Pod> expectedPods = new ArrayList<>();
		expectedPods.add(pod);
		expectedPods.add(new Pod());
		when(psMock.findAll()).thenReturn(expectedPods);
		// Act
		List<Pod> actualPods = pc.findAll();
		// Assert
		assertNotNull(actualPods);
		assertEquals(expectedPods, actualPods);
		verify(psMock).findAll();
	}

	@Test
	void test_FindById_ShouldReturnOnePod_WhenIdProvided() {
		// Arrange
		when(psMock.findPodById(podId)).thenReturn(pod);
		// Act
		Pod actualPod = pc.findById(podId);
		// Assert
		assertEquals(pod, actualPod);
		verify(psMock).findPodById(podId);
	}

	@Test
	void test_SavePod_ShouldReturnOnePod() {
		// Arrange
		when(psMock.save(pod)).thenReturn(pod);
		// Act
		Pod actualPod = pc.savePod(pod);
		// Assert
		assertEquals(pod, actualPod);
		verify(psMock).save(pod);
	}

	@Test
	void test_UpdatePod_ShouldReturnOneUpdatedPod_WhenIdProvided() {
		// Arrange
		when(psMock.update(podId, pod)).thenReturn(pod);
		// Act
		Pod actualPod = pc.updatePod(podId, pod);
		// Assert
		assertEquals(pod, actualPod);
		verify(psMock).update(podId, pod);
	}
	
	@Test
	void test_DeletePod_ShouldDeleteOnePod_WhenIdProvided() {
		// Arrange
		// Act
		pc.deletePod(podId);
		// Assert
		verify(psMock).removePodById(podId);
	}
	
	@Test
	void test_AddUserToPodByIds_ShouldReturnOnePod_WhenPodIdAndUserIdProvided() {
		// Arrange
		when(psMock.addUserToPodByIds(podId, userId)).thenReturn(pod);
		// Act
		Pod actualPod = pc.addUserToPodByIds(podId, userId);
		// Assert
		assertEquals(pod, actualPod);
		verify(psMock).addUserToPodByIds(podId, userId);
	}
	
	@Test
	void test_RemoveUserFromPodByIds_ShouldRemoveOneUserFromPod_WhenPodIdAndUserIdProvided() {
		// Arrange
		when(psMock.removeUserFromPodByIds(podId, userId)).thenReturn(pod);
		// Act
		Pod actualPod = pc.removeUserFromPodByIds(podId, userId);
		// Assert
		assertEquals(pod, actualPod);
		verify(psMock).removeUserFromPodByIds(podId, userId);
	}

}
