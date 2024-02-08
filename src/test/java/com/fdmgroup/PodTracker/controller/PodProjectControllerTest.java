package com.fdmgroup.PodTracker.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PodTracker.model.*;
import com.fdmgroup.PodTracker.service.PodProjectService;

@ExtendWith(MockitoExtension.class)
class PodProjectControllerTest {

	@Mock
	private PodProjectService ppsMock;
	@InjectMocks
	private PodProjectController ppc;
	private PodProject pp;
	private long ppId;
	
	@BeforeEach
	void setUp() throws Exception {
		ppId = 2L;
		pp = new PodProject();
		pp.setProjectId(ppId);
	}

	@Test
	void test_FindAll_ShouldReturnAllPodProjects() {
		// Arrange
		List<PodProject> expectedPPs = new ArrayList<>();
		expectedPPs.add(pp);
		expectedPPs.add(new PodProject());
		when(ppsMock.findAll()).thenReturn(expectedPPs);
		// Act
		List<PodProject> actualPPs = ppc.findAll();
		// Assert
		assertNotNull(actualPPs);
		assertEquals(expectedPPs, actualPPs);
		verify(ppsMock).findAll();
	}

	@Test
	void test_FindById_ShouldReturnOnePodProject_WhenIdProvided() {
		// Arrange
		when(ppsMock.findById(ppId)).thenReturn(pp);
		// Act
		PodProject actualPP = ppc.findById(ppId);
		// Assert
		assertEquals(pp, actualPP);
		verify(ppsMock).findById(ppId);
	}

	@Test
	void test_Save_ShouldReturnOnePodProject() {
		// Arrange
		when(ppsMock.save(pp)).thenReturn(pp);
		// Act
		PodProject actualPP = ppc.save(pp);
		// Assert
		assertEquals(pp, actualPP);
		verify(ppsMock).save(pp);
	}

	@Test
	void test_Update_ShouldReturnOneUpdatedPodProject() {
		// Arrange
		when(ppsMock.update(pp)).thenReturn(pp);
		// Act
		PodProject actualPP = ppc.update(pp);
		// Assert
		assertEquals(pp, actualPP);
		verify(ppsMock).update(pp);
	}
	
	@Test
	void test_DeleteById_ShouldDeleteOnePodProject_WhenIdProvided() {
		// Arrange
		// Act
		ppc.deleteById(ppId);
		// Assert
		verify(ppsMock).deleteById(ppId);
	}

}
