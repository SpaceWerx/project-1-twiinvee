package com.revature.controllers;

import java.util.List;

import com.google.gson.Gson;
import com.revature.models.User;
import com.revature.services.UserService;

import io.javalin.http.Handler;

public class UserController {

	UserService us = new UserService();
	
// get all Users Handler	
	public Handler getUsersHandler = (ctx) ->{

		List<User> allUsers = us.getAllUsers();
		
		Gson gson = new Gson();
		
		String JSONObject = gson.toJson(allUsers);
		
		ctx.result(JSONObject);
		ctx.status(200);
	};
	
	// get by Username Handler	
		public Handler getByUsernameHandler = (ctx) ->{

			List<User> byUsername = us.getByUsername();
			
			Gson gson = new Gson();
			
			String JSONObject = gson.toJson(byUsername);
			
			ctx.result(JSONObject);
			ctx.status(200);
		};


		
		
		
// get by ID Handler	
		public Handler getByIdHandler = (ctx) ->{

			List<User> allUsers = us.getUserbyId();
			
			Gson gson = new Gson();
			
			String JSONObject = gson.toJson(allUsers);
			
			ctx.result(JSONObject);
			ctx.status(200);
		};
	
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

