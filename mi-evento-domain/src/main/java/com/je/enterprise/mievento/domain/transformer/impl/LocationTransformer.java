package com.je.enterprise.mievento.domain.transformer.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.location.Location;
import com.je.enterprise.mievento.api.dto.location.StreetAddress;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;
import com.je.enterprise.mievento.domain.entity.location.StreetAddressEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;

@Component
public class LocationTransformer extends Transformer<LocationEntity,Location> {

	private StreetAddressTransformer streetAddressTransformer;
	
	@Autowired
	public LocationTransformer(StreetAddressTransformer streetAddressTransformer) {
		this.streetAddressTransformer = streetAddressTransformer;
	}
	
	@Override
	public Location transformDomainToApi(LocationEntity domainObject) {
		StreetAddress streetAddress = streetAddressTransformer.transformAndValidateDomainToApi(domainObject.getStreetAddress());
		return new Location(domainObject.getCountryCode(),domainObject.getProvince() ,domainObject.getCity(), streetAddress, domainObject.getLat(), domainObject.getLng());
	}

	@Override
	public LocationEntity transformApiToDomain(Location apiObject) {
		StreetAddressEntity streetAddressEntity = streetAddressTransformer.transformAndValidateApiToDomain(apiObject.getStreetAddress());
		return new LocationEntity(apiObject.getCountryCode(),apiObject.getProvince() ,apiObject.getCity(), streetAddressEntity, apiObject.getLat(), apiObject.getLng());
	}
	
}
