package com.revature.mockdata;

import java.util.ArrayList;

public class MockReimbursementData {
	private final List<Reimbursement> reimbursements = new ArrayList<>();
	
	public  MockReimbursementData() {
	
		MockUserData userData = new MockUserData();
		
		Reimbursement REIMBURSEMENT_TO_PROCESS_1 = new Reimbursement( 1, 1, 0, "Oracle Java Certification", ReimbursementType.Other, Status.Pending, 250.00);
		Reimbursement REIMBURSEMENT_TO_PROCESS_2 = new Reimbursement( 2, 2, 0, description: "Travel to Reston HQ", ReimbursementType.Travel, Status.Pending, 600.00);
		Reimbursement REIMBURSEMENT_APPROVED_1 = new Reimbursement(3, 1, 3, "Free lunch offer from Sean", ReimbursementType.Food, Status.Approved, 25.00);
		Reimbursement REIMBURSEMENT_APPROVED_2 = new Reimbursement(14, 2,  4, "2-night hotel stay near Orlando Office for visit", ReimbursementType.Lodging, Status.Approved, 300.00);
		Reimbursement REIMBURSEMENT_DENIED_1 = new Reimbursement( 5, 1, 3, "Rental Car to drive from REston to Orlando", ReimbursementType.Travel, Status.Denied, 2500.00);

		reimbursements.add(REIMBURSEMENT_TO_PROCESS_1);
		reimbursements.add(REIMBURSEMENT_TO_PROCESS_2);
		reimbursements.add(REIMBURSEMENT_APPROVED_1);
		reimbursements.add(REIMBURSEMENT_APPROVED_2);
		
	}

public List<Reimbursement> getReimbursements() { return reimbursements; }

}
