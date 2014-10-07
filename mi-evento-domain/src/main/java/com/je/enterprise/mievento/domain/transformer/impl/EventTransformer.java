package com.je.enterprise.mievento.domain.transformer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.event.Person;
import com.je.enterprise.mievento.api.dto.location.CommercialLocation;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.entity.common.event.PersonEntity;
import com.je.enterprise.mievento.domain.entity.location.CommercialLocationEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;
import com.je.enterprise.mievento.domain.transformer.TransformerList;


public class EventTransformer implements Transformer<EventEntity, Event>{

	
	private CommercialLocationTransformer commercialLocationTransformer;
	private TransformerList<PersonEntity, Person> personTransformerList;
	
	
	public EventTransformer(CommercialLocationTransformer commercialLocationTransformer,
			TransformerList<PersonEntity, Person> personTransformerList) {
		this.commercialLocationTransformer = commercialLocationTransformer;
		this.personTransformerList = personTransformerList;
	}


	@Override
	public Event transformDomainToApi(EventEntity domainObject) {
		CommercialLocation eventLocation = this.commercialLocationTransformer.transformDomainToApi(domainObject.getEventLocation());
		List<Person> guests = this.personTransformerList.transformDomainToApi(domainObject.getGuests());
		return new Event(domainObject.getName(), domainObject.getEventDate(), eventLocation, guests);
	}

	@Override
	public EventEntity transformApiToDomain(Event apiObject) {
		CommercialLocationEntity eventLocationEntity = null;
		if (apiObject.getEventLocation() != null){
			eventLocationEntity = this.commercialLocationTransformer.transformApiToDomain(apiObject.getEventLocation());
		}
		List<PersonEntity> guestsEntities = this.personTransformerList.transformApiToDomain(apiObject.getGuests());
		return new EventEntity(apiObject.getName(), apiObject.getEventDate(), eventLocationEntity, guestsEntities);
	}

}
