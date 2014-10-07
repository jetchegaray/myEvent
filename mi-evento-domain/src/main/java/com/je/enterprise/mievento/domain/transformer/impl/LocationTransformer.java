package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.location.Location;
import com.je.enterprise.mievento.api.dto.location.StreetAddress;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;
import com.je.enterprise.mievento.domain.entity.location.StreetAddressEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;

@Component
public class LocationTransformer implements Transformer<LocationEntity,Location> {

	private StreetAddressTransformer streetAddressTransformer;
	
	@Autowired
	public LocationTransformer(StreetAddressTransformer streetAddressTransformer) {
		this.streetAddressTransformer = streetAddressTransformer;
	}
	
	@Override
	public Location transformDomainToApi(LocationEntity domainObject) {
		StreetAddress streetAddress = streetAddressTransformer.transformDomainToApi(domainObject.getStreetAddress());
		return new Location(domainObject.getCountryCode(),domainObject.getProvince() ,domainObject.getCity(), streetAddress);
	}

	@Override
	public LocationEntity transformApiToDomain(Location apiObject) {
		StreetAddressEntity streetAddressEntity = streetAddressTransformer.transformApiToDomain(apiObject.getStreetAddress());
		return new LocationEntity(apiObject.getCountryCode(),apiObject.getProvince() ,apiObject.getCity(), streetAddressEntity);
	}
	
}
