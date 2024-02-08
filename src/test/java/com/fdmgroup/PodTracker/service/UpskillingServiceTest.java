package com.fdmgroup.PodTracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.*;
import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PodTracker.dal.UpskillingRepository;
import com.fdmgroup.PodTracker.exceptions.NotFoundException;
import com.fdmgroup.PodTracker.model.*;

@ExtendWith(MockitoExtension.class)
class UpskillingServiceTest {
	
	@Mock
	private UpskillingRepository upskillingRepoMock;
	private Upskilling upSkilling;
	@InjectMocks
	private UpskillingService upskillingService;
	private Trainee trainee;
	

	@BeforeEach
	void setUp() throws Exception {
		this.trainee = new Trainee();
		this.trainee.setUserId(1L);
		this.upSkilling = new Upskilling();
		this.upSkilling.setTrainee(trainee);
		this.upSkilling.setId(2L);
		this.upSkilling.setStartDate(LocalDate.of(2023, Month.OCTOBER, 1)); 
		this.upSkilling.setCompletionETA(LocalDate.of(2023, Month.OCTOBER, 10));
		this.upSkilling.setFinishDate(LocalDate.of(2023, Month.OCTOBER, 5));
		this.upSkilling.setTopic("Application Security");
		this.upSkilling.setObjective("to understand secure sw development");
		this.upSkilling.setStatus(TaskStatus.Completed);
	}
	
	@Test
	void test_Save_ShouldAddUpskilling() {
		when(upskillingRepoMock.save(upSkilling)).thenReturn(upSkilling);
		Upskilling result = upskillingService.save(upSkilling);
		assertNotNull(result);
	}
	
	@Test
	void test_Save_ShouldThrowIllegalArgumentExcpetion_WhenTopicIsNull() {
		// Arrange
		Upskilling upskilling2 = new Upskilling();
		// Act
	    Throwable exception = assertThrows(IllegalArgumentException.class, () -> upskillingService.save(upskilling2));
	    // Assert
	    assertTrue(exception.getMessage().contains("Topic cannot be null"));
	}  
 
	@Test
	void test_FindById_ShouldReturnUpskilling_WhenIdFound() {
		//Arrange
		when(upskillingRepoMock.findById(2L)).thenReturn(Optional.of(upSkilling));
		//Act
		//Assert
		assertEquals(upskillingService.findById(2L), upSkilling);
	}
	
	@Test
	void test_FindById_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		long invalidId = 5L;
		String expectedExceptionMsg = "Unable to find upskilling with id " + invalidId;
		// Act
	    Throwable exception = assertThrows(NotFoundException.class, () -> upskillingService.findById(invalidId));
	    // Assert
	    assertEquals(expectedExceptionMsg, exception.getMessage());
	    verify(upskillingRepoMock).findById(invalidId);
	}  
	
	@Test
	void test_FindAll_ShouldReturnAllUpskilling() {
		//Arrange
		List<Upskilling> upskillingList = new ArrayList<>();
		upskillingList.add(new Upskilling());
		upskillingList.add(new Upskilling());
		when(upskillingRepoMock.findAll()).thenReturn(upskillingList);
		// Act
		List<Upskilling> result = upskillingService.findAll();
		// Assert
		assertNotNull(result);
		assertEquals(2, result.size());
	}
	
	@Test
	void test_UpdateUpskilling_ShouldUpdateUpskillingFields_WhenUpskillingFound() {
		// Arrange
		UpskillingService upskillingService = new UpskillingService(this.upskillingRepoMock);
		Upskilling updatedUpskilling = new Upskilling();
		updatedUpskilling.setObjective("to learn how to pentest");
		when(upskillingRepoMock.save(updatedUpskilling)).thenReturn(updatedUpskilling);
		// Act
		Upskilling result = upskillingService.update(updatedUpskilling);
		// Assert
		assertEquals("to learn how to pentest", result.getObjective());
	}
	
	@Test
	void test_DeleteById_ShouldDeleteUpSkilling_WhenIdFound() {
		// Arrange
		when(upskillingRepoMock.existsById(2L)).thenReturn(true);
		// Act
		upskillingService.deleteById(2L);
		// Assert
		verify(upskillingRepoMock, times(1)).deleteById(2L);
	}
	
	@Test
	void test_DeleteById_ShouldThrowNotFoundException_WhenIdNotFound() {
		// Arrange
		long invalidId = 5L;
		// Act
		// Assert
		assertThrows(NotFoundException.class, () -> upskillingService.deleteById(invalidId));
	}

}