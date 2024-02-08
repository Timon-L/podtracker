package com.fdmgroup.PodTracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PodTracker.dal.PodProjectRepository;
import com.fdmgroup.PodTracker.exceptions.NotFoundException;
import com.fdmgroup.PodTracker.model.PodProject;

@ExtendWith(MockitoExtension.class)
class PodProjectServiceTest {
	
	@Mock
	private PodProjectRepository ppr;
	@InjectMocks
	private PodProjectService pps;
	private PodProject expectedPodProject;
	private long podProjectId;
	private long invalidPodProjectId;
	private String expectedMessage;

	@BeforeEach
	void setUp() throws Exception {
		podProjectId = 2L;
		invalidPodProjectId = 5L;
		expectedMessage = "Pod project with id " + invalidPodProjectId + " does not exist.";
		expectedPodProject = new PodProject();
		expectedPodProject.setProjectId(podProjectId);
	}

	@Test
	void test_FindById_ShouldReturnOnePodProject_WhenIdFound() {
		// Arrange
		when(ppr.findById(podProjectId)).thenReturn(Optional.of(expectedPodProject));
		// Act
		PodProject actualPodProjectFound = pps.findById(podProjectId);
		// Assert
		assertEquals(expectedPodProject, actualPodProjectFound);
		verify(ppr).findById(podProjectId);
	}
	
	@Test
	void test_FindById_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> pps.findById(invalidPodProjectId));
		// Assert
		assertEquals(expectedMessage, actualExceptionThrown.getMessage());
		verify(ppr).findById(invalidPodProjectId);
	}
	
	@Test
	void test_FindAll_ShouldREturnAllPodProjects() {
		// Arrange
		List<PodProject> expectedPodProjects = new ArrayList<>();
		PodProject newPodProject = new PodProject();
		expectedPodProjects.add(expectedPodProject);
		expectedPodProjects.add(newPodProject);
		when(ppr.findAll()).thenReturn(expectedPodProjects);
		// Act
		List<PodProject> actualPodProjects = pps.findAll();
		// Assert
		assertNotNull(expectedPodProjects);
		assertEquals(expectedPodProjects.size(), actualPodProjects.size());
		verify(ppr).findAll();
	}
	
	@Test
	void test_Update_ShouldReturnOneUpdatedPodProject() {
		// Arrange
		PodProject expectedUpdatedPodProject = new PodProject();
		String updatedName = "updated name";
		expectedUpdatedPodProject.setName(updatedName);
		when(ppr.save(expectedUpdatedPodProject)).thenReturn(expectedUpdatedPodProject);
		// Act
		PodProject actualUpdatedPodProject = pps.update(expectedUpdatedPodProject);
		// Assert
		assertEquals(expectedUpdatedPodProject, actualUpdatedPodProject);
		verify(ppr).save(expectedUpdatedPodProject);
	}
	
	@Test
	void test_Save_ShouldReturnOnePodProject() {
		// Arrange
		when(ppr.save(expectedPodProject)).thenReturn(expectedPodProject);
		// Act
		PodProject actualReturnedPodProject = pps.save(expectedPodProject);
		// Assert
		assertEquals(expectedPodProject, actualReturnedPodProject);
		verify(ppr).save(expectedPodProject);
	}
	
	@Test
	void test_DeleteById_ShouldDeleteOnePodProject_WhenIdFound() {
		// Arrange
		when(ppr.existsById(podProjectId)).thenReturn(true);
		// Act
		pps.deleteById(podProjectId);
		// Assert
		verify(ppr).deleteById(podProjectId);
	}
	
	@Test
	void test_DeleteById_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> pps.deleteById(invalidPodProjectId));
		// Assert
		assertEquals(expectedMessage, actualExceptionThrown.getMessage());
		verify(ppr).existsById(invalidPodProjectId);
	}

}
