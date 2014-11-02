package com.je.enterprise.mievento.domain.entity.common.event;

import org.joda.time.DateTime;

import com.je.enterprise.mievento.api.dto.event.StatusType;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;
import com.je.enterprise.mievento.domain.entity.wedding.PersonEntity;

public class GuestEntity extends PersonEntity{
	
	InvitationStatusEntity invitationStatusEntity;

	public GuestEntity() {
	}
	
	public GuestEntity(String firstName,String lastName,String email,LocationEntity location) {
		super(firstName,lastName,email,location);
		this.invitationStatusEntity = new InvitationStatusEntity(StatusType.PENDING, DateTime.now().toDate());
	}
	
	public GuestEntity(String firstName,String lastName,String email,LocationEntity location,InvitationStatusEntity invitationStatusEntity) {
		super(firstName,lastName,email,location);
		this.invitationStatusEntity = invitationStatusEntity;
	}

	public InvitationStatusEntity getInvitationStatusEntity() {
		return invitationStatusEntity;
	}

	public void setInvitationStatusEntity(InvitationStatusEntity invitationStatusEntity) {
		this.invitationStatusEntity = invitationStatusEntity;
	}
	

}
