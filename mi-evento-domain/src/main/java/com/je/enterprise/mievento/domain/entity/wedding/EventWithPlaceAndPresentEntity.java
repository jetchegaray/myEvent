package com.je.enterprise.mievento.domain.entity.wedding;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Embedded;

import com.je.enterprise.mievento.api.dto.event.EventType;
import com.je.enterprise.mievento.api.dto.event.eventWithplace.EventWithPlaceAndPresent;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.entity.common.event.GuestEntity;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.common.event.TaskEntity;
import com.je.enterprise.mievento.domain.entity.location.CommercialLocationEntity;
import com.je.enterprise.mievento.domain.entity.place.PlaceEntity;
import com.je.enterprise.mievento.domain.transformer.impl.events.VisitorTransformer;

public class EventWithPlaceAndPresentEntity extends EventEntity {

	@Embedded
	private List<PresentsEntity> presents;
	private PlaceEntity place;
	private BigDecimal budget;

	public EventWithPlaceAndPresentEntity(){
	}
	
	public EventWithPlaceAndPresentEntity(String name, Date initialDate,Date finalDate,
			List<GuestEntity> guests, List<TaskEntity> tasks,
			List<PresentsEntity> presents, PlaceEntity place, BigDecimal budget, List<ProviderEntity> providers,EventType eventType) {
		super(name, initialDate, finalDate, guests, tasks, providers, eventType);
		this.presents = presents;
		this.place = place;
		this.budget = budget;
	}

	
	public List<PresentsEntity> getPresents() {
		return presents;
	}

	public void setPresents(List<PresentsEntity> presents) {
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

	public EventWithPlaceAndPresent accept(VisitorTransformer visitorTransformer){
		return visitorTransformer.visitTransformer(this);
	}
}
