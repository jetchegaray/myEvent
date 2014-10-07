package com.je.enterprise.mievento.api.dto.event;

import com.je.enterprise.mievento.api.dto.location.Location;

public class Person {

	private String firstName;
	private String lastName;
	private String email;
	private Location location;
	
	public Person() {
	}

	public Person(String firstName, String lastName, String email,
			Location location) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.location = location;
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
