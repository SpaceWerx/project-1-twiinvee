package schemaInitialization;

public class DefaultUsers {

	
//	--This is meant to create two default users in the ers_users table
//	--The only way to create a Manager, currently, is to put it directly in the database.
//	--You will use the manager credentials to test your manager's functionality and reimbursement processing.
	
	INSERT INTO ers_users (username, password, role)
	VALUES('default', 'guest', 'Employee'), ('admin', 'admin', 'Manager');

}
