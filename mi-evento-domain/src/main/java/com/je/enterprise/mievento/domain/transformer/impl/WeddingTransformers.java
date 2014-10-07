package com.je.enterprise.mievento.domain.transformer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.event.Person;
import com.je.enterprise.mievento.api.dto.event.wedding.Place;
import com.je.enterprise.mievento.api.dto.event.wedding.Present;
import com.je.enterprise.mievento.api.dto.event.wedding.Wedding;
import com.je.enterprise.mievento.api.dto.location.CommercialLocation;
import com.je.enterprise.mievento.api.dto.provider.Provider;
import com.je.enterprise.mievento.domain.entity.common.event.PersonEntity;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.location.CommercialLocationEntity;
import com.je.enterprise.mievento.domain.entity.wedding.PlaceEntity;
import com.je.enterprise.mievento.domain.entity.wedding.PresentEntity;
import com.je.enterprise.mievento.domain.entity.wedding.WeddingEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;
import com.je.enterprise.mievento.domain.transformer.TransformerList;

@Component
public class WeddingTransformers extends Transformer<WeddingEntity, Wedding> {

	private CommercialLocationTransformer commercialLocationTransformer;
	private PersonTransformer personTransformer;
	private PlaceTransformer placeTransformer;

	private TransformerList<PersonEntity, Person> personTransformerList;
	private TransformerList<ProviderEntity, Provider> providerTransformerList;
	private TransformerList<PresentEntity, Present> presentTransformerList;

	@Autowired
	public WeddingTransformers(
			CommercialLocationTransformer commercialLocationTransformer,
			PersonTransformer personTransformer,
			PlaceTransformer placeTransformer,
			TransformerList<PersonEntity, Person> personTransformerList,
			TransformerList<ProviderEntity, Provider> providerTransformerList,
			TransformerList<PresentEntity, Present> presentTransformerList) {

		this.commercialLocationTransformer = commercialLocationTransformer;
		this.personTransformer = personTransformer;
		this.personTransformerList = personTransformerList;
		this.providerTransformerList = providerTransformerList;
		this.presentTransformerList = presentTransformerList;
		this.placeTransformer = placeTransformer;
	}

	@Override
	public Wedding transformDomainToApi(WeddingEntity domainObject) {

		CommercialLocation eventLocation = this.commercialLocationTransformer
				.transformAndValidateDomainToApi(domainObject.getEventLocation());
		List<Person> guests = this.personTransformerList
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
		List<PersonEntity> guestsEntities = this.personTransformerList
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
				eventLocationEntity, guestsEntities, husbandEntity, wifeEntity, presentsEntities, placeEntity,
				apiObject.getBudget(), apiObject.getFinalPrice(),
				providersEntities);
	}

}
