package com.je.enterprise.mievento.domain.entity.place;

import java.util.List;

import com.je.enterprise.mievento.domain.entity.common.event.GuestEntity;

public class ControlContextTableEntity {

	private List<GuestEntity> guests;
	
	public ControlContextTableEntity() {
	}
	
	public ControlContextTableEntity(List<GuestEntity> guests) {
		this.guests = guests;
	}

	public List<GuestEntity> getGuests() {
		return guests;
	}

	public void setGuests(List<GuestEntity> guests) {
		this.guests = guests;
	}
	
}
