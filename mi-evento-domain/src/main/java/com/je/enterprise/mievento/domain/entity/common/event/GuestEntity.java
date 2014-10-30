package com.je.enterprise.mievento.domain.entity.common.event;

import java.util.Date;

import org.apache.commons.lang3.tuple.Pair;

import com.je.enterprise.mievento.api.dto.event.StatusType;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;
import com.je.enterprise.mievento.domain.entity.wedding.PersonEntity;

public class GuestEntity extends PersonEntity{
	
	Pair<StatusType, Date> invitationStatus;

	public GuestEntity() {
	}
	
	public GuestEntity(String firstName,String lastName,String email,LocationEntity location) {
		super(firstName,lastName,email,location);
		this.invitationStatus = Pair.of(StatusType.UNINVITED, null);
	}
	
	public GuestEntity(String firstName,String lastName,String email,LocationEntity location,Pair<StatusType, Date> invitationStatus) {
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
