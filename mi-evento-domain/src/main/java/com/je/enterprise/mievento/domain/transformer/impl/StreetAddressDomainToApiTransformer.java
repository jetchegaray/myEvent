package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.location.Location;
import com.je.enterprise.mievento.api.dto.location.StreetAddress;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;
import com.je.enterprise.mievento.domain.entity.location.StreetAddressEntity;
import com.je.enterprise.mievento.domain.transformer.DomainToApiTransformer;

@Component
public class StreetAddressDomainToApiTransformer extends DomainToApiTransformer<StreetAddressEntity,StreetAddress> {

	@Override
	public StreetAddress transform(StreetAddressEntity domainObject) {
		return new StreetAddress(domainObject.getStreet(), domainObject.getNumber(), domainObject.getAdditionalInfo(), domainObject.getNeighborhood());
	}

	
}
