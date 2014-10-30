package com.je.enterprise.mievento.domain.transformer.impl;

import java.util.List;

import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.event.Guest;
import com.je.enterprise.mievento.api.dto.location.CommercialLocation;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.entity.common.event.GuestEntity;
import com.je.enterprise.mievento.domain.entity.location.CommercialLocationEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;
import com.je.enterprise.mievento.domain.transformer.TransformerList;


public class EventTransformer extends Transformer<EventEntity, Event>{

	
	private CommercialLocationTransformer commercialLocationTransformer;
	private TransformerList<GuestEntity, Guest> guestTransformerList;
	
	
	public EventTransformer(CommercialLocationTransformer commercialLocationTransformer,
			TransformerList<GuestEntity, Guest> guestTransformerList) {
		this.commercialLocationTransformer = commercialLocationTransformer;
		this.guestTransformerList = guestTransformerList;
	}


	@Override
	public Event transformDomainToApi(EventEntity domainObject) {
		CommercialLocation eventLocation = this.commercialLocationTransformer.transformAndValidateDomainToApi(domainObject.getEventLocation());
		List<Guest> guests = this.guestTransformerList.transformDomainToApi(domainObject.getGuests());
		return new Event(domainObject.getName(), domainObject.getEventDate(), eventLocation, guests);
	}

	@Override
	public EventEntity transformApiToDomain(Event apiObject) {
		CommercialLocationEntity eventLocationEntity = null;
		eventLocationEntity = this.commercialLocationTransformer.transformAndValidateApiToDomain(apiObject.getEventLocation());
		List<GuestEntity> guestsEntities = this.guestTransformerList.transformApiToDomain(apiObject.getGuests());
		return new EventEntity(apiObject.getName(), apiObject.getEventDate(), eventLocationEntity, guestsEntities);
	}

}
