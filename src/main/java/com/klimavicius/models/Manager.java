package com.klimavicius.models;

import org.mindrot.jbcrypt.BCrypt;

public class Manager {
	
	int managerId;
	String firstName, lastName, email, password;
	
	@Override
	public String toString() {
		return "Manager [managerId=" + managerId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", password=" + password + "]";
	}

	public Manager(int managerId, String firstName, String lastName, String email, String password) {
		String encryptedHash = BCrypt.hashpw(password, BCrypt.gensalt());
		this.managerId = managerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = encryptedHash;
	}

	public Manager(String firstName, String lastName, String email, String password) {
		String encryptedHash = BCrypt.hashpw(password, BCrypt.gensalt());
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = encryptedHash;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
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
