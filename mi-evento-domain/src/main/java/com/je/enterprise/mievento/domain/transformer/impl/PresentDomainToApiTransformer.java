package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.event.wedding.Present;
import com.je.enterprise.mievento.api.dto.location.CommercialLocation;
import com.je.enterprise.mievento.domain.entity.wedding.PresentEntity;
import com.je.enterprise.mievento.domain.transformer.DomainToApiTransformer;

@Component
public class PresentDomainToApiTransformer extends DomainToApiTransformer<PresentEntity, Present>{

	private CommercialLocationDomainToApiTransformer commercialLocationDomainToApiTransformer;

	@Autowired
	public PresentDomainToApiTransformer(
			CommercialLocationDomainToApiTransformer commercialLocationDomainToApiTransformer) {
		this.commercialLocationDomainToApiTransformer = commercialLocationDomainToApiTransformer;
	}

	@Override
	public Present transform(PresentEntity domainObject) {
		CommercialLocation locationCredit = commercialLocationDomainToApiTransformer.transform(domainObject.getLocationCredit());	
		return new Present(domainObject.getType(), domainObject.getCredit(), locationCredit);
	}
	
	
}
