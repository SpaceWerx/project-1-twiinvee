package schemaInitialization;

import com.revature.models.CREATE;
import com.revature.models.FOREIGN;
import com.revature.models.REFERENCES;
import com.revature.models.id;
import com.revature.models.status;
import com.revature.models.type;

public class Ers_reimbursementsTable {

//	--This will create a table for the reimbursement data
	CREATE TABLE ers_reimbursements (
	
//	--The ID is SERIAL type to increment with every new row added
//  --The ID is denoted as the primary key
	id SERIAL PRIMARY KEY,
	
	
//	--The author and resolver are both integer values
//	--They store the primary key (ID) of the respective user
//	--Author should never be null when a new entry is created; however, the resolver will be until processing
	author INT NOT NULL,
	resolver INT,
	
	
//	--The description must be stored as TEXT to ensure that longer description strings can be store appropriately
//	--This field should never be null when a new entry is created
	description TEXT NOT NULL,
	
//	--The type and status are stored as strings and should never be null upon entry
	type VARCHAR (250) NOT NULL,
	status VARCHAR (250) NOT NULL,
	
	
//	--The amount must be stored as a float to account for the double datatype
//	--This value will have 2 decimal places and should never be null upon entry
	amount FLOAT NOT NULL,
	
	
//	--We need to denote the foreign key relationships that the author and resolver IDs have to the ers_users table
//	--As you can see, we create new constraints that reference the ers_users primary key (id)
	CONSTRAINT fk_author
		FOREIGN KEY (author)
			REFERENCES ers_users(id),
	CONSTRAINT fk_resolver
		FOREIGN KEY (resolver)
			REFERENCES ers_users(id)
);
}
