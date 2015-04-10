package com.je.enterprise.mievento.api.dto.event;

import com.je.enterprise.mievento.api.dto.location.Location;


public class Guest extends Person {

	InvitationStatus invitationStatus;

	public Guest() {
	}
	
	public Guest(String firstName,String lastName,String email,Location location,InvitationStatus invitationStatus) {
		super(firstName,lastName,email,location);
		this.invitationStatus = invitationStatus;
	}

	public InvitationStatus getInvitationStatus() {
		return invitationStatus;
	}

	public void setInvitationStatus(InvitationStatus invitationStatus) {
		this.invitationStatus = invitationStatus;
	}
	
}
