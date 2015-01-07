package com.je.enterprise.mievento.domain.transformer.impl.events;

import java.util.List;

import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.event.EventType;
import com.je.enterprise.mievento.api.dto.event.wedding.Wedding;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;

@Component
public class StrategyApiVisitor {

	public StrategyApiVisitor() {
	}
	
	public void addDomainObjectWithStrategy(Event event,VisitorTransformer visitorTransfomer,List<EventEntity> domainObjects){
		if (event.getType() == EventType.WEDDING){
			domainObjects.add(visitorTransfomer.visitTransformer((Wedding)event));
		}else if (event.getType() == EventType.COMMON_EVENT){
			domainObjects.add(visitorTransfomer.visitTransformer(event));
		}
	}
}
