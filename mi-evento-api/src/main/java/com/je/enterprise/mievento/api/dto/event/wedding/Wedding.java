package com.je.enterprise.mievento.api.dto.event.wedding;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.event.Person;
import com.je.enterprise.mievento.api.dto.location.CommercialLocation;
import com.je.enterprise.mievento.api.dto.provider.Provider;

public class Wedding extends Event {

	private Person husband;
	private Person wife;
	private List<Present> presents;
	private Place place;
	private BigDecimal budget;
	private BigDecimal finalPrice;
	private List<Provider> providers;

	public Wedding(String name, Date eventDate,
			CommercialLocation eventLocation, List<Person> guests,Person husband, Person wife,
			List<Present> presents, Place place, BigDecimal budget,
			BigDecimal finalPrice, List<Provider> providers) {
		super(name, eventDate, eventLocation, guests);
		this.husband = husband;
		this.wife = wife;
		this.presents = presents;
		this.place = place;
		this.budget = budget;
		this.finalPrice = finalPrice;
		this.providers = providers;
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

	public List<Provider> getProviders() {
		return providers;
	}

	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}

}
