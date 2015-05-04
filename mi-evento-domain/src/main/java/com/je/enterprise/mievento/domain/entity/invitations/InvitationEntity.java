package com.je.enterprise.mievento.domain.entity.invitations;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;

import com.je.enterprise.mievento.domain.dao.BaseEntity;

@Entity("invitations")
public class InvitationEntity extends BaseEntity{

	//FIXME debe ser un id
	private String eventName;
	private String userMail;
	private String guestMail;
	
	@Indexed
	private String 	key;

	public InvitationEntity() {
	}

	public InvitationEntity(String eventName, String userMail, String guestMail,String key) {
		this.eventName = eventName;
		this.userMail = userMail;
		this.guestMail = guestMail;
		this.key = key;
	}


	public String getEventName() {
		return eventName;
	}



	public void setEventName(String eventName) {
		this.eventName = eventName;
	}



	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getGuestMail() {
		return guestMail;
	}

	public void setGuestMail(String guestMail) {
		this.guestMail = guestMail;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	

}
