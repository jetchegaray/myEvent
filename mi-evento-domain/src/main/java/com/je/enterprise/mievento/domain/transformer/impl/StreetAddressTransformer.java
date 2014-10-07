package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.location.Location;
import com.je.enterprise.mievento.api.dto.location.StreetAddress;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;
import com.je.enterprise.mievento.domain.entity.location.StreetAddressEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;

@Component
public class StreetAddressTransformer implements Transformer<StreetAddressEntity,StreetAddress> {

	@Override
	public StreetAddress transformDomainToApi(StreetAddressEntity domainObject) {
		return new StreetAddress(domainObject.getStreet(), domainObject.getNumber(), domainObject.getAdditionalInfo(), domainObject.getNeighborhood());
	}

	@Override
	public StreetAddressEntity transformApiToDomain(StreetAddress apiObject) {
		return new StreetAddressEntity(apiObject.getStreet(), apiObject.getNumber(), apiObject.getAdditionalInfo(), apiObject.getNeighborhood());
	}

	
}
