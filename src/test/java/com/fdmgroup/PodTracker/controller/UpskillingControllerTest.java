package com.fdmgroup.PodTracker.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PodTracker.model.*;
import com.fdmgroup.PodTracker.service.UpskillingService;

@ExtendWith(MockitoExtension.class)
class UpskillingControllerTest {

	@Mock
	private UpskillingService usMock;
	@InjectMocks
	private UpskillingController uc;
	private Upskilling u;
	private long uId;
	
	@BeforeEach
	void setUp() throws Exception {
		uId = 2L;
		u = new Upskilling();
		u.setId(uId);
	}

	@Test
	void test_FindAll_ShouldReturnAllUpskillings() {
		// Arrange
		List<Upskilling> expectedUs = new ArrayList<>();
		expectedUs.add(u);
		expectedUs.add(new Upskilling());
		when(usMock.findAll()).thenReturn(expectedUs);
		// Act
		List<Upskilling> actualUs = uc.findAll();
		// Assert
		assertNotNull(actualUs);
		assertEquals(expectedUs, actualUs);
		verify(usMock).findAll();
	}

	@Test
	void test_FindById_ShouldReturnOneUpskilling_WhenIdProvided() {
		// Arrange
		when(usMock.findById(uId)).thenReturn(u);
		// Act
		Upskilling actualU = uc.findById(uId);
		// Assert
		assertEquals(u, actualU);
		verify(usMock).findById(uId);
	}

	@Test
	void test_Save_ShouldReturnOneUpskilling() {
		// Arrange
		when(usMock.save(u)).thenReturn(u);
		// Act
		Upskilling actualU = uc.save(u);
		// Assert
		assertEquals(u, actualU);
		verify(usMock).save(u);
	}

	@Test
	void test_Update_ShouldReturnOneUpdatedUpskilling() {
		// Arrange
		when(usMock.update(u)).thenReturn(u);
		// Act
		Upskilling actualU = uc.update(u);
		// Assert
		assertEquals(u, actualU);
		verify(usMock).update(u);
	}
	
	@Test
	void test_Delete_ShouldDeleteOneUpskilling_WhenIdProvided() {
		// Arrange
		// Act
		uc.delete(uId);
		// Assert
		verify(usMock).deleteById(uId);
	}

}
