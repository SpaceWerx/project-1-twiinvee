package com.revature.services;

import com.revature.models.User;
import com.revature.repositories.UserDAO;

public class AuthService {

//	registerMethod.png
	/**
	 * Note: userToBeRegistered will have an id=0.
	 * After registration, the id will be a positive integer.
	 */
	
// making a new user object
	public int register(User userToBeRegistered) {
		
		//	checking if the username already exists in the database 
		// if the method returns null, the username is available
		if(userDAO.getByUsername(userToBeRegistered.getUsername()) !=null) {
			
			
			// Throws a NullPointerException if the username is already taken
			throw new NullPointerException("Username is already taken");
		}
		
		//	take in the user object sent from the menu and send it to the userDAO to be inserted into the database
		// After the entry has been made, the ID of the new user is immediately returned
		return userDAO.create(userToBeRegistered);
		
		
	}
	
	
	
//	loginMethod.png
	//What would normally happen here, if you would call your DAO, your DAO would query the database, and you would compare values here. After which
	// You would send off if it worked or not
	//My recommendation to all of you, is have your login differentiate here
	//Because you need a manager AND employee menu, When a manager logs in, send a 201 status code, and have your JS put them on the Manager Menu
	//When a normal employee logs in, send a 202 status code, and have them sent to the Employee Menu
	
	//As well, I recommend that you ONLY pass the employee to the DAO, there is no need to also send the password
/**
 * The login method is used to check the information given and verify their credentials 
 * 
 * @return User object	
 */
public User login(String username, String password) {
	
	//	Instantiating a temporary user
	User user;
	
	//	The try+catch block will catch any exceptions thrown by the userDAO methods
	try {
			//	REtreiving the user data from the database from the username given
		user = userDAO.getByUsername(username);
		
		// These conditional statements are checking various contingencies
		// The first is checking if the user exists and that the password given matches the one stored
		if (user!=null && password.equals(user.getPassword())) {
			
			// If this one is true, the user object is returned and login is deemed successful
			System.out.println("Logged In Successful!");
			return user;
			
			// The second is checking if the user exists and the password given is different than the one stored
		} else if (user!=null && !password.equals(user.getPassword())) {
			
			// If this one is true and the previous false, a null object is returned and login is deemed unsuccessful
			System.out.println("Wrong Password");
			return null;
			
		// The third is the final contingency and will only occur if the username does not exist in the database 
		} else {
			
			// This outcome will return a null object and login is deemed unsuccessful 
			System.out.println("User Does not Exist!");
			return null;
		}
	} catch (Exception e) {
		System.out.println("Login Unsuccessful");
		e.printStackTrace(); // Helpful debugging tool
	}
	
	// if the try+catch does not run, a null object is returned and login is deemed unsuccessful
	return null;
}
	
}
