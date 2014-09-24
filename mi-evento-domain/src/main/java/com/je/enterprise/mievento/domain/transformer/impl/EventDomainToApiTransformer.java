package com.je.enterprise.mievento.domain.transformer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.event.Person;
import com.je.enterprise.mievento.api.dto.location.CommercialLocation;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.transformer.DomainToApiTransformer;

@Component
public class EventDomainToApiTransformer extends DomainToApiTransformer<EventEntity, Event>{

	
	private CommercialLocationDomainToApiTransformer commercialLocationDomainToApiTransformer;
	private PersonDomainToApiTransformer personDomainToApiTransformer;
	
	@Autowired
	public EventDomainToApiTransformer(CommercialLocationDomainToApiTransformer commercialLocationDomainToApiTransformer,
			PersonDomainToApiTransformer personDomainToApiTransformer) {
		this.commercialLocationDomainToApiTransformer = commercialLocationDomainToApiTransformer;
		this.personDomainToApiTransformer = personDomainToApiTransformer;
	}

	@Override
	public Event transform(EventEntity domainObject) {
		CommercialLocation eventLocation = this.commercialLocationDomainToApiTransformer.transform(domainObject.getEventLocation());
		List<Person> guests = this.personDomainToApiTransformer.transform(domainObject.getGuests());
		return new Event(domainObject.getName(), domainObject.getEventDate(), eventLocation, guests);
	}

}
