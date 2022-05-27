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

	//handleRegisterMethod.png
	/**
	* This Javalin handler method leverages the Http call context to call the register AuthService method.
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
		
//		// Try+catch block to catch any exceptions thrown
//		try {
//			// Storing the json body as a string
//			String input = ctx.body();
//			
//			// Instantiating and using the object mapper
//			// This will parse the input string to a User object and store it in the local variable
////			ObjectMapper mapper = new ObjectMapper();
//			// User user = mapper.readValue(input, User.class);
//			// Once user object is created, storing the positive integer ID from the register service method
//		//	int id = as.register(user);
//			
//			// If ID is still 0, the registration was unsuccessful
//			if(id == 0) {
//				// Telling the client that registration failed
//				ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
//				ctx.result("Registration unsuccessful.");
//			} else {
//			// Proclaiming successful creation of new user
//				ctx.status (HttpCode.CREATED);
//				ctx.result("Registration successful.");
//			}
//			
//		// Catching any exceptions thrown
//		} catch (Exception e) {
//				// Returning 500 status
//				ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
//			
//				// If the exception has a message, send it back in the body
//				if(!e.getMessage().isEmpty()) {
//					ctx.result(e.getMessage());
//				}
//				
//				// Stacktrace to help debug the server
//				e.printStackTrace();
//
//			}
	};
		
		
//handleLoginMethod.png
/** This Javalin handler method leverages the Http call context to call the login AuthService method.
 * 
 * @param ctx
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

