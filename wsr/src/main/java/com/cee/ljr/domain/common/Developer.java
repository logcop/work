package com.cee.ljr.domain.common;

public class Developer {

	private String nameInJira;	
	private String firstName;
	private String lastName;
	
	public Developer(String nameInJira, String firstName, String lastName) {
		this.nameInJira = nameInJira;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	/**
	 * @return the nameInJira
	 */
	public String getNameInJira() {
		return nameInJira;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
}
