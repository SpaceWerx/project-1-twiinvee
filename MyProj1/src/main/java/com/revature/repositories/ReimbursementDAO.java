package com.revature.repositories;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementType;
import com.revature.models.Status;
import com.revature.utilities.ConnectionFactoryUtility;

public class ReimbursementDAO {

//	UpdateMethod.png
	/**
	 * The update method is meant to process reimbursements
	 * This method is void because we are only using it to update specific fields in a given record
	 */
	
	public void update(Reimbursement unprocessedReimbursement) {
		
		//	try+catch block to catch sql exception that can be thrown with connection
		try (Connection connection = ConnectionFactoryUtility.getConnection()) {
			
			//Write the query that we want to send to the database and assign it to a String
			String sql = "UPDATE ers_reimbursements SET resolver =?, status = ?::status WHERE id = ?";
			
			//Creating a prepared statement with the sql string we created
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			//Setting the update parameters (?'s) with their respective values.
			preparedStatement.setInt(1, unprocessedReimbursement.getResolver());
			preparedStatement.setObject(2, unprocessedReimbursement.getStatus());
			preparedStatement.setInt(3, unprocessedReimbursement.getId());
			
			// executing the record update
			preparedStatement.executeUpdate();
			
			
			// Proclaim victory
			System.out.println("Reimbursement Successfully Updated!");
			
		} catch (SQLException e) {
			System.out.println("Updating Failed!"); // Proclaim defeat
			e.printStackTrace(); //useful debugging tool 	
		}
	}

//	getReimbursemetnsByUserMethod.png
	/**
	 * this method is intended to extract any reimbursements from the database
	 * that were submitted by a specific user, whose ID is passed in as a parameter
	 * 
	 *  @return List of reimbursements created by author with matching useId
	 */
public List<Reimbursement> getReimbursementsByUser (int userId) {
	
	//	try+catch block to catch sql exception that can be thrown with connection 
	try (Connection connection = ConnectionFactoryUtility.getConnection()) {
		
		//SQL statement prepared as a string
		// In this instance, we are filtering reimbursements by an author (user) id
		String sql = "select * from ers_reimbursements where author =?";
		
		// Preparing the sql statement to be executed once we fill the query parameters
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		// Filling the missing query value (?) with the method parameter (userId)
		preparedStatement.setInt(1, userId);
		
		
		// Building a sql result set from the excecution of the query statement
		ResultSet resultSet = preparedStatement.executeQuery();
		
		// Initializing a new Reimbursement array list to house and return with the data from the database 
		List<Reimbursement> reimbursement = new ArrayList<>();
		
		//This while loop will continue to add reimbursements to the list
		// until all the data from the result set has run out
		while (resultSet.next()) {
			
			// Adding reimbursements to the list with the data extracted from the database 
			reimbursement.add(new Reimbursement(
						resultSet.getInt("id"),
						resultSet.getInt("author"),
						resultSet.getInt("resolver"),
						resultSet.getString("description"),
						ReimbursementType.valueOf(resultSet.getString("type")),
						Status.valueOf(resultSet.getString("status")),
						resultSet.getDouble("amount")
						));
		}
		
		
		// Return the list of reimbursements that have a matching author (user) id
		return reimbursement;
	
		
	} catch (SQLException e) {
		
		
		// Catching the sql exception (this is a good place to utilize custom exception handling)
		System.out.println("Something Went Wrong Obtaining Your List!");
		e.printStackTrace();
	}
	
	
	// Fail-safe if the try+catch block does not run
	return null;
}

	
	
//	getReimbursementsByIdMethod.png
	
	/**
	 * Should retrieve a Reimbursement from the DB with the corresponding id or null if there is no match.
	 */
	public Reimbursement getReimbursementById(int id) {
		
//		try+catch block to catch sql exception that can be thrown with connection
	try(Connection connection = ConnectionFactoryUtility.getConnection()) {
		
		String sql = "select * from ers_reimbursements where id = ?";
		
		//when we need parameter we need to use a PREPARED Statement, as opposed to a Statement (seen above)
		PreparedStatement preparedStatement = connection.prepareStatement(sql); //prepareStatement() as opposed to createStatement()
		
		
		//insert the method argument (int id) as the first (and only) variable in our SQL query
		preparedStatement.setInt(1, id); // the 1 here is referring to the first parameter (?) found in our SQL String
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		// if there are results in the result set...
		if (resultSet.next()) {
			
			//return a reimbursement with the data to be returned to the service layer
			return new Reimbursement (
					resultSet.getInt("id"),
					resultSet.getInt("author"),
					resultSet.getInt("resolver"),
					resultSet.getString("description"),
					ReimbursementType.valueOf(resultSet.getString("type")),
					Status.valueOf(resultSet.getString("status")),
					resultSet.getDouble("amount")
					);					
		}
		
	} catch (SQLException e) {
		System.out.println("Something Went Wrong Obtaining Your List!");
		e.printStackTrace();
	}
	
	
	// Fail-safe if the try+catch block does not run
	return null;
		
		
	}

	
//	getByStatusMethod.png
	
/**
 * Should retrieve a List of Reimbursements from the DB with the corresponding status or an empty List if there are no matches.
 */
	
