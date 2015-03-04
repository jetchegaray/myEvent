package com.je.enterprise.mievento.domain.transformer.impl;

import java.util.List;

import com.je.enterprise.mievento.api.dto.location.Location;
import com.je.enterprise.mievento.api.dto.provider.Provider;
import com.je.enterprise.mievento.api.dto.provider.Review;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderReviewEntity;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;
import com.je.enterprise.mievento.domain.transformer.TransformerList;

public class ProviderTransformer extends
		Transformer<ProviderEntity, Provider> {

	private LocationTransformer locationTransformer;
	private TransformerList<ProviderReviewEntity, Review> reviewTransformerList;

	public ProviderTransformer(LocationTransformer locationTransformer,TransformerList<ProviderReviewEntity, Review> reviewTransformerList) {
		this.locationTransformer = locationTransformer;
		this.reviewTransformerList = reviewTransformerList;
	}

	@Override
	public Provider transformDomainToApi(ProviderEntity domainObject) {
		Location location = this.locationTransformer
				.transformAndValidateDomainToApi(domainObject.getLocation());

		List<Review> reviews = reviewTransformerList.transformDomainToApi(domainObject.getReviews());
		
		return new Provider(domainObject.getBusinessName(),
				domainObject.getDescription(), location,
				domainObject.getEmail(), domainObject.getCellPhone(),
				domainObject.getPhone(), domainObject.getPrice(),
				domainObject.getEstimatedPrice(), domainObject.getPhotos(),
				domainObject.getProviderType(),reviews);
	}

	@Override
	public ProviderEntity transformApiToDomain(Provider apiObject) {
		LocationEntity locationEntity = this.locationTransformer
				.transformAndValidateApiToDomain(apiObject.getLocation());
		List<ProviderReviewEntity> reviewEntities = reviewTransformerList.transformApiToDomain(apiObject.getReviews());
		
		return new ProviderEntity(apiObject.getBusinessName(),
				apiObject.getDescription(), locationEntity,
				apiObject.getEmail(), apiObject.getCellPhone(),
				apiObject.getPhone(), apiObject.getPrice(),
				apiObject.getEstimatedPrice(), apiObject.getPicture(),
				apiObject.getProviderType(),reviewEntities);
	}

}
