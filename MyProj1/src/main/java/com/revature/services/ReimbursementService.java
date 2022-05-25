package com.revature.services;
import java.util.ArrayList;
import java.util.List;

import com.revature.mockdata.MockReimbursementData;
import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;

public class ReimbursementService {
	
// Initiating the DAO and user service to utilize in various methods	
	 ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
     UserService userService = new UserService();
    
    
    
     
     
  public static List<MockReimbursementData> mockData = new ArrayList<>();
  public static ArrayList<Reimbursement> reimbursements = new ArrayList<>();
  public static void clearData() {
     reimbursements.clear();
   }  

	
// Update Method
  /**
   * method pass in a reimbursement, manager ID, and new status
   * it is meant to update fields and ensure user has manager role
   * The full reimbursement will be returned with updated field
   */
  public Reimbursement update(Reimbursement unprocessedReimbursement, int resolverId, Status updatedStatus) {
	  	
	  // Getting the user information from the resolver ID passed in
	  User manager = userService.getUserById(resolverId);
	  
	  //check if user is manager
	  if(manager.getRole() != Role.Manager) {
		  // Throwing an exception if the user is an employee
		  throw new IllegalArgumentException("An Employee cannot process reimbursement requests.");
	  } else {
		  //setting the respective fields with the passed in data
		  unprocessedReimbursement.setResolver(resolverId);
		  unprocessedReimbursement.setStatus(updatedStatus);
		  
		  //calling the update method to ensure persistence to database
		  reimbursementDAO.update(unprocessedReimbursement);
		  // return reimbursement with updated fields
		  return unprocessedReimbursement;
	  }
  }
  
  
//	public Reimbursement update(Reimbursement unprocessedReimbursement, int resolverId, Status updatedStatus) {
//		
//		for (Reimbursement reimbursement : reimbursements) {
//			if (reimbursement.getId() == unprocessedReimbursement.getId()) {
//				reimbursement.setResolver(resolverId);
//				reimbursement.setStatus(updatedStatus);
//				return;
//			}
//		}
//		throw new RuntimeException("There was an error processing this reimbursement, please try again");
//
//	}
//	
	
// Get Pending Reimbursement Method
	/**
	 * This method is meant to return a List of reimbursement records from the database that have a status of Pending.  
	 */
	public List<Reimbursement> getPendingReimbursements() { return reimbursementDAO.getByStatus(Status.Pending); }
	

	     
//		public List<Reimbursement> getPendingReimbursements() {
//			List<Reimbursement> pendingReimbursements = new ArrayList<Reimbursement>();
//			
//			for (Reimbursement reimbursement : reimbursements) { 
//				if (reimbursement.getStatus() == Status.Pending) {
//					pendingReimbursements.add(reimbursement);
//		
//				}
//			}
//			return pendingReimbursements;
//		}

	
	
// GET RESOLVED METHOD
	
	/**
	 * This method is meant to combine reimbursement records with Status of Approved or Denied.
	 * It will return the combined list of records.
	 * 
	 */
	
	public List<Reimbursement> getResolvedReimbursements() {
		
		//create temp return list to combine record queries from getByStatus
		List<Reimbursement> resolvedReimbursements = new ArrayList<>();
		
		//addAll function adds a collection of records to the arrayList
		resolvedReimbursements.addAll(reimbursementDAO.getByStatus(Status.Approved));
		resolvedReimbursements.addAll(reimbursementDAO.getByStatus(Status.Denied));
		
		// return the combined list of records
		return resolvedReimbursements;
	}
	
	
//		public List<Reimbursement> getResolvedReimbursements() {
//				List<Reimbursement> resolvedReimbursements = new ArrayList<Reimbursement>();
//				
//			for (Reimbursement reimbursement : reimbursements) { 
//				if (reimbursement.getStatus() == Status.Approved || reimbursement.getStatus() == Status.Denied) {
//					resolvedReimbursements.add(reimbursement);
//				}
//			}
//			return resolvedReimbursements;
//		}
		
		
//	SUBMIT METHOD
	
	
	/**
	 * method take in new reimbursement submission.
	 * submission author must be an employee
	 * method will return new positive integer ID
	 * @param reimbursementToBeSubmitted
	 */
	
	public int submitReimbursement (Reimbursement reimbursementToBeSubmitted) {
		
		
		// Getting user info. from author ID attached to the reimbursement submission
		User employee = userService.getUserById(reimbursementToBeSubmitted.getAuthor());
		
		//check if user is an employee
		if(employee.getRole() !=Role.Employee) {
			// throw exception if user is manager
			throw new IllegalArgumentException("Manager cannot sumbit reimbursement requests.");
			
		} else {
			//set status as pending by default and calling create method
			reimbursementToBeSubmitted.setStatus(Status.Pending);
			// returning the new int ID from the create method
			return reimbursementDAO.create(reimbursementToBeSubmitted);
		}
	}
	
	
//		public void submitReimbursement (Reimbursement reimbursementToBeSubmitted) {
//			Reimbursement latestReimbursement = reimbursements.get(reimbursements.size() - 1);
//			int id = latestReimbursement.getId() + 1; // New ID is 1 higher than the previous highest
//			
//			reimbursementToBeSubmitted.setId(id); 
//			reimbursementToBeSubmitted.setStatus (Status.Pending);
//			reimbursements.add(reimbursementToBeSubmitted);
//			
//			
//}
		
	
//		GET BY ID METHOD
		/**
		 * method is meant to retrieve a single record with the passed-in ID
		 * @param userId
		 * @return
		 */
	public Reimbursement getReimbursementById(int id) { 
		return reimbursementDAO.getReimbursementById(id); 
	}
//		public Reimbursement getReimbursementById(int id) {
//			
//		
//			for (Reimbursement reimbursement : reimbursements) { 
//				if (reimbursement.getId() == id) {
//					return reimbursement;
//				}
//			}
//			return null;
//		}
//	
		
		
//		GET BY AUTHOR METHOD
	/**
	 * This method should retrieve all reimbursement records that are associated with the userId provided
	 */
	public List<Reimbursement> getReimbursementsByAuthor(int userId) {
		return reimbursementDAO.getReimbursementsByUser(userId);
	}
	
//		public List<Reimbursement> getReimbursementsByAuthor(int userId) {
//			
//			List<Reimbursement> userReimbursements = new ArrayList<Reimbursement>();
//				for (Reimbursement r : reimbursements) { 
//					if (r.getAuthor() == userId || r.getResolver() == userId) {
//					}
//				}
//			
//				return userReimbursements;
//			}
//			
//		
		
}
		
		
		
		