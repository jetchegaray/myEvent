package com.je.enterprise.mievento.domain.service.impl;

import java.util.UUID;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.je.enterprise.mievento.api.dto.event.GuestStatusType;
import com.je.enterprise.mievento.domain.dao.impl.InvitationDAO;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.entity.common.event.GuestEntity;
import com.je.enterprise.mievento.domain.entity.common.event.InvitationStatusEntity;
import com.je.enterprise.mievento.domain.entity.common.event.UserEntity;
import com.je.enterprise.mievento.domain.entity.invitations.InvitationEntity;
import com.je.enterprise.mievento.domain.exception.HttpEventException;
import com.je.enterprise.mievento.domain.service.helper.CRUDHelper;

@Service
public class EventService {

	private UserService userService;
	private MailService mailService;
	private CRUDHelper<InvitationEntity, ObjectId> crudHelperInvitation;
	
	private static final Logger logger = Logger.getLogger(EventService.class);

	@Autowired
	public EventService(MailService mailService,CRUDHelper<InvitationEntity, ObjectId> crudHelperInvitation) {
		this.crudHelperInvitation = crudHelperInvitation;
		this.mailService = mailService;
	}

	public void inviteTo(EventEntity eventEntity, String userEmail, String guestEmail) {
		
		try{
			UUID key = UUID.randomUUID();
			mailService.sendInvitation(eventEntity, userEmail, guestEmail,key.toString());
			crudHelperInvitation.update(new InvitationEntity(eventEntity.getName(), userEmail, guestEmail, key.toString()));
		}catch(HttpEventException exception){
			throw exception;
		}catch (Exception e) {
			e.getStackTrace();
		}
		
	}

	private void updateGuestStatus(final String eventName, String userEmail,final String guestEmail,GuestStatusType statusUpdate) {
		
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
		
		guestEntity.setInvitationStatusEntity(new InvitationStatusEntity(statusUpdate, DateTime.now().toDate()));
		userService.update(userEntity);
	}
	
	
	
	public void updateInvitation(String key, GuestStatusType statusUpdate){
		
		try{
			InvitationEntity invitation = ((InvitationDAO)crudHelperInvitation.getDao()).findByKey(key);
			Validate.notNull(invitation);
			this.updateGuestStatus(invitation.getEventName(), invitation.getUserMail(), invitation.getGuestMail(),GuestStatusType.CONFIRMED);
			
		}catch(Exception ex){
			ex.getStackTrace();
			logger.debug(ExceptionUtils.getMessage(ex));
		}
		
	}
	
	
	
	
	
}
