package com.fdmgroup.PodTracker.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PodTracker.model.InterviewPrep;
import com.fdmgroup.PodTracker.service.InterviewPrepService;

@ExtendWith(MockitoExtension.class)
class InterviewPrepControllerTest {

	@Mock
	private InterviewPrepService ipsMock;
	@InjectMocks
	private InterviewPrepController ipController;
	private InterviewPrep ip;
	private long ipId;

	@BeforeEach
	void setUp() throws Exception {
		ipId = 2L;
		ip = new InterviewPrep();
		ip.setId(ipId);
	}

	@Test
	void test_FindAll_ShouldReturnAllInterviewPreps() {
		// Arrange
		List<InterviewPrep> expectedIps = new ArrayList<>();
		expectedIps.add(ip);
		expectedIps.add(new InterviewPrep());
		when(ipsMock.findAll()).thenReturn(expectedIps);
		// Act
		List<InterviewPrep> actualIps = ipController.findAll();
		// Assert
		assertNotNull(actualIps);
		assertEquals(expectedIps, actualIps);
		verify(ipsMock).findAll();
	}

	@Test
	void test_FindById_ShouldReturnOneInterviewPrep_WhenIdProvided() {
		// Arrange
		when(ipsMock.findById(ipId)).thenReturn(ip);
		// Act
		InterviewPrep actualIp = ipController.findById(ipId);
		// Assert
		assertEquals(ip, actualIp);
		verify(ipsMock).findById(ipId);
	}

	@Test
	void test_Save_ShouldReturnOneInterview() {
		// Arrange
		when(ipsMock.save(ip)).thenReturn(ip);
		// Act
		InterviewPrep actualIp = ipController.save(ip);
		// Assert
		assertEquals(ip, actualIp);
		verify(ipsMock).save(ip);
	}

	@Test
	void test_Update_ShouldReturnOneUpdatedInterview_WhenIdProvided() {
		// Arrange
		when(ipsMock.update(ipId, ip)).thenReturn(ip);
		// Act
		InterviewPrep actualIp = ipController.update(ipId, ip);
		// Assert
		assertEquals(ip, actualIp);
		verify(ipsMock).update(ipId, ip);
	}
	
	@Test
	void test_Delete_ShouldDeleteOneClient_WhenIdProvided() {
		// Arrange
		// Act
		ipController.delete(ipId);
		// Assert
		verify(ipsMock).removeById(ipId);
	}

}
