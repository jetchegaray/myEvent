package com.je.enterprise.mievento.domain.entity.wedding;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Embedded;

import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.event.EventType;
import com.je.enterprise.mievento.api.dto.event.wedding.Wedding;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.entity.common.event.GuestEntity;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.common.event.TaskEntity;
import com.je.enterprise.mievento.domain.entity.location.CommercialLocationEntity;
import com.je.enterprise.mievento.domain.entity.place.PlaceEntity;
import com.je.enterprise.mievento.domain.transformer.impl.events.VisitorTransformer;

public class WeddingEntity extends EventEntity {

	@Embedded
	private PersonEntity husband;
	@Embedded
	private PersonEntity wife;
	@Embedded
	private List<PresentEntity> presents;
	private PlaceEntity place;
	private BigDecimal budget;
	private BigDecimal finalPrice;

	public WeddingEntity(){
	}
	
	public WeddingEntity(String name, Date initialDate,Date finalDate,
			CommercialLocationEntity eventLocation, List<GuestEntity> guests, List<TaskEntity> tasks, PersonEntity husband, PersonEntity wife,
			List<PresentEntity> presents, PlaceEntity place, BigDecimal budget,
			BigDecimal finalPrice, List<ProviderEntity> providers) {
		super(name, initialDate, finalDate, eventLocation, guests, tasks, providers, EventType.WEDDING);
		this.husband = husband;
		this.wife = wife;
		this.presents = presents;
		this.place = place;
		this.budget = budget;
		this.finalPrice = finalPrice;
		
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

	public Wedding accept(VisitorTransformer visitorTransformer){
		return visitorTransformer.visitTransformer(this);
	}
}
