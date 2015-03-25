package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.event.eventWithplace.Presents;
import com.je.enterprise.mievento.api.dto.location.CommercialLocation;
import com.je.enterprise.mievento.domain.entity.location.CommercialLocationEntity;
import com.je.enterprise.mievento.domain.entity.wedding.PresentsEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;

@Component
public class PresentTransformer extends Transformer<PresentsEntity, Presents>{

	private CommercialLocationTransformer commercialLocationTransformer;

	@Autowired
	public PresentTransformer(
			CommercialLocationTransformer commercialLocationTransformer) {
		this.commercialLocationTransformer = commercialLocationTransformer;
	}

	@Override
	public Presents transformDomainToApi(PresentsEntity domainObject) {
		CommercialLocation locationCredit = commercialLocationTransformer.transformAndValidateDomainToApi(domainObject.getLocationCredit());	
		return new Presents(domainObject.getDescription(), domainObject.getTotalBudget(), locationCredit, null);
	}

	@Override
	public PresentsEntity transformApiToDomain(Presents apiObject) {
		CommercialLocationEntity locationCredit = commercialLocationTransformer.transformAndValidateApiToDomain(apiObject.getLocationCredit());	
		return new PresentsEntity(apiObject.getDescriptions(), apiObject.getTotalBudget(), locationCredit, null);
	}
	
	
}
