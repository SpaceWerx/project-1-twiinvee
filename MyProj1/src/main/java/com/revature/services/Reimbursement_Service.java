package com.revature.services;

public class Reimbursement_Service {
	
	
// Update Reimbursement Method	
	public void update(Reimbursement unprocessedReimbursement, int resolverId, Status updatedStatus) {
		
		for (Reimbursement reimbursement : reimbursements) {
			if (reimbursement.getId() == unprocessedReimbursement.getId() {
				reimbursement.setResolver(resolverId);
				reimbursement.setStatus(updatedStatus);
				return;
			}
		}
		throw new RuntimeException("There was an error processing this reimbursement, please try again");

	}
	
	
// Get Pending Reimbursement Method
		public List<Reimbursement> getPendingReimbursements() {
			List<Reimbursements> pendingReimbursements = new ArrayList>();
			
			for (Reimbursement reimbursement : reimbursements) { 
				if (reimbursement.getStatus() == Status.Pending) {
					pendingReimbursements.add(reimbursement);
		
				}
			}
			return pendingReimbursements;
		}
		
// Get Resolved Method
		public List<Reimbursement> getResolvedReimbursements() {
				List<Reimbursement> resolvedReimbursements = new ArrayList<>();
				
			for (Reimbursement reimbursement : reimbursements) { 
				if (reimbursement.getStatus() == Status. Approved || reimbursement.getStatus() == Status. Denied) {
				resolvedReimbursements.add(reimbursement);
				}
			}
			return resolvedReimbursements;
		}
		
		
//	Submit Reimbursement Method
		public void submitReimbursement (Reimbursement reimbursementToBeSubmitted) {
			Reimbursement LatestReimbursement = reimbursements.get(reimbursements.size() - 1);
			int id = latestReimbursement.getId() + 1; // New ID is 1 higher than the previous highest
			
			reimbursementToBeSubmitted.setId(id); 
			reimbursementToBeSubmitted.setStatus (Status.Pending);
			reimbursements.add(reimbursementToBeSubmitted);
			
			
}
		
//		Get by iD method
		public Reimbursement getReimbursementById(int id) {
			
			for (Reimbursement reimbursement : reimbursements) { 
				if (reimbursement.getId() == id) {
					return reimbursement;
				}
			}
				return null;
			}
	
		
		
//		Get by Author Method
		public List<Reimbursement> getReimbursementsByAuthor(int userid) {
			
			List<Reimbursements userReimbursements = new ArrayList<>O;
			
			for (Reimbursement r : reimbursements) { 
				if (r.getAuthor() == userId || r.getResolver() == userId) {
					User Reimbursements.add(r);
				}
			}
		
			return userReimbursements;
		}
}
		
		
		
		