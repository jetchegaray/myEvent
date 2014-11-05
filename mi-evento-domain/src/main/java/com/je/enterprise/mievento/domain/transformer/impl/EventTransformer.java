package com.je.enterprise.mievento.domain.transformer.impl;

import java.util.List;

import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.event.Guest;
import com.je.enterprise.mievento.api.dto.event.Task;
import com.je.enterprise.mievento.api.dto.location.CommercialLocation;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.entity.common.event.GuestEntity;
import com.je.enterprise.mievento.domain.entity.common.event.TaskEntity;
import com.je.enterprise.mievento.domain.entity.location.CommercialLocationEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;
import com.je.enterprise.mievento.domain.transformer.TransformerList;


public class EventTransformer extends Transformer<EventEntity, Event>{

	
	private CommercialLocationTransformer commercialLocationTransformer;
	private TransformerList<GuestEntity, Guest> guestTransformerList;
	private TransformerList<TaskEntity, Task> taskTransformerList;
	
	
	public EventTransformer(CommercialLocationTransformer commercialLocationTransformer,
			TransformerList<GuestEntity, Guest> guestTransformerList, TransformerList<TaskEntity, Task> taskTransformerList) {
		this.commercialLocationTransformer = commercialLocationTransformer;
		this.guestTransformerList = guestTransformerList;
		this.taskTransformerList = taskTransformerList;
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
		List<TaskEntity> tasksEntities = this.taskTransformerList.transformApiToDomain(apiObject.getTasks());
		
		return new EventEntity(apiObject.getName(), apiObject.getEventDate(), eventLocationEntity, guestsEntities, tasksEntities);
	}

}
