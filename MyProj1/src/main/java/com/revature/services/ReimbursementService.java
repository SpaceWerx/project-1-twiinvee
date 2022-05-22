package com.revature.services;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import com.revature.mockdata.MockReimbursementData;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;
import com.revature.repositories.UserDAO;

public class ReimbursementService {
	static UserDAO userDAO = new UserDAO();

	
	public ReimbursementDAO reimbursementDAO = new ReimbursementDAO();



    public UserService rService = new UserService();
    public static List<MockReimbursementData> mockData = new ArrayList<>();
    public static ArrayList<Reimbursement> reimbursements = new ArrayList<>();
    public static void clearData() {
        reimbursements.clear();
    }



    public User getUserUsername(String username) {
        return userDAO.getByUsername(username);
    }
//////////////////////////////////////////////////

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
/////////////////////////////////////////////////////////////////////////////////////////////////
    public void UserExistsById(int id) {
    for(User user : userDAO.getAllUsers()) {
        if(user.getId()== id) {
            System.out.println("This ID exists");
            break;
        }
    }
        System.out.println("This ID does not exist");
}
//////////////////////////////////////////////
    public List<User> getUserByRole(Role role){
        List<User> byRole = new ArrayList<>();
        for(User user : userDAO.getAllUsers()) {
            if(user.getRole() == role) 
            {
                byRole.add(user);
            }
        }

        return byRole;
    }
/////////////////////////////////////////////////////////////////////////
    public User getUserById(int id) {
        return userDAO.getUserbyId(id);
    }
//////////////////////////////////////////////////////////////////////////
    public void addUser(User newEmployee) throws SQLException {

        //take in the Employee object sent from the menu and send it to the EmployeeDAO to be inserted into the database

        //call the DAO method that inserts the new Employee
        userDAO.create(newEmployee);
    }

    public boolean checkUserExistsById(int id) {
        return false;
    }

    
    
    

	
// Update Method	
	public void update(Reimbursement unprocessedReimbursement, int resolverId, Status updatedStatus) {
		
		for (Reimbursement reimbursement : reimbursements) {
			if (reimbursement.getId() == unprocessedReimbursement.getId()) {
				reimbursement.setResolver(resolverId);
				reimbursement.setStatus(updatedStatus);
				return;
			}
		}
		throw new RuntimeException("There was an error processing this reimbursement, please try again");

	}
	
	
// Get Pending Reimbursement Method
		public List<Reimbursement> getPendingReimbursements() {
			List<Reimbursement> pendingReimbursements = new ArrayList<Reimbursement>();
			
			for (Reimbursement reimbursement : reimbursements) { 
				if (reimbursement.getStatus() == Status.Pending) {
					pendingReimbursements.add(reimbursement);
		
				}
			}
			return pendingReimbursements;
		}
		
// Get Resolved Method
		public List<Reimbursement> getResolvedReimbursements() {
				List<Reimbursement> resolvedReimbursements = new ArrayList<Reimbursement>();
				
			for (Reimbursement reimbursement : reimbursements) { 
				if (reimbursement.getStatus() == Status.Approved || reimbursement.getStatus() == Status.Denied) {
					resolvedReimbursements.add(reimbursement);
				}
			}
			return resolvedReimbursements;
		}
		
		
//	Submit Method
		public void submitReimbursement (Reimbursement reimbursementToBeSubmitted) {
			Reimbursement latestReimbursement = reimbursements.get(reimbursements.size() - 1);
			int id = latestReimbursement.getId() + 1; // New ID is 1 higher than the previous highest
			
			reimbursementToBeSubmitted.setId(id); 
			reimbursementToBeSubmitted.setStatus (Status.Pending);
			reimbursements.add(reimbursementToBeSubmitted);
			
			
}
		
//		Get by iD method
		public Reimbursement getReimbursementById(int id) {
			
		
			for (Reimbursement reimbursement : reimbursements) { 
				if (reimbursement.getId() == id) {
					return reimbursement;
				}
			}
			return null;
		}
	
		
		
//		Get by Author Method
		public List<Reimbursement> getReimbursementsByAuthor(int userId) {
			
			List<Reimbursement> userReimbursements = new ArrayList<Reimbursement>();
			
		
			for (Reimbursement r : reimbursements) { 
				if (r.getAuthor() == userId || r.getResolver() == userId) {
				}
			}
		
			return userReimbursements;
		}
		
		
		
}
		
		
		
		