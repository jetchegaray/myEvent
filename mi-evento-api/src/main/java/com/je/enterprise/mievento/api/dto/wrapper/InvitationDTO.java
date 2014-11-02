package com.je.enterprise.mievento.api.dto.wrapper;

import com.je.enterprise.mievento.api.dto.event.Event;

public class InvitationDTO {

	private Event event;
	private String userEmail;
	private String guestEmail;

	public InvitationDTO() {
	}
	
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getGuestEmail() {
		return guestEmail;
	}

	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}

}
