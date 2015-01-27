package com.je.enterprise.mievento.api.dto.event.wedding;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.event.EventType;
import com.je.enterprise.mievento.api.dto.event.Guest;
import com.je.enterprise.mievento.api.dto.event.Person;
import com.je.enterprise.mievento.api.dto.location.CommercialLocation;
import com.je.enterprise.mievento.api.dto.place.Place;
import com.je.enterprise.mievento.api.dto.provider.Provider;


public class Wedding extends Event {

	private Person husband;
	private Person wife;
	private List<Present> presents;
	private Place place;
	private BigDecimal budget;
	private BigDecimal finalPrice;

	
	public Wedding() {
	}
	
	public Wedding(String name, Date initialDate,Date finalDate,
			CommercialLocation eventLocation, List<Guest> guests,Person husband, Person wife,
			List<Present> presents, Place place, BigDecimal budget,
			BigDecimal finalPrice, List<Provider> providers) {
		
		super(name, initialDate, finalDate, eventLocation, guests, providers, EventType.WEDDING);
		this.husband = husband;
		this.wife = wife;
		this.presents = presents;
		this.place = place;
		this.budget = budget;
		this.finalPrice = finalPrice;
	}
	
	public Person getHusband() {
		return husband;
	}

	public void setHusband(Person husband) {
		this.husband = husband;
	}

	public Person getWife() {
		return wife;
	}

	public void setWife(Person wife) {
		this.wife = wife;
	}

	public List<Present> getPresents() {
		return presents;
	}

	public void setPresents(List<Present> presents) {
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

	public BigDecimal getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}


}
