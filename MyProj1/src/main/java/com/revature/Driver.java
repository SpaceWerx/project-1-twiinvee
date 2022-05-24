package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

public class Driver {

	try(Connection conn = ConnectionFactory.getConnection()) {
		System.out.println("Connection Successful");
	} catch(SQLException e) {
		System.out.println("Connection failed");
		e.printStackTrace();
	}
	
	//start Javalin on desired port
	
	public void start(int port) {
		//Start Javalin instance on server
		this.app.start(port)
	}
	
	//create options obj for the service class
	
	// call the displaymenu method. 

}

}
