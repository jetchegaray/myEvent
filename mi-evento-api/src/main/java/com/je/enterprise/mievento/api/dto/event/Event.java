package com.je.enterprise.mievento.api.dto.event;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.je.enterprise.mievento.api.dto.location.CommercialLocation;
import com.je.enterprise.mievento.api.dto.provider.Provider;


@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

	private String name;
	private Date eventDate;
	private CommercialLocation eventLocation;
	private List<Guest> guests;
	private List<Task> tasks;
	private List<Provider> providers;
	
	public Event() {
	}
	
	public Event(String name, Date eventDate, CommercialLocation eventLocation,
			List<Guest> guests,List<Provider> providers) {
		this.name = name;
		this.eventDate = eventDate;
		this.eventLocation = eventLocation;
		this.guests = guests;
		this.providers = providers;
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

	public List<Guest> getGuests() {
		return guests;
	}

	public void setGuests(List<Guest> guests) {
		this.guests = guests;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public List<Provider> getProviders() {
		return providers;
	}

	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}

}
