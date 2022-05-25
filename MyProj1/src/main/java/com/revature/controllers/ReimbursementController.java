package com.revature.controllers;

import com.revature.models.Reimbursement;
import com.revature.models.Status;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class ReimbursementController {

	
//	handleSubmitMethod.png
/** 
 * This Javelin handler method controls any reimbursement submission http calls
 */
	public Handler handleSubmit(Context ctx) {
	// Try+catch block to catch any exceptions
		try {
			// Storing the json object input as a string 
			String input = ctx.body();
			
			// Utilizing the object mapper to parse the input into a reimbursement object 
			Reimbursement reimbursement = objectMapper.readValue(input, Reimbursement.class);
			
	// Storing the positive integer ID that is returned from the service method
		int id = reimbursementService.submitReimbursement(reimbursement);
		
	// If the ID is still 0, the submission was unsuccessful
		if(id != 0) {
			// Proclaim victory and returning the ID 
			ctx.status(HttpCode.CREATED);
			ctx.result(""+id);
			
		} else {
			// Proclaim defeat if the ID was unchanged
			ctx.status(HttpCode.BAD_REQUEST); 
			ctx.result("Reimbursement submission was unsuccessful.");
		}
		
	// Catching any exception thrown 
		} catch (Exception e) {
			// Returning 500 status 
			ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
	
				// If the exception has a message, send it back in the body
			if(!e.getMessage().isEmpty()) {
				ctx.result(e.getMessage());
				
			}
			
			// Stacktrace to help debug the server
			e.printStackTrace(); 
	
		}
	}
	
	
//	handleProcessMethod.png
	
	public void handleProcess(Context ctx) {
	
			// Retrieving the header sent with the request that stores the ID of the current user 
		String authHeader = ctx.header("Current-user");
		
		// Making sure the client sent the header along with the request
		if(authHeader != null) {
			
			// Parsing the ID they sent in the header 
			int userId = Integer.parseInt(authHeader);
			
			// Try+catch block to catch any exceptions 
			try{
				// Retrieving the id from the path parameter as designated in the Javalin Routes Config 
				String reimbursementIdInput = ctx.pathParam("id"); 
				// Parsing the reimbursement ID from the path parameter 
				int id = Integer.parseInt(reimbursementIdInput);
				
			// Storing the new status sent with the request as a form parameter 
				String statusInput = ctx.formParam("status");
				
			// Calling the getReimbursementById Method and storing the return value 
				Reimbursement reimbursement = reimbursementService.getReimbursementById(id);
				
			// Checking to ensure that the reimbursement exists in the database before updating 
				if(reimbursement != null) {
					// Calling the update method and storing the updated reimbursement 
					Reimbursement processedReimbursement = reimbursementService.update(reimbursement, userId, Status.valueOf(statusInput));
			
						// Proclaim victory and return the processed reimbursement object 
					ctx.status(HttpCode.ACCEPTED); 
					ctx.json(processedReimbursement);
					
				} else {
					// Proclaim defeat and tell client processing was unsuccessful 
					ctx.status(HttpCode.BAD_REQUEST);
					ctx.result("Reimbursement processing was not successful");
				}
				
				
			} catch(Exception e) {
					// Returning 500 status
					ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
					
					// If the exception has a message, send it back in the body 
					if(!e.getMessage().isEmpty()) {
						ctx.result(e.getMessage());
						
					}
					
					
					// Stacktrace to help debug the server
					e.printStackTrace();
					
				}
			
				} else {
					// Telling the client they are missing the authHeader 
					ctx.status(HttpCode.FORBIDDEN); 
					ctx.result("Missing Current User Header with ID"); 
			
				}
			
			}
	
	
//	handleGetReimbursementsMethod.png
	

/*This javalin handler method is the entry point for any calls to get reimbursements with various filters. 
* Query Parameters such as /reimbursements?author=x or /reimbursements?status=x will instead leverage 
* the corresponding handler method.
*/

	public void handleGetReimbursements(Context ctx) { 
		if (ctx.queryParam("author") != null) {

				handleGetReimbursementsByAuthor(ctx); 
			} else if (ctx.queryParam("status") != null) {
					handleGetReimbursementByStatus(ctx); 
		
		}
	}
			
		
//	handleGetByStatusMethod.png
// method is entry point for any calls to get reimbursmeents by status

public void handleGetReimbursementByStatus(Context ctx) {

			// Try+catch block to catch any exceptions
			try{
				// Retrieving the status query parameter from the request 
				String statusParan = ctx.queryParam("status");
	
				// Getting the status desired as an Enum value 
				Status status = Status.valueOf(statusParam);
				
				// Retrieving all pending reimbursements or all resolved reimbursements 
				if(status == Status.Pending) {
					ctx.status(HttpCode.OK);
					ctx.json(reimbursementService.getPendingReimbursements()); 
				} else {
					ctx.status(HttpCode.OK);
					ctx.json(reimbursementService.getResolvedReimbursements());
				}
				
		} catch(Exception e) {
			// Returning 500 status 
			ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
				
			// If the exception has a message, send it back in the body 
			if(!e.getMessage().isEmpty()) {
				ctx.result(e.getMessage());
			}
			
		// Stacktrace to help debug the server 
			e.printStackTrace(); 
			
		}
}
		
			
		
//	handleGetByIdMethod.png

// This Javalin handler method is the entry point for any calls to get a reimbursement by reimbursement ID
	
	public void handleGetReimbursementById(Context ctx) {
		

		// Try+catch block to catch any exceptions
			try{
				// Retrieving the ID from the path parameter as designated by the Javalin routes config 
				String idParam = ctx.pathParam("id"); 
				
				// Parsing the ID from the path param 
				int id = Integer.parseInt(idParam);
			
				// Using the int ID to get the respective reimbursement 
				Reimbursement reimbursement = reimbursementService.getReimbursementById(id);
			
				// Checking to make sure reimbursement was retrieved 
				if(reimbursement != null) {
			
					// Proclain victory and return the respective reimbursement 
					ctx.status(HttpCode.OK);
					ctx.json(reimbursement); 
					
				} else {
					// Proclaim defeat and tell the client retrieval was unsuccessful 
					ctx.status(HttpCode.BAD_REQUEST);
					ctx.result("Could not retrieve the reimbursement");
					
				}
			
			} catch (Exception e) {
			
					// Returning 500 status 
					ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
			
					// If the exception has a message, send it back in the body
					if(!e.getMessage().isEmpty()) {
						ctx.result(e.getMessage());
						
					}
					
						// Stacktrace to help debug the server
						e.printStackTrace(); 
			}
			
	}
	
//	handleGetByAuthorMethod.png
	


// This Javalin handler method is the entry point for any calls to get reimbursements by author ID
public void handleGetReimbursementsByAuthor(Context ctx) {
		
	// Try+catch block to catch any exceptions
	try{
		
		// Retrieving the ID from the current user header 
		String idParam = ctx.queryParam("author");
		
		// Making sure the client sent the header with the request 
		if(idParam != null) {
			// Parsing to integer 
			int id = Integer.parseInt(idParam);
			
		// Checking if the user exists 
			if (userService.checkUserExistsById(id)) {
				// Proclaim victory 
				ctx.status(HttpCode.OK); 
				
				// Return all reimbursements submitted by the current user
				ctx.json(reimbursementService.getReimbursementsByAuthor(id)); 
			} else {
				// Proclaim default and tell the client that the user does not exist
				ctx.status(HttpCode.NOT_FOUND);
				ctx.result("Unable to retrieve reimbursements, current user is not in the database");
				} 
		} else {
			// Telling the client that they need to send the current-user header with the request
			ctx.status(HttpCode.BAD_REQUEST); 
			ctx.result("Missing Current User header");
		}
		
		
		} catch (Exception e) {
			// Returning 500 status 
			ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
			
			// If the exception has a message, send it back in the body 
			if(!e.getMessage().isEmpty()) {
					ctx.result(e.getMessage());
					
			}
			
			// Stacktrace to help debug the server 
			e.printStackTrace(); 
		}
	}
}
