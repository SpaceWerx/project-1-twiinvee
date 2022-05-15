package com.revature.models;



public class User {
	
	private int id;
	private String username;
	private String password;
	private String role;
	
	
	
// boiler plate code 
//	1. Generate constructor from super class: default constructor to make obj. This is no-args 
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


//  2. generate constructor using fields, all box checked. This is all-args 
	
	public User(int id, String username, String password, String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}

//	3. Gen getters and setters - select all
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}

	
	
	
	// example gen cons using fields of username and pw ONLY
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	


	
}
