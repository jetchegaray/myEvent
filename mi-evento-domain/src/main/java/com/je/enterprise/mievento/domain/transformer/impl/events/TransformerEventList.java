package com.je.enterprise.mievento.domain.transformer.impl.events;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;

@Component
public class TransformerEventList{

	private VisitorTransformer visitorTransfomer;
	private StrategyApiVisitor strategyApiVisitor;  

	@Autowired
	public TransformerEventList(VisitorTransformerEvent visitorTransfomer,StrategyApiVisitor strategyApiVisitor) {
		this.visitorTransfomer = visitorTransfomer;
		this.strategyApiVisitor = strategyApiVisitor;
	}
	

	public List<Event> transformDomainToApi(List<EventEntity> domainObjects) {
		List<Event> apiObjects = Lists.newArrayList();
		
		try {
			Preconditions.checkNotNull(domainObjects);
			for (EventEntity d : domainObjects) {
				apiObjects.add(d.accept(this.visitorTransfomer));
			}
			return apiObjects;

		} catch (NullPointerException ex) {
			return apiObjects;
		}
	}

	public List<EventEntity> transformApiToDomain(List<? extends Event> apiObjects) {
		List<EventEntity> domainObjects = Lists.newArrayList();
		try {
			Preconditions.checkNotNull(domainObjects);

			for (Event a : apiObjects) {
				strategyApiVisitor.addDomainObjectWithStrategy(a, visitorTransfomer, domainObjects);
			}
			return domainObjects;

		} catch (NullPointerException ex) {
			return domainObjects;
		}
	}

}
