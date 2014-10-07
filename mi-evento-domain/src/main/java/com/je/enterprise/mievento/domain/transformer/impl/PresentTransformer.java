package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.event.wedding.Present;
import com.je.enterprise.mievento.api.dto.location.CommercialLocation;
import com.je.enterprise.mievento.domain.entity.location.CommercialLocationEntity;
import com.je.enterprise.mievento.domain.entity.wedding.PresentEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;

@Component
public class PresentTransformer extends Transformer<PresentEntity, Present>{

	private CommercialLocationTransformer commercialLocationTransformer;

	@Autowired
	public PresentTransformer(
			CommercialLocationTransformer commercialLocationTransformer) {
		this.commercialLocationTransformer = commercialLocationTransformer;
	}

	@Override
	public Present transformDomainToApi(PresentEntity domainObject) {
		CommercialLocation locationCredit = commercialLocationTransformer.transformAndValidateDomainToApi(domainObject.getLocationCredit());	
		return new Present(domainObject.getType(), domainObject.getCredit(), locationCredit);
	}

	@Override
	public PresentEntity transformApiToDomain(Present apiObject) {
		CommercialLocationEntity locationCredit = commercialLocationTransformer.transformAndValidateApiToDomain(apiObject.getLocationCredit());	
		return new PresentEntity(apiObject.getType(), apiObject.getCredit(), locationCredit);
	}
	
	
}
