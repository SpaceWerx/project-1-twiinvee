package com.revature.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.mockdata.MockReimbursementData;
import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;


public class ReimbursementServiceTest {

	
//updateNotManagerFailsTest.png
	@Test
	public void testUpdateThrowsIllegalArgumentExceptionWhenResolverIsNotManager() {
	
		// Telling the nested userService method to return an Employee when called in the tested update method
		when(userService.getUserById(anyInt())).thenReturn(GENERIC_EMPLOYEE_1);
	
		//Checking to make sure the tested update method throws the exception
		assertThrows(IllegalArgumentException.class,
				
				() -> reimbursementService.update(REIMBURSEMENT_TO_PROCESS, GENERIC_EMPLOYEE_1.getId(), Status.Approved)
				
		);
		
		// Verifying that the mocked reimbursementDAO update method is never called
		// Verifying that the mocked userService getUserById method is called
		verify(reimbursementDAO, never()).update(REIMBURSEMENT_TO_PROCESS);
		verify(userService).getUSerById(GENERIC_EMPLOYEE_1.getId());
		
	}
	
	
//	submitThrowsWhenManagerTest.png
	@Test
	public void testSubmitReimbursementThrowsIllegalArgumentExceptionWhenSubmittedByManager() {
		
		// Telling the nested userService method to return an Manager when called in the tested update method
		when(userService.getUserById(anyInt())).thenReturn(GENERIC_MANAGER_1);
		//Checking to make sure the tested update method throws the exception
		assertThrows(IllegalArgumentException.class,
				
				() -> reimbursementService.submitReimbursement(REIMBURSEMENT_TO_PROCESS)
		);
		
		// Verifying that the mocked reimbursementDAO update method is never called
		// Verifying that the mocked userService getUserById method is called
		verify(reimbursementDAO, never()).update(REIMBURSEMENT_TO_PROCESS);
		verify(userService).getUSerById(GENERIC_EMPLOYEE_1.getId());
		
	}
	
	
//	statusChangeOnUpdateTest.png
	@Test
	public void testReimbursementStatusIsChangedAfterUpdate() {
	
	// Telling the nested userService method to return an Manager when called in the tested update method
	when(userService.getUserById(anyInt())).thenReturn(GENERIC_MANAGER_1);
	//Checking to make sure the tested update method throws the exception
	assertEquals(Status.Approved, reimbursementService.update(REIMBURSEMENT_TO_PROCESS, GENERIC_MANAGER_1.getId(), Status.Approved).getStatus());
			
	// Verifying that the mocked reimbursementDAO update method is never called
			// Verifying that the mocked userService getUserById method is called
			verify(userService).getUserById(GENERIC_MANAGER_1.getId());
			verify(reimbursementDAO).update(REIMBURSEMENT_TO_PROCESS);
			
		}		
			
			
			
//	resolverAssignedonUpdateTest.png		
			
	@Test
	public void testResolverIsAssignedAfterReimbursementUpdate() {
		
		// Telling the nested user Service method to return a Manager when called in the tested update method
		when(userService.getUserById(anyInt())).thenReturn (GENERIC_MANAGER_1);
		
		// Checking to make sure the resolver is assigned accordingly when the update method is called
		assertEquals(GENERIC_MANAGER_1.getId(), reimbursementService.update(REIMBURSEMENT_TO_PROCESS, GENERIC_MANAGER_1.getId(), Status.Approved).getResolver());
		
		// Verifying that the mocked reimbursementDAO update method is called
		// Verifying that the mocked user Service getUserById method is called
		verify(userService).getUserById(GENERIC_MANAGER_1.getId());
		verify(reimbursementDAO).update(REIMBURSEMENT_TO_PROCESS);
	}
		
	
	
			
			
//		ReimbursementsServiceTestSetup.png	
			
	private static ReimbursementService reimbursementService;
	private static UserService userService;
	private static ReimbursementDAO reimbursementDAO;
	
