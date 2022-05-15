package schemaInitialization;

public class DropTablesAndNewTypes {

	
	
//	--Remove the tables and all contents if they exists in the current schema
//	--WARNING this is only meant to initialize the database structure, do not use otherwise
//	--It WILL delete all of your data
//	--We use CASCADE to ensure that all references get deleted as well.
	DROP TABLE IF EXISTS ers_users CASCADE;
	DROP TABLE IF EXISTS ers_reimbursements CASCADE;
	
	
//	--Creating the necessary enum types for data storage
	create type role as enum ('Employee', 'Manager');
	create type type as enum ('Lodging', 'Travel', 'Food', 'Other');
	create type status as enum ('Pending', 'Approved', 'Denied');

}