	public List<Reimbursement> getByStatus(Status status) {
			List<Reimbursement> byStatus = new ArrayList<>();
			for(Reimbursement r: getAllReimbursement()) {

			if(r.getStatus() == status) {
				byStatus.add(r);
				
			}
		}
		for(Reimbursement bs : byStatus) {
			System.out.println(bs.getAuthor() + " " + bs.getType() + " " + bs.getDescription() 
            + " " + bs.getAmount() + " " + bs.getStatus());
		}
		return byStatus;
		
		}
		
		
		//see Oscar Code above
	
//		try (Connection connection = ConnectionFactoryUtility.getConnection()) {
//	
//		String sql = "select * from ers_reimbursements where status = ?::status";
//		
//		PreparedStatement preparedStatement = connection.prepareStatement(sql);
//		
//		preparedStatement.getString(1,status.toString()); //
//		
//		
//		ResultSet resultSet = preparedStatement.executeQuery();
//		
//		//ALL THE ABOVE CODE MAKES A CALL TO OUR DATABASE. Now we need to store the data in an ArrayList.
//		
//		//create an empty ArrayList to be filled with the data from the database
//		List<Reimbursement> reimbursements = new ArrayList<>(); //Upcasting, we are instantiatiing an ArrayList
//		
//		//while there are results in the results
//		while(resultSet.next()) {
//			
//			//Use the all args constructor to create new User obj from each returned row from DB
//			reimbursements.add(new Reimbursement(
//					resultSet.getInt("id"),
//					resultSet.getInt("author"),
//					resultSet.getInt("resolver"),
//					resultSet.getString("description"),
//					ReimbursementType.valueOf(resultSet.getString("type")),
//					Status.valueOf(resultSet.getString("status")),
//					resultSet.getDouble("amount")
//					));					
//		}
//					
//		//when there are no more results in resultSet, while loop will break
//		//then, return the populated ArrayList of Users
//		return reimbursements;
//				
//		
//		} catch (SQLException e) {
//			System.out.println("Something went wrong Obtaining the reimbursements!");
//			e.printStackTrace();
//		}
//		
//		
//		// Fail-safe if the try+catch block does not run
//		return null;
//		
//	}
//	
	
//	getAllReimbursementsMethod.png
/**
 * retrieves all reimbursement records from the database
 * This method should return an array list of reimbursements or null if there is a connection error
 */
public List<Reimbursement> getAllReimbursement() {
	
	try (Connection connection = ConnectionFactoryUtility.getConnection()) {
	
		List<Reimbursement> reimbursements = new ArrayList<>();
		
		// write out the appropriate sql query string
		String sql = "Select * from ers_reimbursements";
		
		// we can use createStatement in this case bc we do not have any parameters in the query
		Statement statement = connection.createStatement();
		
		//storing the record from the query in a result set
		ResultSet resultSet = statement.executeQuery(sql);
		
		
		//Looping over the records from the query to then add to the return list
		while(resultSet.next()) {
			reimbursements.add(new Reimbursement(
					resultSet.getInt("id"),
					resultSet.getInt("author"),
					resultSet.getInt("resolver"),
					resultSet.getString("description"),
					ReimbursementType.valueOf(resultSet.getString("type")),
					Status.valueOf(resultSet.getString("status")),
					resultSet.getDouble("amount")
					));					
		}
		return reimbursements;
		
	} catch (SQLException sqlException) {
		System.out.println("Something went wrong with the database!");
		sqlException.printStackTrace();
	}
	
	// Fail-safe if the try+catch block does not run
	return null;
	
}
	

	
//	createMethod.png
public int create(Reimbursement reimbursementToBeSubmitted) {
	
	try (Connection connection = ConnectionFactoryUtility.getConnection()) {
		
		
		//writing out sql insert string to create a new record
		// ask database to return new id after entry
		String sql = "INSERT INTO ers_reimbursements (author, description, type, status, amount"
				+"VALUES (?, ?, ?::type, ?::status, ?)"
				+ "RETURNING ers_reimbursements.id";
		
		// We must use a prepared statement because we have parameters
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setInt(1, reimbursementToBeSubmitted.getAuthor());
		preparedStatement.setString(2, reimbursementToBeSubmitted.getDescription());
		preparedStatement.setObject(3, reimbursementToBeSubmitted.getType().name());
		preparedStatement.setObject(4, reimbursementToBeSubmitted.getStatus().name());
		preparedStatement.setDouble(5, reimbursementToBeSubmitted.getAmount());
		
		
		ResultSet resultSet;
		
		//checking if sql query executed and return reimbursement record with new id
		if((resultSet = preparedStatement.executeQuery()) !=null) {
			//must call this to get the returned reimbursement record id
			resultSet.next();
			//finally returning the new id
			return resultSet.getInt(1);
		}
		
		
	} catch (SQLException e) {
		System.out.println("Creating reimbursement has failed");
		e.printStackTrace();
	}
	//Fail safe if try catch block does not run
	return 0;
}
	
}
	
	
	
	
