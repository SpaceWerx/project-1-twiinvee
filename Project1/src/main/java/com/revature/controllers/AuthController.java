package com.revature.controllers;


import com.google.gson.Gson;
import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.services.AuthService;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpCode;

public class AuthController {
	
UserDAO userDAO = new UserDAO();
User user = new User();
AuthService as = new AuthService();

	//Allows user to create a new user in the database
	/**
	* This Javalin handler method leverages the Http call context to 
	* call the register AuthService method.
	* The input json user object must have an ID of 0 to map correctly
	*/
	public Handler handleRegister = (ctx) -> {
		
		String body = ctx.body();
		Gson gson = new Gson();
		User user = gson.fromJson(body, User.class);
		
		
		
		if (user != null ) {
			userDAO.create(user);
			ctx.status(HttpCode.CREATED);
			ctx.result("Registration successful.");
		} else {
			ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
			ctx.result("Registration unsuccessful.");
			
		}
		

	};
		
		
/** This Javalin handler method leverages the Http call
 *  context to call the login AuthService method.
 */
	
public Handler handleLogin = (ctx) -> {
	
	String body = ctx.body();
	Gson gson = new Gson();
	
	User LDTO = gson.fromJson(body, User.class);

	
	if(as.login(LDTO.getUsername(),LDTO.getPassword()) == 1) {
		
			ctx.status(202);
			ctx.result("Manager login successful");
		} 
	else if (as.login(LDTO.getUsername(), LDTO.getPassword())== 2) {
			ctx.status(200);
			ctx.result("Employee login successful");
		
		} 
		else {
				ctx.status(401);
				ctx.result("Invalid credentials");
		}
};
	
}

