package com.je.enterprise.mievento.domain.transformer.impl.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.event.eventWithplace.EventWithPlaceAndPresent;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.entity.wedding.EventWithPlaceAndPresentEntity;
import com.je.enterprise.mievento.domain.transformer.impl.EventTransformer;
import com.je.enterprise.mievento.domain.transformer.impl.EventWithPlaceAndPresentTransformer;

@Component
public class VisitorTransformerEvent implements VisitorTransformer{
	
	
	private EventTransformer eventTransformer;
	private EventWithPlaceAndPresentTransformer weddingTransformer;

	@Autowired
	public void visitTransformer(EventTransformer eventTransformer,EventWithPlaceAndPresentTransformer weddingTransformer) {
		this.eventTransformer = eventTransformer;
		this.weddingTransformer = weddingTransformer;
	}

	@Override
	public EventEntity visitTransformer(Event event) {
		return this.eventTransformer.transformAndValidateApiToDomain(event);
	}

	@Override
	public EventWithPlaceAndPresentEntity visitTransformer(EventWithPlaceAndPresent eventWithPlaceAndPresent) {
		return this.weddingTransformer.transformApiToDomain(eventWithPlaceAndPresent);
	}

	@Override
	public Event visitTransformer(EventEntity eventEntity) {
		return this.eventTransformer.transformAndValidateDomainToApi(eventEntity);
	}

	@Override
	public EventWithPlaceAndPresent visitTransformer(EventWithPlaceAndPresentEntity eventWithPlaceAndPresentEntity) {
		return this.weddingTransformer.transformAndValidateDomainToApi(eventWithPlaceAndPresentEntity);
	}
	
	
}
