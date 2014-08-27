package com.je.enterprise.mievento.domain.entity.common;

import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Embedded;

public class Event {

	private Date eventDate;
	@Embedded
	private Location location;
	@Embedded
	private List<Person> guests;

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Person> getGuests() {
		return guests;
	}

	public void setGuests(List<Person> guests) {
		this.guests = guests;
	}

}
