package com.je.enterprise.mievento.domain.entity.place;

import java.util.List;

import com.je.enterprise.mievento.api.dto.event.Guest;

public class ControlContextTableEntity {

	private List<Guest> guests;
	
	public ControlContextTableEntity() {
	}
	
	public ControlContextTableEntity(List<Guest> guests) {
		this.guests = guests;
	}

	public List<Guest> getGuests() {
		return guests;
	}

	public void setGuests(List<Guest> guests) {
		this.guests = guests;
	}
	
}
