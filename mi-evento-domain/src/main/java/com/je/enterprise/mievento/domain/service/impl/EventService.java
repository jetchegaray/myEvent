package com.je.enterprise.mievento.domain.service.impl;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.je.enterprise.mievento.api.dto.event.GuestStatusType;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.entity.common.event.GuestEntity;
import com.je.enterprise.mievento.domain.entity.common.event.InvitationStatusEntity;
import com.je.enterprise.mievento.domain.entity.common.event.UserEntity;
import com.je.enterprise.mievento.domain.exception.HttpEventException;

@Service
public class EventService {

	private UserService userService;
	private MailService mailService;
	private static final Logger logger = Logger.getLogger(EventService.class);

	@Autowired
	public EventService(MailService mailService,UserService userService) {
		this.mailService = mailService;
	}

	public void inviteToOf(EventEntity eventEntity, String userEmail, String guestEmail) {
		
		try{
			
			mailService.sendInvitation(eventEntity, userEmail, guestEmail);
			this.updateGuestStatus(eventEntity.getName(), userEmail, guestEmail);
			
		}catch(HttpEventException exception){
			throw exception;
		}catch (Exception e) {
			e.getStackTrace();
		}
		
	}

	private void updateGuestStatus(final String eventName, String userEmail,final String guestEmail) {
		
		UserEntity userEntity = userService.findByMail(userEmail);
		
		EventEntity eventEntity = Iterables.find(userEntity.getEvents(), new Predicate<EventEntity>() {

			@Override
			public boolean apply(EventEntity event) {
				return event.getName().equals(eventName);
			}
		});
		
		GuestEntity guestEntity = Iterables.find(eventEntity.getGuests(), new Predicate<GuestEntity>() {

			@Override
			public boolean apply(GuestEntity guest) {
				return guest.getEmail().equals(guestEmail);
			}
		});
		
		
		guestEntity.setInvitationStatusEntity(new InvitationStatusEntity(GuestStatusType.PENDING, DateTime.now().toDate()));
		userService.update(userEntity);
	}
	
	
	
	
}
