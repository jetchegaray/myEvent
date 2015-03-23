package com.je.enterprise.mievento.domain.transformer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.je.enterprise.mievento.api.dto.event.Guest;
import com.je.enterprise.mievento.api.dto.event.Task;
import com.je.enterprise.mievento.api.dto.event.eventWithplace.EventWithPlaceAndPresent;
import com.je.enterprise.mievento.api.dto.event.eventWithplace.Present;
import com.je.enterprise.mievento.api.dto.place.Place;
import com.je.enterprise.mievento.api.dto.provider.Provider;
import com.je.enterprise.mievento.api.dto.provider.ProviderType;
import com.je.enterprise.mievento.domain.entity.common.event.GuestEntity;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.common.event.TaskEntity;
import com.je.enterprise.mievento.domain.entity.place.PlaceEntity;
import com.je.enterprise.mievento.domain.entity.wedding.EventWithPlaceAndPresentEntity;
import com.je.enterprise.mievento.domain.entity.wedding.PresentEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;
import com.je.enterprise.mievento.domain.transformer.TransformerList;

@Component
public class EventWithPlaceAndPresentTransformer extends Transformer<EventWithPlaceAndPresentEntity, EventWithPlaceAndPresent> {

	private PlaceTransformer placeTransformer;

	private TransformerList<GuestEntity, Guest> guestTransformerList;
	private TransformerList<ProviderEntity, Provider> providerTransformerList;
	private TransformerList<PresentEntity, Present> presentTransformerList;
	private TransformerList<TaskEntity, Task> taskTransformerList;

	@Autowired
	public EventWithPlaceAndPresentTransformer(
			PlaceTransformer placeTransformer,
			TransformerList<GuestEntity, Guest> guestTransformerList,
			TransformerList<ProviderEntity, Provider> providerTransformerList,
			TransformerList<PresentEntity, Present> presentTransformerList,TransformerList<TaskEntity, Task> taskTransformerList) {

		this.guestTransformerList = guestTransformerList;
		this.providerTransformerList = providerTransformerList;
		this.presentTransformerList = presentTransformerList;
		this.placeTransformer = placeTransformer;
		this.taskTransformerList = taskTransformerList;
	}

	@Override
	public EventWithPlaceAndPresent transformDomainToApi(EventWithPlaceAndPresentEntity domainObject) {

		List<Guest> guests = this.guestTransformerList
				.transformDomainToApi(domainObject.getGuests());
		List<Task> tasks = this.taskTransformerList.transformDomainToApi(domainObject.getTasks());
		List<Provider> providers = this.providerTransformerList
				.transformDomainToApi(domainObject.getProviders());
		List<Present> presents = this.presentTransformerList
				.transformDomainToApi(domainObject.getPresents());
		
		ProviderEntity providerPlace = Iterables.find(domainObject.getProviders(), new Predicate<ProviderEntity>() {
			@Override
			public boolean apply(ProviderEntity input) {
				return ProviderType.getPlaceTypes().contains(input.getProviderType());
			}
		});
		// Si hay un proveedor de lugar lo seteo.
		if (providerPlace != null && domainObject.getPlace() == null){
			domainObject.setPlace(new PlaceEntity(providerPlace));
		}
		
		Place place = this.placeTransformer.transformAndValidateDomainToApi(domainObject
				.getPlace());

		return new EventWithPlaceAndPresent(domainObject.getName(), domainObject.getInitialDate(), domainObject.getFinalDate(), guests, tasks, presents, place,
				domainObject.getBudget(),providers, domainObject.getType());
	}

	@Override
	public EventWithPlaceAndPresentEntity transformApiToDomain(EventWithPlaceAndPresent apiObject) {
		List<TaskEntity> tasksEntities = this.taskTransformerList
				.transformApiToDomain(apiObject.getTasks());
		List<GuestEntity> guestsEntities = this.guestTransformerList
				.transformApiToDomain(apiObject.getGuests());
		List<ProviderEntity> providersEntities = this.providerTransformerList
				.transformApiToDomain(apiObject.getProviders());
		List<PresentEntity> presentsEntities = this.presentTransformerList
				.transformApiToDomain(apiObject.getPresents());
		
		Provider providerPlace = Iterables.find(apiObject.getProviders(), new Predicate<Provider>() {
			@Override
			public boolean apply(Provider input) {
				return ProviderType.getPlaceTypes().contains(input.getProviderType());
			}
		});
		// Si hay un proveedor de lugar lo seteo.
		if (providerPlace != null && apiObject.getPlace() == null){
			apiObject.setPlace(new Place(providerPlace));
		}
		
		PlaceEntity placeEntity = this.placeTransformer.transformAndValidateApiToDomain(apiObject
				.getPlace());

		return new EventWithPlaceAndPresentEntity(apiObject.getName(), apiObject.getInitialDate(), apiObject.getFinalDate(),
				guestsEntities, tasksEntities, presentsEntities, placeEntity,
				apiObject.getBudget(), providersEntities, apiObject.getType());
	}

}
