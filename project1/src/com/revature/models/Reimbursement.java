package com.revature.models;

public class Reimbursement {
	
	private int id;
	private String author;
	private int resolver;
	private String description;
	private ReimbursementType type;
	private Status status;
	private double amount;
	
//	Boiler plate code
	// gen super class

	
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	
//  2. generate constructor using fields, all box checked. This is all-args 
public Reimbursement(int id, String author, int resolver, String description, ReimbursementType type, Status status,
		double amount) {
	super();
	this.id = id;
	this.author = author;
	this.resolver = resolver;
	this.description = description;
	this.type = type;
	this.status = status;
	this.amount = amount;
}


public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}


public String getAuthor() {
	return author;
}


public void setAuthor(String author) {
	this.author = author;
}


public int getResolver() {
	return resolver;
}


public void setResolver(int resolver) {
	this.resolver = resolver;
}


public String getDescription() {
	return description;
}


public void setDescription(String description) {
	this.description = description;
}


public ReimbursementType getType() {
	return type;
}


public void setType(ReimbursementType type) {
	this.type = type;
}


public Status getStatus() {
	return status;
}


public void setStatus(Status status) {
	this.status = status;
}


public double getAmount() {
	return amount;
}


public void setAmount(double amount) {
	this.amount = amount;
}
	

//	3. Gen getters and setters - select all


	

}
