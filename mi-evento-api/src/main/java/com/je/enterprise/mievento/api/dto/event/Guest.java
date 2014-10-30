package com.je.enterprise.mievento.api.dto.event;

import java.util.Date;

import org.apache.commons.lang3.tuple.Pair;

import com.je.enterprise.mievento.api.dto.location.Location;


public class Guest extends Person {

	Pair<StatusType, Date> invitationStatus;

	public Guest() {
	}
	
	public Guest(String firstName,String lastName,String email,Location location,Pair<StatusType, Date> invitationStatus) {
		super(firstName,lastName,email,location);
		this.invitationStatus = invitationStatus;
	}

	public Pair<StatusType, Date> getInvitationStatus() {
		return invitationStatus;
	}

	public void setInvitationStatus(Pair<StatusType, Date> invitationStatus) {
		this.invitationStatus = invitationStatus;
	}
	
}
