package com.je.enterprise.mievento.domain.entity.common.event;

import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Reference;

import com.google.common.collect.Lists;
import com.je.enterprise.mievento.domain.entity.location.CommercialLocationEntity;

public class EventEntity {

	private String name;
	private Date eventDate;
	@Embedded
	private CommercialLocationEntity eventLocation;
	@Embedded
	private List<PersonEntity> guests = Lists.<PersonEntity>newArrayList();
	
	public EventEntity() {
	}
	
	public EventEntity(String name, Date eventDate, CommercialLocationEntity eventLocation,
			List<PersonEntity> guests) {
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

	public CommercialLocationEntity getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(CommercialLocationEntity eventLocation) {
		this.eventLocation = eventLocation;
	}

	public List<PersonEntity> getGuests() {
		return guests;
	}

	public void setGuests(List<PersonEntity> guests) {
		this.guests = guests;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
