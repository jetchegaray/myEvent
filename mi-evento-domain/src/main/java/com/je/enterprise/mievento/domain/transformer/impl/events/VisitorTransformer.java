package com.je.enterprise.mievento.domain.transformer.impl.events;

import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.event.wedding.Wedding;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.entity.wedding.WeddingEntity;

public interface VisitorTransformer {

	public EventEntity visitTransformer(Event event);
	public WeddingEntity visitTransformer(Wedding wedding);
	
	public Event visitTransformer(EventEntity eventEntity);
	public Wedding visitTransformer(WeddingEntity weddingEntity);
	
}
