package com.je.enterprise.mievento.domain.transformer.impl.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.event.wedding.Wedding;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.entity.wedding.WeddingEntity;
import com.je.enterprise.mievento.domain.transformer.impl.EventTransformer;
import com.je.enterprise.mievento.domain.transformer.impl.WeddingTransformer;

@Component
public class VisitorTransformerEvent implements VisitorTransformer{
	
	
	private EventTransformer eventTransformer;
	private WeddingTransformer weddingTransformer;

	@Autowired
	public void visitTransformer(EventTransformer eventTransformer,WeddingTransformer weddingTransformer) {
		this.eventTransformer = eventTransformer;
		this.weddingTransformer = weddingTransformer;
	}

	@Override
	public EventEntity visitTransformer(Event event) {
		return this.eventTransformer.transformAndValidateApiToDomain(event);
	}

	@Override
	public WeddingEntity visitTransformer(Wedding wedding) {
		return this.weddingTransformer.transformApiToDomain(wedding);
	}

	@Override
	public Event visitTransformer(EventEntity eventEntity) {
		return this.eventTransformer.transformAndValidateDomainToApi(eventEntity);
	}

	@Override
	public Wedding visitTransformer(WeddingEntity weddingEntity) {
		return this.weddingTransformer.transformAndValidateDomainToApi(weddingEntity);
	}
	
	
}
