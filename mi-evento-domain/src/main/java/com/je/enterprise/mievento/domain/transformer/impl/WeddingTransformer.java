package com.je.enterprise.mievento.domain.transformer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.event.Guest;
import com.je.enterprise.mievento.api.dto.event.Person;
import com.je.enterprise.mievento.api.dto.event.Task;
import com.je.enterprise.mievento.api.dto.event.wedding.Place;
import com.je.enterprise.mievento.api.dto.event.wedding.Present;
import com.je.enterprise.mievento.api.dto.event.wedding.Wedding;
import com.je.enterprise.mievento.api.dto.location.CommercialLocation;
import com.je.enterprise.mievento.api.dto.provider.Provider;
import com.je.enterprise.mievento.domain.entity.common.event.GuestEntity;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.common.event.TaskEntity;
import com.je.enterprise.mievento.domain.entity.location.CommercialLocationEntity;
import com.je.enterprise.mievento.domain.entity.wedding.PersonEntity;
import com.je.enterprise.mievento.domain.entity.wedding.PlaceEntity;
import com.je.enterprise.mievento.domain.entity.wedding.PresentEntity;
import com.je.enterprise.mievento.domain.entity.wedding.WeddingEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;
import com.je.enterprise.mievento.domain.transformer.TransformerList;

@Component
public class WeddingTransformer extends Transformer<WeddingEntity, Wedding> {

	private CommercialLocationTransformer commercialLocationTransformer;
	private PersonTransformer personTransformer;
	private PlaceTransformer placeTransformer;

	private TransformerList<GuestEntity, Guest> guestTransformerList;
	private TransformerList<ProviderEntity, Provider> providerTransformerList;
	private TransformerList<PresentEntity, Present> presentTransformerList;
	private TransformerList<TaskEntity, Task> taskTransformerList;

	@Autowired
	public WeddingTransformer(
			CommercialLocationTransformer commercialLocationTransformer,
			PersonTransformer personTransformer,
			PlaceTransformer placeTransformer,
			TransformerList<GuestEntity, Guest> guestTransformerList,
			TransformerList<ProviderEntity, Provider> providerTransformerList,
			TransformerList<PresentEntity, Present> presentTransformerList,TransformerList<TaskEntity, Task> taskTransformerList) {

		this.commercialLocationTransformer = commercialLocationTransformer;
		this.personTransformer = personTransformer;
		this.guestTransformerList = guestTransformerList;
		this.providerTransformerList = providerTransformerList;
		this.presentTransformerList = presentTransformerList;
		this.placeTransformer = placeTransformer;
		this.taskTransformerList = taskTransformerList;
	}

	@Override
	public Wedding transformDomainToApi(WeddingEntity domainObject) {

		CommercialLocation eventLocation = this.commercialLocationTransformer
				.transformAndValidateDomainToApi(domainObject.getEventLocation());
		List<Guest> guests = this.guestTransformerList
				.transformDomainToApi(domainObject.getGuests());
		List<Provider> providers = this.providerTransformerList
				.transformDomainToApi(domainObject.getProviders());
		List<Present> presents = this.presentTransformerList
				.transformDomainToApi(domainObject.getPresents());
		Person wife = this.personTransformer.transformAndValidateDomainToApi(domainObject
				.getWife());
		Person husband = this.personTransformer
				.transformAndValidateDomainToApi(domainObject.getHusband());
		Place place = this.placeTransformer.transformAndValidateDomainToApi(domainObject
				.getPlace());

		return new Wedding(domainObject.getName(), domainObject.getEventDate(),
				eventLocation, guests, husband, wife, presents, place,
				domainObject.getBudget(), domainObject.getFinalPrice(),
				providers);
	}

	@Override
	public WeddingEntity transformApiToDomain(Wedding apiObject) {
		CommercialLocationEntity eventLocationEntity = this.commercialLocationTransformer
				.transformAndValidateApiToDomain(apiObject.getEventLocation());
		List<TaskEntity> tasksEntities = this.taskTransformerList
				.transformApiToDomain(apiObject.getTasks());
		List<GuestEntity> guestsEntities = this.guestTransformerList
				.transformApiToDomain(apiObject.getGuests());
		List<ProviderEntity> providersEntities = this.providerTransformerList
				.transformApiToDomain(apiObject.getProviders());
		List<PresentEntity> presentsEntities = this.presentTransformerList
				.transformApiToDomain(apiObject.getPresents());
		PersonEntity wifeEntity = this.personTransformer.transformAndValidateApiToDomain(apiObject
				.getWife());
		PersonEntity husbandEntity = this.personTransformer
				.transformAndValidateApiToDomain(apiObject.getHusband());
		PlaceEntity placeEntity = this.placeTransformer.transformAndValidateApiToDomain(apiObject
				.getPlace());

		return new WeddingEntity(apiObject.getName(), apiObject.getEventDate(),
				eventLocationEntity, guestsEntities, tasksEntities, husbandEntity, wifeEntity, presentsEntities, placeEntity,
				apiObject.getBudget(), apiObject.getFinalPrice(),
				providersEntities);
	}

}
