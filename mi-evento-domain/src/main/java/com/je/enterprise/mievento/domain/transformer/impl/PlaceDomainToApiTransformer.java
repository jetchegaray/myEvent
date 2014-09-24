package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.event.wedding.Place;
import com.je.enterprise.mievento.api.dto.location.Location;
import com.je.enterprise.mievento.domain.entity.wedding.PlaceEntity;
import com.je.enterprise.mievento.domain.transformer.DomainToApiTransformer;

@Component
public class PlaceDomainToApiTransformer extends DomainToApiTransformer<PlaceEntity, Place>{

	private LocationDomainToApiTransformer locationDomainToApiTransformer;
	
	@Autowired
	public PlaceDomainToApiTransformer(
			LocationDomainToApiTransformer locationDomainToApiTransformer) {
		this.locationDomainToApiTransformer = locationDomainToApiTransformer;
	}

	@Override
	public Place transform(PlaceEntity domainObject) {
		
		Location location = locationDomainToApiTransformer.transform(domainObject.getLocation());
		return new Place(domainObject.getBusinessName(), domainObject.getDescription(), location, domainObject.getEmail(), domainObject.getCellPhone(), domainObject.getPhone(), domainObject.getPrice(), domainObject.getEstimatedPrice(), domainObject.getM2(), domainObject.getEstimatedQuantityTables(), domainObject.getEstimatedQuantityPerson(), domainObject.getPicture(), domainObject.getProviderType());
	}

	
}
