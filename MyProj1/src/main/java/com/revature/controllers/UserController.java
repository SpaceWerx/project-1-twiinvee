package com.revature.controllers;

import java.util.List;

import com.google.gson.Gson;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

import io.javalin.http.Handler;
import io.javalin.http.HttpCode;

public class UserController {

	UserService us = new UserService();
	
// get all Users Handler	
	public Handler getUserHandler = (ctx) -> {

		List<User> allUsers = us.getAllUsers();
		
		Gson gson = new Gson();
		
		String JSONObject = gson.toJson(allUsers);
		
		ctx.result(JSONObject);
		ctx.status(200);
	};
	
	
	

	public Handler handleRegister() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Handler handleSubmit = (ctx) ->{
		
		String body = ctx.body();
		Gson gson = new Gson();
		
		Reimbursement type = gson.fromJson(body, Reimbursement.class);
		int id = ReimbursementService.submitReimbursement(type);
		
//		rs.addUser();
		
		if(id != 0) {
			// Proclaim victory and returning the ID 
			ctx.status(HttpCode.CREATED);
			ctx.result("Reimbursement submission was successful. The id is: " + id);
			
		} else {
			// Proclaim defeat if the ID was unchanged
			ctx.status(HttpCode.BAD_REQUEST); 
			ctx.result("Reimbursement submission was unsuccessful.");
		}

	};
		
	
	
	
// get by Username Handler	NOT MVP
//		public Handler getByUsernameHandler = (ctx) ->{
//
//			List<User> byUsername = us.getByUsername();
//			
//			Gson gson = new Gson();
//			
//			String JSONObject = gson.toJson(byUsername);
//			
//			ctx.result(JSONObject); 
//			ctx.status(200);
//		};


		
		
	
		

	
//Insert User Handler
	public Handler insertUsersHandler = (ctx) ->{
		String body = ctx.body();
		
		Gson gson = new Gson();
		
		User user = gson.fromJson(body, User.class);
		
		us.addUser(user);
		
		ctx.result("User successfully added!");
		ctx.status(201);
		
	};
}

