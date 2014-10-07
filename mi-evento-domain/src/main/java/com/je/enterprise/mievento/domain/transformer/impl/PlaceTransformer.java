package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.event.wedding.Place;
import com.je.enterprise.mievento.api.dto.location.Location;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;
import com.je.enterprise.mievento.domain.entity.wedding.PlaceEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;

@Component
public class PlaceTransformer implements Transformer<PlaceEntity, Place>{

	private LocationTransformer locationTransformer;
	
	@Autowired
	public PlaceTransformer(
			LocationTransformer locationTransformer) {
		this.locationTransformer = locationTransformer;
	}


	@Override
	public Place transformDomainToApi(PlaceEntity domainObject) {
		Location location = locationTransformer.transformDomainToApi(domainObject.getLocation());
		return new Place(domainObject.getBusinessName(), domainObject.getDescription(), location, domainObject.getEmail(), domainObject.getCellPhone(), domainObject.getPhone(), domainObject.getPrice(), domainObject.getEstimatedPrice(), domainObject.getM2(), domainObject.getEstimatedQuantityTables(), domainObject.getEstimatedQuantityPerson(), domainObject.getPicture(), domainObject.getProviderType());
	}

	@Override
	public PlaceEntity transformApiToDomain(Place apiObject) {
		LocationEntity locationEntity = locationTransformer.transformApiToDomain(apiObject.getLocation());
		return new PlaceEntity(apiObject.getBusinessName(), apiObject.getDescription(), locationEntity, apiObject.getEmail(), apiObject.getCellPhone(), apiObject.getPhone(), apiObject.getPrice(), apiObject.getEstimatedPrice(), apiObject.getM2(), apiObject.getEstimatedQuantityTables(), apiObject.getEstimatedQuantityPerson(), apiObject.getPicture(), apiObject.getProviderType());
	}

	
}
