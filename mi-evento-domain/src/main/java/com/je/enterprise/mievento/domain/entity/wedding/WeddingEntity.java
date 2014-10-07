package com.je.enterprise.mievento.domain.entity.wedding;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Reference;

import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.entity.common.event.PersonEntity;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.location.CommercialLocationEntity;

public class WeddingEntity extends EventEntity {

	@Embedded
	private PersonEntity husband;
	@Embedded
	private PersonEntity wife;
	@Embedded
	private List<PresentEntity> presents;
	@Embedded
	@Reference
	private List<ProviderEntity> providers;
	private PlaceEntity place;
	private BigDecimal budget;
	private BigDecimal finalPrice;

	public WeddingEntity(){
	}
	
	public WeddingEntity(String name, Date eventDate,
			CommercialLocationEntity eventLocation, List<PersonEntity> guests,PersonEntity husband, PersonEntity wife,
			List<PresentEntity> presents, PlaceEntity place, BigDecimal budget,
			BigDecimal finalPrice, List<ProviderEntity> providers) {
		super(name, eventDate, eventLocation, guests);
		this.husband = husband;
		this.wife = wife;
		this.presents = presents;
		this.place = place;
		this.budget = budget;
		this.finalPrice = finalPrice;
		this.providers = providers;
	}

	
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
