package schemaInitialization;

import com.revature.models.CREATE;
import com.revature.models.NOT;
import com.revature.models.id;
import com.revature.models.password;
import com.revature.models.role;
import com.revature.models.username;

public class Ers_usersTable {

	
//	Schema Initialization
//--This will create a table for the user data
	CREATE TABLE ers_users (
//	--The ID is SERIAL type to increment with every new row added
//	--The ID is denoted as the primary key
	id SERIAL PRIMARY KEY,
//	--Username, password, and roles are VARCHAR type with a max of 250 characters to store then as strings
//	--Username must be unique because we will query for single results of a given username
//	-- None of these columns will be null when a new entry is created
	
	username VARCHAR (250) UNIQUE NOT NULL,
	password VARCHAR (250) NOT NULL,
	role VARCHAR (250) NOT NULL
	);

}
