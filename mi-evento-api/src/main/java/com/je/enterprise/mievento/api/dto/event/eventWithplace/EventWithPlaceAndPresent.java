package com.je.enterprise.mievento.api.dto.event.eventWithplace;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.event.EventType;
import com.je.enterprise.mievento.api.dto.event.Guest;
import com.je.enterprise.mievento.api.dto.event.Task;
import com.je.enterprise.mievento.api.dto.place.Place;
import com.je.enterprise.mievento.api.dto.provider.Provider;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventWithPlaceAndPresent extends Event {

	private List<Presents> presents;
	private Place place;
	private BigDecimal budget;

	
	public EventWithPlaceAndPresent() {
	}
	
	public EventWithPlaceAndPresent(String name, Date initialDate,Date finalDate, List<Guest> guests, List<Task> tasks,
			List<Presents> presents, Place place, BigDecimal budget, List<Provider> providers, EventType eventType) {
		
		super(name, initialDate, finalDate, guests, tasks, providers, eventType);
		this.presents = presents;
		this.place = place;
		this.budget = budget;
	}
	
	public List<Presents> getPresents() {
		return presents;
	}

	public void setPresents(List<Presents> presents) {
		this.presents = presents;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}


}
