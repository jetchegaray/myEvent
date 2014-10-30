package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.event.Guest;
import com.je.enterprise.mievento.api.dto.location.Location;
import com.je.enterprise.mievento.domain.entity.common.event.GuestEntity;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;

@Component
public class GuestTransformer extends Transformer<GuestEntity, Guest>{

	
	private LocationTransformer locationTransformer;
	
	@Autowired
	public GuestTransformer(LocationTransformer locationTransformer) {
		this.locationTransformer = locationTransformer;
	}

	@Override
	public Guest transformDomainToApi(GuestEntity domainObject) {
		Location  location = locationTransformer.transformAndValidateDomainToApi(domainObject.getLocation()); 
		return new Guest(domainObject.getFirstName(),domainObject.getLastName(), domainObject.getEmail(), location,domainObject.getInvitationStatus());
	}

	@Override
	public GuestEntity transformApiToDomain(Guest apiObject) {
		LocationEntity  locationEntity = locationTransformer.transformAndValidateApiToDomain(apiObject.getLocation()); 
		return new GuestEntity(apiObject.getFirstName(),apiObject.getLastName(), apiObject.getEmail(), locationEntity,apiObject.getInvitationStatus());
	}

}
