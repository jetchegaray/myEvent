package com.je.enterprise.mievento.domain.entity.common.event;

import org.mongodb.morphia.annotations.Embedded;

import com.je.enterprise.mievento.domain.entity.location.LocationEntity;

public class PersonEntity {

	private String firstName;
	private String lastName;
	private String email;
	@Embedded
	private LocationEntity location;
	
	public PersonEntity() {
	}

	public PersonEntity(String firstName, String lastName, String email,
			LocationEntity location) {
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

	public LocationEntity getLocation() {
		return location;
	}

	public void setLocation(LocationEntity location) {
		this.location = location;
	}

}
