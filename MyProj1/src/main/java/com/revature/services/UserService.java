package com.revature.services;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserDAO;

public class UserService {
	
	static UserDAO userDAO = new UserDAO();

	
	//Get User by username Method
    public User getUserUsername(String username) {
        return userDAO.getByUsername(username);
    }


    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
    
    
    
    
    
  //if Id exist
    public void UserExistsById(int id) {
    for(User user : userDAO.getAllUsers()) {
        if(user.getId()== id) {
            System.out.println("This ID exists");
            break;
        }
    }
        System.out.println("This ID does not exist");
}
    
    
    
    
  //get user by role
    public List<User> getUserByRole(Role role){
        List<User> byRole = new ArrayList<>();
        for(User user : userDAO.getAllUsers()) {
            if(user.getRole() == role)  {
                byRole.add(user);
            }
        }

        return byRole;
    }
    
    
    
//get user by ID
    public User getUserById(int id) {
        return userDAO.getUserbyId(id);
    }
    
    
    
    
//create employee
    public void addUser(User newEmployee) throws SQLException {

        //take in the Employee object sent from the menu and send it to the EmployeeDAO to be inserted into the database

        //call the DAO method that inserts the new Employee
        userDAO.create(newEmployee);
    }

    public boolean checkUserExistsById(int id) {
        return false;
    }


}
