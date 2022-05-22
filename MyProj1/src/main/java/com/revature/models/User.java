package com.revature.models;



public class User {
	
	private int Id;
	private String username;
	private String password;
	private Role role;
	
	
	
// boiler plate code 
//	1. Generate constructor from super class: default constructor to make obj. This is no-args 
	
	public User() {
		super();
	}


//  2. generate constructor using fields, all box checked. This is all-args 
	
	public User(int Id, String username, String password, Role role) {
		super();
		this.Id = Id;
		this.username = username;
		this.password = password;
		this.role = role;
	}




	
//	3. Gen getters and setters - select all
	
	public int getId() {
		return Id;
	}

	
	public void setId(int Id) {
		this.Id = Id;
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


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	
}
