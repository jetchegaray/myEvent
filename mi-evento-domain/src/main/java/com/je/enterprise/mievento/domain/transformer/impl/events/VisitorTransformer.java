package com.je.enterprise.mievento.domain.transformer.impl.events;

import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.event.wedding.EventWithPlaceAndPresent;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.entity.wedding.EventWithPlaceAndPresentEntity;

public interface VisitorTransformer {

	public EventEntity visitTransformer(Event event);
	public EventWithPlaceAndPresentEntity visitTransformer(EventWithPlaceAndPresent eventWithPlaceAndPresent);
	
	public Event visitTransformer(EventEntity eventEntity);
	public EventWithPlaceAndPresent visitTransformer(EventWithPlaceAndPresentEntity weddingEntity);
	
}
