package com.klimavicius.models;

import org.mindrot.jbcrypt.BCrypt;

public class Employee {
	
	private int employeeId;
	private String firstName, lastName, email, password;
	
	
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", password=" + password + "]";
	}

	//default constructor
	public Employee(){
		super();
	}

	public Employee(int employeeId, String firstName, String lastName, String email, String password) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public Employee(String firstName, String lastName, String email, String password) {
		String encryptedHash = BCrypt.hashpw(password, BCrypt.gensalt());
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = encryptedHash;
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		String encryptedHash = BCrypt.hashpw(password, BCrypt.gensalt());
		this.password = encryptedHash;
	}
	
	

}