	private Reimbursement REIMBURSEMENT_TO_PROCESS;
	private List<Reimbursement> mockPendingReimbursements;
	private List<Reimbursement> mockApprovedReimbursements;
	private List<Reimbursement> mockDeniedReimbursements;
	private User GENERIC_EMPLOYEE_1;
	private User GENERIC_MANAGER_1;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
		// Instantiating a new reimbursement service that is being tested
		reimbursementService = new ReimbursementService();
	}
	
	
	// This method will be called before each test is initiated
	@Before
	public void setUp() throws Exception {
	
		// Mocking the user service and reimbursementDAO because they are not being directly tested here
		userService = mock(UserService.class);
		reimbursementDAO = mock(ReimbursementDAO.class);
		
		// Retrieving the mock data we made in week 1 to leverage in our tests
		MockReimbursementData mockReimbursementData = new MockReimbursementData();
		
		
		// We must assign the mocks to the instantiated reimbursement service to ensure it's not using the real ones
		reimbursementService.reimbursementDAO = reimbursementDAO;
		reimbursementService.userService = userService;
		
		
		// Generic mock users to use in our tests
		GENERIC_EMPLOYEE_1 = new User(1, "generic Employeel", "generic Password", Role.Employee);
		GENERIC_MANAGER_1 = new User(1, "generic Manager1", "generic Password", Role.Manager);
		
		// mock reimbursement that can be tested by processing
		REIMBURSEMENT_TO_PROCESS = new Reimbursement(2, GENERIC_EMPLOYEE_1.getId(), "Oracle Certification", ReimbursementType.Other, Status.Pending, 150.00);
		
		
		// Creating lists of reimbursements from the mockReimbursement Data
		// These lists will be used in various tests of the Reimbursement Service
		List<Reimbursement> mockReimbursements = mockReimbursementData.getReimbursements();
		mockPendingReimbursements = new ArrayList<>();
		mockApprovedReimbursements = new ArrayList<>();
		mockDeniedReimbursements = new ArrayList<>();
		// Iterating through the mock reimbursements and adding them to their respective lists by status
		for(Reimbursement reimbursement : mockReimbursements) {
				if(reimbursement.getStatus() == Status.Pending) {
					mockPendingReimbursements.add(reimbursement);
				} else if (reimbursement.getStatus() == Status.Approved) {
					mockApprovedReimbursements.add(reimbursement);
				} else {
					mockDeniedReimbursements.add(reimbursement);
			
				}
		}
	}
	
//	getResolvedOnlyResolvedTest.png	
	
	@Test
	public void testGetResolvedReimbursementsReturnsOnlyApprovedAndDenied() {
	
		// Telling the nested reimbursementDAO getByStatus method to return the mocked list of Approved and Denied reimbursements when called respectively
		when(reimbursementDAO.getByStatus(Status.Approved)).thenReturn(mockApprovedReimbursements);
		when(reimbursementDAO.getByStatus(Status.Denied)).thenReturn(mockDeniedReimbursements);
		
		// Creating a new list that combines the mocked approved and denied reimbursements (similar to how the service method works)
		List<Reimbursement> resolvedReimbursements = new ArrayList<>();
		resolvedReimbursements.addAll(mockApprovedReimbursements);
		resolvedReimbursements.addAll(mockDeniedReimbursements);
		
		// Checking to make sure the service method returns the correct data
		assertEquals(resolvedReimbursements, reimbursementService.getResolvedReimbursements());
		
		// Verifying that the mocked reimbursementDAO method getByStatus is called twice
		verify(reimbursementDAO).getByStatus(Status.Approved);
		verify(reimbursementDAO).getByStatus(Status.Denied);
		
	}
	
	//getPendingIsOnlyPendingTest.png
	@Test
	public void testGetPendingReimbursementsReturnsOnlyPending() {
		
		// Telling the nested reimbursementDAO getByStatus method to return the mocked list of pending reimbursements when called
		when(reimbursementDAO.getByStatus(any(Status.class))).thenReturn(mockPendingReimbursements);
		
		// Checking to make sure the service method returns the correct data
		assertEquals(mockPendingReimbursements, reimbursementService.getPendingReimbursements());
		
		// Verifying that the mocked reimbursementDAO method getByStatus is called
		verify(reimbursementDAO).getByStatus(Status.Pending);
		
	}
	
			
}
