package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.event.Guest;
import com.je.enterprise.mievento.api.dto.event.InvitationStatus;
import com.je.enterprise.mievento.api.dto.location.Location;
import com.je.enterprise.mievento.domain.entity.common.event.GuestEntity;
import com.je.enterprise.mievento.domain.entity.common.event.InvitationStatusEntity;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;

@Component
public class GuestTransformer extends Transformer<GuestEntity, Guest>{

	
	private LocationTransformer locationTransformer;
	private InvitationStatusTransformer InvitationStatusTransformer;
	
	@Autowired
	public GuestTransformer(LocationTransformer locationTransformer,InvitationStatusTransformer invitationStatusTransformer) {
		this.locationTransformer = locationTransformer;
		this.InvitationStatusTransformer = invitationStatusTransformer;
	}

	@Override
	public Guest transformDomainToApi(GuestEntity domainObject) {
		Location  location = locationTransformer.transformAndValidateDomainToApi(domainObject.getLocation()); 
		InvitationStatus invitationStatus = InvitationStatusTransformer.transformAndValidateDomainToApi(domainObject.getInvitationStatusEntity());
		return new Guest(domainObject.getFirstName(),domainObject.getLastName(), domainObject.getEmail(), location, invitationStatus);
	}

	@Override
	public GuestEntity transformApiToDomain(Guest apiObject) {
		LocationEntity  locationEntity = locationTransformer.transformAndValidateApiToDomain(apiObject.getLocation()); 
		InvitationStatusEntity invitationStatusEntity = InvitationStatusTransformer.transformAndValidateApiToDomain(apiObject.getInvitationStatus());
		return new GuestEntity(apiObject.getFirstName(),apiObject.getLastName(), apiObject.getEmail(), locationEntity, invitationStatusEntity);
	}

}
