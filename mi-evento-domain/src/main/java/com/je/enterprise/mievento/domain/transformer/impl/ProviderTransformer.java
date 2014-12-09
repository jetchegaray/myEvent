package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.location.Location;
import com.je.enterprise.mievento.api.dto.provider.Provider;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;

@Component
public class ProviderTransformer extends
		Transformer<ProviderEntity, Provider> {

	private LocationTransformer locationTransformer;

	@Autowired
	public ProviderTransformer(LocationTransformer locationTransformer) {
		this.locationTransformer = locationTransformer;
	}

	@Override
	public Provider transformDomainToApi(ProviderEntity domainObject) {
		Location location = this.locationTransformer
				.transformAndValidateDomainToApi(domainObject.getLocation());

		return new Provider(domainObject.getBusinessName(),
				domainObject.getDescription(), location,
				domainObject.getEmail(), domainObject.getCellPhone(),
				domainObject.getPhone(), domainObject.getPrice(),
				domainObject.getEstimatedPrice(), domainObject.getPhotos(),
				domainObject.getProviderType());
	}

	@Override
	public ProviderEntity transformApiToDomain(Provider apiObject) {
		LocationEntity locationEntity = this.locationTransformer
				.transformAndValidateApiToDomain(apiObject.getLocation());

		return new ProviderEntity(apiObject.getBusinessName(),
				apiObject.getDescription(), locationEntity,
				apiObject.getEmail(), apiObject.getCellPhone(),
				apiObject.getPhone(), apiObject.getPrice(),
				apiObject.getEstimatedPrice(), apiObject.getPicture(),
				apiObject.getProviderType());
	}

}
