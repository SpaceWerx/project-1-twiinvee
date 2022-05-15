package com.revature.mockdata;

public class MockUserData {
	private final List<User> users = new ArrayList<>();
	
	public MockUserData() {
		
		User GENERIC_EMPLOYEE_1 = new User(id: 1, username: "genericEmployee1", password: "genericPassword1", Role.Employee);
		User GENERIC_EMPLOYEE_2 = new User(id: 2, username: "genericEmployee2", password: "genericPassword2", Role.Employee);
		User GENERIC_EMPLOYEE_3 = new User(id: 3, username: "genericEmployee3", password: "genericPassword3", Role.Employee);
		User GENERIC_FINANCE_MANAGER_1 = new User(id: 4, username: "genericManager1", password: "genericPassword1", Role.Manager);
		User GENERIC_FINANCE_MANAGER_2 = new User(id: 5, username: "genericManager2", password: "genericPassword2", Role.Manager);
		User GENERIC_FINANCE_MANAGER_3 = new User(id: 6, username: "genericManager3", password: "genericPassword3", Role.Manager);
		
		users.add(GENERIC_EMPLOYEE_1);
		users.add(GENERIC_EMPLOYEE_2);
		users.add(GENERIC_EMPLOYEE_3);
		users.add(GENERIC_FINANCE_MANAGER_1);
		users.add(GENERIC_FINANCE_MANAGER_2);
		users.add(GENERIC_FINANCE_MANAGER_3);
	}
	
	
	public List<User> getUsers() { return users; L}
}
