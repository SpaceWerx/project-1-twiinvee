package com.revature;




import java.sql.SQLException;

import org.postgresql.core.ConnectionFactory;

import com.revature.controllers.AuthController;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
import com.revature.services.MenuServices;

import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;
// or Launcher
public class Driver {
	

	public static void main(String[] args) {
		

//	try(Connection conn = ConnectionFactory.getConnection()) {
//		System.out.println("Connection Successful");
//	} catch(SQLException e) {
//		System.out.println("Connection failed");
//		e.printStackTrace();
//	}
//	}
//	
//	
	
//startWebServiceMethod.png
	// to start Javalin on desired port
//	public void start(int port) {
//		//Start Javalin instance on server
//		this.app.start(port);
//	}
//	
//	//create options obj for the service class
	
	// call the displaymenu method. 



		//Instantiating respective controllers to access methods for the routes configuration
		AuthController ac = new AuthController();
		UserController uc = new UserController();
		ReimbursementController rc = new ReimbursementController();
		
		//Creating the Javalin app to designate routes
		//Enabling CORS for all origins to avoid http request constraints
		Javalin app = Javalin.create(
				config -> {
					config.enableCorsForAllOrigins();
				}
			).start(4000);
			
			//Now we need our endpoints
			app.post("/login", ac.handleLogin); //MVP done WORK
			app.post("/register", ac.handleRegister); //MVP done WORK

			app.get("/Users", uc.getUserHandler); //mvp done WORK 
			//make sure sumbit handler matches register handler 
			
			app.post("/submit", rc.handleSubmit); //mvp done WORK
			
			app.get("/reimbursement", rc.handleGetReimbursement); // MVP done WORK
			
			//instead of handleProcess
			app.put("/denied", rc.handleDenied); // MVP *******near identicle to post
												//done WORK
			
			
			
//			app.get("/status", rc.handleGetReimbursementByStatus); // hard code in
			//	app.get("/userid", uc.handleGetById);
			// 	app.get("/username", uc.handleGetByUsername);		
			// app.get("/{id}",reimbursementController.handleGetReimbursementById);
			// app.get("/author", rc.handleGetReimbursementByAuthor);
	}	
}
		
//		//Setting the /login path
//		path("Login", () -> {
//			//routes the http post requests to /login to the respective authController method
//			post(authController::handleLogin);
//		});
//		
//		//Setting the /register path
//		path("register", () -> {
//			//routes the http post requests to /register to the respective authController method
//			post(authController::handleRegister);
//		});
//		
//		//Setting the /users path
//		path("users", () -> {
//			//Routes get requests to /users
//			//Query Params such as /users?username=x will use the respective userController method
//				get(userController::handleGetUsers);
//				
//				//Setting sub-path for /users/{id} where {id} is a path parameter
//				path("{id}", () -> {
//					//Routes get requests with id path parameter to respective userController method
//					get(userController::handleGetUserById);
//				});
//		});
//		
//		//Setting the /reimbursements path
//		path("reimbursements", () -> {
//				//Routes get requests to /reimbursements to the respective reimbursement Controller method
//				//Query Params such as /reimbursements?author=x will use respective reimbursement Controller method
//				get(reimbursementController::handleGetReimbursements);
//				//Routes post http requests to the submit method
//				post(reimbursementController::handleSubmit);
//				
//				//Setting sub-path for /reimbursements/{id} where {id} is a path parameter
//				path("{id}", () -> {
//					//Routes get requests with id path parameter to respective reimbursement Controller method
//					get (reimbursementController::handleGetReimbursementById);
//					//Routes put http requests for /reimbursements/{id} to process reimbursements
//					put (reimbursementController::handleProcess);
//				});
//			});
//	});


