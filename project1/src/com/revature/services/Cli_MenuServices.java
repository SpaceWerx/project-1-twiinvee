package com.revature.services;

import com.revature.utilities.User;

public class Cli_MenuServices {
	
	
//	Submit reimbursement Helper Method
	public void submitreimbursement (User employee) {
		Reimbursement reimbursementToBeSubmitted = new Reimbursement();
		reimbursementToBeSubmitted.setAuthor(employee.getId());
		System.out.println("What type of reimbursement would you like to submit?");
		System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
		System.out.println("1 -> Lodging");
		System.out.println("2 -> Travel");
		System.out.println("3 -> Food");
		System.out.println("4 -> Other");
		int typeDecision = promptSelection( .valid Entries 1,2,3,4);
		
		
		switch (typeDecision) {
		}
			case 1:
			reimbursementToBeSubmitted.setType (ReimbursementType.Lodging);
			break;
			
			case 2:
		reimbursementToBeSubmitted.setType(ReimbursementType.Travel);
			break;
			
			case 3:
		reimbursementToBeSubmitted.setType(ReimbursementType. Food);
			break;
			
			case 4:
		reimbursementToBeSubmitted.setType (ReimbursementType.Other);
			break;
		}
		
		
		System.out.println("Please enter the dollar amount you are requesting to be reimbursed: ");
		System.out.print("$");
		reimbursementToBeSubmitted.setAmount (parseDoubleInput(fetchInput());
		if (reimbursementToBeSubmitted.getAmount() <= 0) {
		System.out.println("Invalid Amount has been entered, please input a correct dollar amount.");
		boolean valid = false;
		while (!valid) {
		reimbursementToBeSubmitted.setAmount (parseDoubleInput (fetchInput());
		if (reimbursementToBeSubmitted.getAmount() != 0) {
		valid = true;
		System.out.println("Please enter a description/reason for your reimbursement request.");
		reimbursementToBeSubmitted.setDescription(scan.nextLine());
		if (reimbursementToBe Submitted.getDescription().trim().equals("") {
		System.out.println("You cannot submit a request with an empty description, please explain the reason for your request.");
		boolean valid = false;
		while (!valid) {
		reimbursementToBeSubmitted.setDescription(scan.nextLine());
		if (!reimbursementToBeSubmitted.getDescription().trim().equals("")) {
		valid = true;
		rService.submitReimbursement(reimbursementToBeSubmitted);
		}

//	
	
}
