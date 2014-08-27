package com.je.enterprise.mievento.domain.entity.wedding;

import java.math.BigDecimal;
import java.util.List;

import org.mongodb.morphia.annotations.Reference;

import com.je.enterprise.mievento.domain.entity.common.Event;
import com.je.enterprise.mievento.domain.entity.common.Person;
import com.je.enterprise.mievento.domain.entity.common.Provider;

public class Wedding extends Event {

	private Person husband;
	private Person wife;
	private List<Present> presents;
	private Place place;
	private BigDecimal budget;
	private BigDecimal finalPrice;
	@Reference
	private List<Provider> providers;

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
