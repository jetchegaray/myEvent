package com.je.enterprise.mievento.domain.transformer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.event.Person;
import com.je.enterprise.mievento.api.dto.event.wedding.Place;
import com.je.enterprise.mievento.api.dto.event.wedding.Present;
import com.je.enterprise.mievento.api.dto.event.wedding.Wedding;
import com.je.enterprise.mievento.api.dto.location.CommercialLocation;
import com.je.enterprise.mievento.api.dto.provider.Provider;
import com.je.enterprise.mievento.domain.entity.wedding.WeddingEntity;
import com.je.enterprise.mievento.domain.transformer.DomainToApiTransformer;

@Component
public class WeddingDomainToApiTransformers extends
		DomainToApiTransformer<WeddingEntity, Wedding> {

	private CommercialLocationDomainToApiTransformer commercialLocationDomainToApiTransformer;
	private PersonDomainToApiTransformer personDomainToApiTransformer;
	private ProviderDomainToApiTransformer providerDomainToApiTransformer;
	private PresentDomainToApiTransformer presentDomainToApiTransformer;
	private PlaceDomainToApiTransformer placeDomainToApiTransformer;
	
	@Autowired
	public WeddingDomainToApiTransformers(
			CommercialLocationDomainToApiTransformer commercialLocationDomainToApiTransformer,
			PersonDomainToApiTransformer personDomainToApiTransformer,
			ProviderDomainToApiTransformer providerDomainToApiTransformer,
			PresentDomainToApiTransformer presentDomainToApiTransformer,
			PlaceDomainToApiTransformer placeDomainToApiTransformer) {
		this.commercialLocationDomainToApiTransformer = commercialLocationDomainToApiTransformer;
		this.personDomainToApiTransformer = personDomainToApiTransformer;
		this.providerDomainToApiTransformer = providerDomainToApiTransformer;
		this.presentDomainToApiTransformer = presentDomainToApiTransformer;
		this.placeDomainToApiTransformer = placeDomainToApiTransformer;
	}

	@Override
	public Wedding transform(WeddingEntity domainObject) {
		
		CommercialLocation eventLocation = this.commercialLocationDomainToApiTransformer.transform(domainObject.getEventLocation());
		List<Person> guests = this.personDomainToApiTransformer.transform(domainObject.getGuests());
		List<Provider> providers = this.providerDomainToApiTransformer.transform(domainObject.getProviders());
		List<Present> presents = this.presentDomainToApiTransformer.transform(domainObject.getPresents());
		Person wife = this.personDomainToApiTransformer.transform(domainObject.getWife());
		Person husband = this.personDomainToApiTransformer.transform(domainObject.getHusband());
		Place place = this.placeDomainToApiTransformer.transform(domainObject.getPlace());
		
		return new Wedding(domainObject.getName(), domainObject.getEventDate(), eventLocation, guests, husband, wife, presents, place, domainObject.getBudget(), domainObject.getFinalPrice(), providers);
	}

}
