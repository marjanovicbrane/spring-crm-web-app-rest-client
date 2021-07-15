package com.brane.springdemo.model;

//THIS CLASS WE NEED TO HAVE LIKE EntityResponse CLASS, BECAUSE WHEN REST API RETURN US customers object
//IN JSON FORMAT, THEN JACKSON NEED TO CONVERT THAT JSON OBJECTS TO JAVA POJO.
public class Customer {

	
	//fields
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	
	//default constructor
	public Customer() {
		
	}

	
	//getter and setter methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	
	//toString method for debugging
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
		
}





