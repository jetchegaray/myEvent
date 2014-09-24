package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.location.Location;
import com.je.enterprise.mievento.api.dto.provider.Provider;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.transformer.DomainToApiTransformer;

@Component
public class ProviderDomainToApiTransformer extends
		DomainToApiTransformer<ProviderEntity, Provider> {

	private LocationDomainToApiTransformer locationDomainToApiTransformer;

	@Autowired
	public ProviderDomainToApiTransformer(
			LocationDomainToApiTransformer locationDomainToApiTransformer) {
		this.locationDomainToApiTransformer = locationDomainToApiTransformer;
	}

	@Override
	public Provider transform(ProviderEntity providerEntity) {
		Location location = this.locationDomainToApiTransformer
				.transform(providerEntity.getLocation());
		
		return new Provider(providerEntity.getBusinessName(),
				providerEntity.getDescription(), location,
				providerEntity.getEmail(), providerEntity.getCellPhone(),
				providerEntity.getPhone(), providerEntity.getPrice(),
				providerEntity.getEstimatedPrice(),
				providerEntity.getPicture(), providerEntity.getProviderType());
	}

}
