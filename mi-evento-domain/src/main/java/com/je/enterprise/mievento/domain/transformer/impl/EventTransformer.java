package com.je.enterprise.mievento.domain.transformer.impl;

import java.util.List;

import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.event.Guest;
import com.je.enterprise.mievento.api.dto.event.Task;
import com.je.enterprise.mievento.api.dto.provider.Provider;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.entity.common.event.GuestEntity;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.common.event.TaskEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;
import com.je.enterprise.mievento.domain.transformer.TransformerList;


//avoid redundancy because transformer list need trnasformer y this transformer needs trnaformer list
public class EventTransformer extends Transformer<EventEntity, Event>{

	
	private TransformerList<GuestEntity, Guest> guestTransformerList;
	private TransformerList<TaskEntity, Task> taskTransformerList;
	private TransformerList<ProviderEntity, Provider> providerTransformerList;
	
	public EventTransformer(
			TransformerList<GuestEntity, Guest> guestTransformerList, TransformerList<TaskEntity, Task> taskTransformerList, TransformerList<ProviderEntity, Provider> providerTransformerList) {
		this.guestTransformerList = guestTransformerList;
		this.taskTransformerList = taskTransformerList;
		this.providerTransformerList = providerTransformerList;
	}


	@Override
	public Event transformDomainToApi(EventEntity domainObject) {
		List<Guest> guests = this.guestTransformerList.transformDomainToApi(domainObject.getGuests());
		List<Task> tasks = this.taskTransformerList.transformDomainToApi(domainObject.getTasks());
		List<Provider> providers = this.providerTransformerList.transformDomainToApi(domainObject.getProviders());
		return new Event(domainObject.getName(), domainObject.getInitialDate(),domainObject.getFinalDate(), guests, tasks, providers, domainObject.getType());
	}

	@Override
	public EventEntity transformApiToDomain(Event apiObject) {
		List<GuestEntity> guestsEntities = this.guestTransformerList.transformApiToDomain(apiObject.getGuests());
		List<TaskEntity> tasksEntities = this.taskTransformerList.transformApiToDomain(apiObject.getTasks());
		List<ProviderEntity> providersEntities = this.providerTransformerList.transformApiToDomain(apiObject.getProviders());
		return new EventEntity(apiObject.getName(), apiObject.getInitialDate(),apiObject.getFinalDate(), guestsEntities, tasksEntities, providersEntities, apiObject.getType());
	}

}
