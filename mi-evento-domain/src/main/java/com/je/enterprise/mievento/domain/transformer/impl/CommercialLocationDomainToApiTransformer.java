package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.location.CommercialLocation;
import com.je.enterprise.mievento.api.dto.location.StreetAddress;
import com.je.enterprise.mievento.domain.entity.location.CommercialLocationEntity;
import com.je.enterprise.mievento.domain.transformer.DomainToApiTransformer;

@Component
public class CommercialLocationDomainToApiTransformer extends DomainToApiTransformer<CommercialLocationEntity,CommercialLocation> {

	private StreetAddressDomainToApiTransformer streetAddressDomainToApiTransformer;
	
	@Autowired
	public CommercialLocationDomainToApiTransformer(StreetAddressDomainToApiTransformer streetAddressDomainToApiTransformer) {
		this.streetAddressDomainToApiTransformer = streetAddressDomainToApiTransformer;
	}
	
	@Override
	public CommercialLocation transform(CommercialLocationEntity domainObject) {
		StreetAddress streetAddress = streetAddressDomainToApiTransformer.transform(domainObject.getStreetAddress());
		return new CommercialLocation(domainObject.getPlaceName(), domainObject.getCountryCode(), domainObject.getProvince(), domainObject.getCity(), streetAddress);
	}
	
}
