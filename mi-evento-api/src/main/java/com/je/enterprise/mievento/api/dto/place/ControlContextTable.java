package com.je.enterprise.mievento.api.dto.place;

import java.util.List;

import com.je.enterprise.mievento.api.dto.event.Guest;

public class ControlContextTable {

	private List<Guest> guests;
	
	public ControlContextTable() {
	}
	
	public ControlContextTable(List<Guest> guests) {
		this.guests = guests;
	}

	public List<Guest> getGuests() {
		return guests;
	}

	public void setGuests(List<Guest> guests) {
		this.guests = guests;
	}
	
}
