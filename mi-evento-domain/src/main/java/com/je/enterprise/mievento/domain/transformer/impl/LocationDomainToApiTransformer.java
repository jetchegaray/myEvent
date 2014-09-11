package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.Location;
import com.je.enterprise.mievento.api.dto.StreetAddress;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;
import com.je.enterprise.mievento.domain.transformer.DomainToApiTransformer;

@Component
public class LocationDomainToApiTransformer extends DomainToApiTransformer<LocationEntity,Location> {

	private StreetAddressDomainToApiTransformer streetAddressDomainToApiTransformer;
	
	@Autowired
	public LocationDomainToApiTransformer(StreetAddressDomainToApiTransformer streetAddressDomainToApiTransformer) {
		this.streetAddressDomainToApiTransformer = streetAddressDomainToApiTransformer;
	}
	
	@Override
	public Location transform(LocationEntity domainObject) {
		StreetAddress streetAddress = streetAddressDomainToApiTransformer.transform(domainObject.getStreetAddress());
		return new Location(domainObject.getCountryCode(),domainObject.getProvince() , streetAddress);
	}
	
}
