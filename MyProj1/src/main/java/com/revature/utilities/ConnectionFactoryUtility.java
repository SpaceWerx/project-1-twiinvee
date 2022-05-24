package com.revature.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <p>This ConnectionFactoryUtility class follows the Singleton Design Pattern and facilitates obtaining a connection to a Database for the ERS application.</p>
 * <p>Following the Singleton Design Pattern, the provided Constructor is private, and you obtain an instance via the {@link ConnectionFactoryUtility#getInstance()} method.</p>
 */

//This Class contains the java logic that gets a connection to our database
//It will have a method called getConnection() which will return a Connection object
//We will need this Connection object in our repository package to interact with our database
public class ConnectionFactoryUtility {
	
    private static ConnectionFactoryUtility instance;

    private ConnectionFactoryUtility() {
        super();
    }

    /**
     * <p>This method follows the Singleton Design Pattern to restrict this class to only having 1 instance.</p>
     * <p>It is invoked via:</p>
     *
     * {@code ConnectionFactoryUtility.getInstance()}
     */
    public static ConnectionFactoryUtility getInstance() {
        if(instance == null) {
            instance = new ConnectionFactoryUtility();
        }

        return instance;
    }

    /**
     * <p>The {@link ConnectionFactoryUtility#getConnection()} method is responsible for leveraging a specific Database Driver to obtain an instance of the {@link java.sql.Connection} interface.</p>
     * <p>Typically, this is accomplished via the use of the {@link java.sql.DriverManager} class.</p>
     * @throws SQLException 
     */
    public static Connection getConnection() throws SQLException {
        
    	//For compatibility with other technologies, we need to register our PostgreSQL Driver
    	//This process makes the application aware of what database Driver (SQL dialect) we're using
    	try {
    		Class.forName("org.postgresql.Driver"); //try to find and return the psotgresql Driver Class
    	} catch (ClassNotFoundException e) {
    		System.out.println("CLASS WASN'T FOUND");
    		e.printStackTrace(); //this will print the exception message to the console
    	}
    	
    	//we need to provide our database credentials
    	
    	
    	//the url to my database schema
    	String url = "jdbc:postgresql://javaproject1.ccbxgbomitee.us-east-1.rds.amazonaws.com:5432/postgres?currentSchema=p1demo"; // NEED TO CHANGE SERVER URL (from DBeaver) (LAst thing for Week 2)
    	
    	
    	//your postgres username 
    	String username = "postgres";
    	
    	
    	//your postgres password 
    	String password = "password"; 
    	//This is what actually returns our Connection object. (Note how this method has a return type of Connection)
    	return DriverManager.getConnection(url, username, password);
    	
    }
}