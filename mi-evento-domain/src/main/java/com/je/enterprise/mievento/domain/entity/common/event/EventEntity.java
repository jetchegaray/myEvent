package com.je.enterprise.mievento.domain.entity.common.event;

import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Embedded;

import com.google.common.collect.Lists;
import com.je.enterprise.mievento.domain.entity.location.CommercialLocationEntity;

public class EventEntity {

	private String name;
	private Date eventDate;
	@Embedded
	private CommercialLocationEntity eventLocation;
	@Embedded
	private List<GuestEntity> guests = Lists.<GuestEntity>newArrayList();
	
	
	public EventEntity() {
	}
	
	public EventEntity(String name, Date eventDate, CommercialLocationEntity eventLocation,
			List<GuestEntity> guests) {
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

	public List<GuestEntity> getGuests() {
		return guests;
	}

	public void setGuests(List<GuestEntity> guests) {
		this.guests = guests;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
