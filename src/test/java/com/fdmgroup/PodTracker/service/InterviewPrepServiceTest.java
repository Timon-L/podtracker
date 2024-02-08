package com.fdmgroup.PodTracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PodTracker.dal.InterviewPrepRepository;
import com.fdmgroup.PodTracker.exceptions.NotFoundException;
import com.fdmgroup.PodTracker.model.InterviewPrep;

@ExtendWith(MockitoExtension.class)
class InterviewPrepServiceTest {
	
	@Mock
	private InterviewPrepRepository ipRepoMock;
	
	@InjectMocks
	private InterviewPrepService ipService;
	private InterviewPrep ip;
	private long ipId;
	private long invalidIpId;
	private String expectedMessage;

	@BeforeEach
	void setUp() throws Exception {
		ipId = 2L;
		invalidIpId = 10L;
		expectedMessage = "InterviewPrep with id " + invalidIpId + " does not exist.";
		ip = new InterviewPrep();
		ip.setId(ipId);
	}

	@Test
	void test_FindById_ShouldReturnOneInterviewPrep_WhenIdFound() {
		// Arrange
		when(ipRepoMock.findById(ipId)).thenReturn(Optional.of(ip));
		// Act
		InterviewPrep actualIp = ipService.findById(ipId);
		// Assert
		assertEquals(ip, actualIp);
		verify(ipRepoMock).findById(ipId);
	}
	
	@Test
	void test_FindById_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> ipService.findById(invalidIpId));
		// Assert
		assertEquals(expectedMessage, actualExceptionThrown.getMessage());
		verify(ipRepoMock).findById(invalidIpId);
	}
	
	@Test
	void test_FindAll_ShouldReturnAllInterviewPrep() {
		// Arrange
		List<InterviewPrep> expectedIps = new ArrayList<>();
		expectedIps.add(ip);
		expectedIps.add(new InterviewPrep());
		when(ipRepoMock.findAll()).thenReturn(expectedIps);
		// Act
		List<InterviewPrep> actualIps = ipService.findAll();
		// Assert
		assertNotNull(actualIps);
		assertEquals(expectedIps.size(), actualIps.size());
		verify(ipRepoMock).findAll();
	}
	
	@Test
	void test_Save_ShouldReturnOneInterviewPrep() {
		// Arrange
		when(ipRepoMock.save(ip)).thenReturn(ip);
		// Act
		InterviewPrep actualIp = ipService.save(ip);
		// Arrange
		assertEquals(ip, actualIp);
		verify(ipRepoMock).save(ip);
	}
	
	@Test
	void test_Update_ShouldUpdateOneInterviewPrep_WhenIdFound() {
		// Arrange
		when(ipRepoMock.existsById(ipId)).thenReturn(true);
		InterviewPrep expectedUpdatedIp = new InterviewPrep();
		expectedUpdatedIp.setAllocationHours(2);
		when(ipRepoMock.save(expectedUpdatedIp)).thenReturn(expectedUpdatedIp);
		// Act
		InterviewPrep actualUpdatedIp = ipService.update(ipId, expectedUpdatedIp);
		// Assert
		assertEquals(expectedUpdatedIp, actualUpdatedIp);
		verify(ipRepoMock).save(expectedUpdatedIp);
	}
	
	@Test
	void test_Update_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> ipService.update(invalidIpId, ip));
		// Assert
		assertEquals(expectedMessage, actualExceptionThrown.getMessage());
		verify(ipRepoMock).existsById(invalidIpId);
	}
	
	@Test
	void test_RemoveById_ShouldRemoveOneInterviewPrep_WhenIdFounfd() {
		// Arrange
		when(ipRepoMock.existsById(ipId)).thenReturn(true);
		// Act
		ipService.removeById(ipId);
		// Assert
		verify(ipRepoMock).deleteById(ipId);
	}
	
	@Test
	void test_RemoveById_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		// Act
		Exception actualExceptionThrown = assertThrows(NotFoundException.class, () -> ipService.removeById(invalidIpId));
		// Assert
		assertEquals(expectedMessage, actualExceptionThrown.getMessage());
		verify(ipRepoMock).existsById(invalidIpId);
	}
	
	

}
