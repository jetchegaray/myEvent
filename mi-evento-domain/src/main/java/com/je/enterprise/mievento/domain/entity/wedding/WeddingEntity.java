package com.je.enterprise.mievento.domain.entity.wedding;

import java.math.BigDecimal;
import java.util.List;

import org.mongodb.morphia.annotations.Reference;

import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.entity.common.event.PersonEntity;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;

public class WeddingEntity extends EventEntity {

	private PersonEntity husband;
	private PersonEntity wife;
	private List<PresentEntity> presents;
	private PlaceEntity place;
	private BigDecimal budget;
	private BigDecimal finalPrice;
	@Reference
	private List<ProviderEntity> providers;

	public PersonEntity getHusband() {
		return husband;
	}

	public void setHusband(PersonEntity husband) {
		this.husband = husband;
	}

	public PersonEntity getWife() {
		return wife;
	}

	public void setWife(PersonEntity wife) {
		this.wife = wife;
	}

	public List<PresentEntity> getPresents() {
		return presents;
	}

	public void setPresents(List<PresentEntity> presents) {
		this.presents = presents;
	}

	public PlaceEntity getPlace() {
		return place;
	}

	public void setPlace(PlaceEntity place) {
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

	public List<ProviderEntity> getProviders() {
		return providers;
	}

	public void setProviders(List<ProviderEntity> providers) {
		this.providers = providers;
	}

}
