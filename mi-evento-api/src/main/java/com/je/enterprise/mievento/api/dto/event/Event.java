package com.je.enterprise.mievento.api.dto.event;

import java.util.Date;
import java.util.List;

import com.je.enterprise.mievento.api.dto.location.CommercialLocation;

public class Event {

	private String name;
	private Date eventDate;
	private CommercialLocation eventLocation;
	private List<Person> guests;
	
	public Event() {
	}
	
	public Event(String name, Date eventDate, CommercialLocation eventLocation,
			List<Person> guests) {
		this.name = name;
		this.eventDate = eventDate;
		this.eventLocation = eventLocation;
		this.guests = guests;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public CommercialLocation getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(CommercialLocation eventLocation) {
		this.eventLocation = eventLocation;
	}

	public List<Person> getGuests() {
		return guests;
	}

	public void setGuests(List<Person> guests) {
		this.guests = guests;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
