package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.location.CommercialLocation;
import com.je.enterprise.mievento.api.dto.location.StreetAddress;
import com.je.enterprise.mievento.domain.entity.location.CommercialLocationEntity;
import com.je.enterprise.mievento.domain.entity.location.StreetAddressEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;

@Component
public class CommercialLocationTransformer extends Transformer<CommercialLocationEntity,CommercialLocation> {

	private StreetAddressTransformer streetAddressTransformer;
	
	@Autowired
	public CommercialLocationTransformer(StreetAddressTransformer streetAddressTransformer) {
		this.streetAddressTransformer = streetAddressTransformer;
	}

	@Override
	public CommercialLocation transformDomainToApi(CommercialLocationEntity domainObject) {
		StreetAddress streetAddress = streetAddressTransformer.transformAndValidateDomainToApi(domainObject.getStreetAddress());
		return new CommercialLocation(domainObject.getPlaceName(), domainObject.getCountryCode(), domainObject.getProvince(), domainObject.getCity(), streetAddress);
	}

	@Override
	public CommercialLocationEntity transformApiToDomain(CommercialLocation apiObject) {
		StreetAddressEntity streetAddress = streetAddressTransformer.transformAndValidateApiToDomain(apiObject.getStreetAddress());
		return new CommercialLocationEntity(apiObject.getPlaceName(), apiObject.getCountryCode(), apiObject.getProvince(), apiObject.getCity(), streetAddress);
	}

	
}
