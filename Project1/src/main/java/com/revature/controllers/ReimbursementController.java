package com.revature.controllers;

import java.util.List;

import com.google.gson.Gson;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;
import com.revature.services.ReimbursementService;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpCode;


public class ReimbursementController {

	ReimbursementService rs = new ReimbursementService();
//	handleSubmitMethod.png
/** 
 * This Javelin handler method controls any reimbursement submission http calls
 */
	public Handler handleSubmit = (ctx) ->{
		
		String body = ctx.body();
		Gson gson = new Gson();
		
		Reimbursement type = gson.fromJson(body, Reimbursement.class);
		int id = ReimbursementService.submitReimbursement(type);
		
		
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
		
	
	
	
//	handleGetReimbursementsMethod.png
	

/*This javalin handler method is the entry point for any calls to get reimbursements with various filters. 
* Query Parameters such as /reimbursements?author=x or /reimbursements?status=x will instead leverage 
* the corresponding handler method.
*/

	public Handler handleGetReimbursement = (ctx) -> {
		//get all reimbursement, you made list object 
		
		List<Reimbursement> allReimbursement = rs.getAllReimbursement();
		
		Gson gson = new Gson();
		
		String JSONObject = gson.toJson(allReimbursement);
		
		ctx.result(JSONObject);
		ctx.status(200);
		
//		if (ctx.queryParam("author") != null) {
//
//				handleGetReimbursementsByAuthor(ctx); 
//				
//			} else if (ctx.queryParam("status") != null) {
//					handleGetReimbursementByStatus(ctx); 
//		
//		}
	};
	
	
//public Handler handleApproved = (ctx) ->{
//		
////		String body = ctx.body();
////		Gson gson = new Gson();
////		
////		Reimbursement type = gson.fromJson(body, Reimbursement.class);
////		int id = rs.update(type);
////		
////			rs.update(unprocessed, resolver, Status.Approved);
////			ctx.status(HttpCode.CREATED);
////			ctx.result("Reimbursement was approved");
////			
////	};
//		
//	
//	
	


	
public Handler handleDenied = (ctx) ->{
		
	String body = ctx.body();
    Gson gson = new Gson();
    Reimbursement reimbursement = gson.fromJson(body, Reimbursement.class);
    int id = reimbursement.getResolver();

    Reimbursement processedReimbursement = rs.update(reimbursement, id, null); 
    if(processedReimbursement != null){
    ctx.status(HttpCode.ACCEPTED);


    } else {
            ctx.status(HttpCode.ACCEPTED);
            ctx.result("Reimbursement processing was not successful");
        }
		

	};
	
	
	
	
}
			
		
//	handleGetByStatusMethod.png
// method is entry point for any calls to get reimbursmeents by status

//public Handler handleGetReimbursementByStatus = (ctx) -> {
//
//	
//	String body = ctx.body();
//	Gson gson = new Gson();
//	rs.addUser(user);
//	
//	ctx.result("User successfully added!");
//	ctx.status(201);
//	
//};
//}
	
//			// Try+catch block to catch any exceptions
//			try{
//				// Retrieving the status query parameter from the request 
//				String statusParan = ctx.queryParam("status");
//	
//				// Getting the status desired as an Enum value 
//				Status status = Status.valueOf(statusParam);
//				
//				// Retrieving all pending reimbursements or all resolved reimbursements 
//				if(status == Status.Pending) {
//					ctx.status(HttpCode.OK);
//					ctx.json(reimbursementService.getPendingReimbursements()); 
//				} else {
//					ctx.status(HttpCode.OK);
//					ctx.json(reimbursementService.getResolvedReimbursements());
//				}
//				
//		} catch(Exception e) {
//			// Returning 500 status 
//			ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
//				
//			// If the exception has a message, send it back in the body 
//			if(!e.getMessage().isEmpty()) {
//				ctx.result(e.getMessage());
//			}
//			
//		// Stacktrace to help debug the server 
//			e.printStackTrace(); 
//			
//		}
//};
//		
//			
//		
////	handleGetByIdMethod.png
//
//// This Javalin handler method is the entry point for any calls to get a reimbursement by reimbursement ID
//	
//	public Handler handleGetReimbursementById = (ctx) -> {
//		
//
//		// Try+catch block to catch any exceptions
//			try{
//				// Retrieving the ID from the path parameter as designated by the Javalin routes config 
//				String idParam = ctx.pathParam("id"); 
//				// Parsing the ID from the path param 
//				int id = Integer.parseInt(idParam);
//			
//				// Using the int ID to get the respective reimbursement 
//				Reimbursement reimbursement = reimbursementService.getReimbursementById(id);
//			
//				// Checking to make sure reimbursement was retrieved 
//				if(reimbursement != null) {
//			
//					// Proclain victory and return the respective reimbursement 
//					ctx.status(HttpCode.OK);
//					ctx.json(reimbursement); 
//					
//				} else {
//					// Proclaim defeat and tell the client retrieval was unsuccessful 
//					ctx.status(HttpCode.BAD_REQUEST);
//					ctx.result("Could not retrieve the reimbursement");
//					
//				}
//			
//			} catch (Exception e) {
//			
//					// Returning 500 status 
//					ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
//			
//					// If the exception has a message, send it back in the body
//					if(!e.getMessage().isEmpty()) {
//						ctx.result(e.getMessage());
//						
//					}
//					
//						// Stacktrace to help debug the server
//						e.printStackTrace(); 
//			}
//			
//	};
//	
////	handleGetByAuthorMethod.png
//	
//
//
//// This Javalin handler method is the entry point for any calls to get reimbursements by author ID
//public Handler handleGetReimbursementsByAuthor = (ctx) -> {
//		
//	// Try+catch block to catch any exceptions
//	try{
//		
//		// Retrieving the ID from the current user header 
//		String idParam = ctx.queryParam("author");
//		
//		// Making sure the client sent the header with the request 
//		if(idParam != null) {
//			// Parsing to integer 
//			int id = Integer.parseInt(idParam);
//			
//		// Checking if the user exists 
//			if (userService.checkUserExistsById(id)) {
//				// Proclaim victory 
//				ctx.status(HttpCode.OK); 
//				
//				// Return all reimbursements submitted by the current user
//				ctx.json(reimbursementService.getReimbursementsByAuthor(id)); 
//			} else {
//				// Proclaim default and tell the client that the user does not exist
//				ctx.status(HttpCode.NOT_FOUND);
//				ctx.result("Unable to retrieve reimbursements, current user is not in the database");
//				} 
//		} else {
//			// Telling the client that they need to send the current-user header with the request
//			ctx.status(HttpCode.BAD_REQUEST); 
//			ctx.result("Missing Current User header");
//		}
//		
//		
//		} catch (Exception e) {
//			// Returning 500 status 
//			ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
//			
//			// If the exception has a message, send it back in the body 
//			if(!e.getMessage().isEmpty()) {
//					ctx.result(e.getMessage());
//					
//			}
//			
//			// Stacktrace to help debug the server 
//			e.printStackTrace(); 
//		}
//	};
	
