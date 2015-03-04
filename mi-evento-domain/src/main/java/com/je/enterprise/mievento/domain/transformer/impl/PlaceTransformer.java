package com.je.enterprise.mievento.domain.transformer.impl;

import java.util.List;

import com.je.enterprise.mievento.api.dto.location.Location;
import com.je.enterprise.mievento.api.dto.place.Place;
import com.je.enterprise.mievento.api.dto.provider.Review;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderReviewEntity;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;
import com.je.enterprise.mievento.domain.entity.place.PlaceEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;
import com.je.enterprise.mievento.domain.transformer.TransformerList;

//avoid redundancy because transformer list need trnasformer y this transformer needs trnaformer list
public class PlaceTransformer extends Transformer<PlaceEntity, Place>{

	private LocationTransformer locationTransformer;
	private TransformerList<ProviderReviewEntity, Review> reviewTransformerList;
	
	public PlaceTransformer(
			LocationTransformer locationTransformer,TransformerList<ProviderReviewEntity, Review> reviewTransformerList) {
		this.locationTransformer = locationTransformer;
		this.reviewTransformerList = reviewTransformerList;
	}


	@Override
	public Place transformDomainToApi(PlaceEntity domainObject) {
		Location location = locationTransformer.transformAndValidateDomainToApi(domainObject.getLocation());
		List<Review> reviews = reviewTransformerList.transformDomainToApi(domainObject.getReviews());
		return new Place(domainObject.getBusinessName(), domainObject.getDescription(), location, domainObject.getEmail(), domainObject.getCellPhone(), domainObject.getPhone(), domainObject.getPrice(), domainObject.getEstimatedPrice(), domainObject.getM2(), domainObject.getEstimatedQuantityTables(), domainObject.getEstimatedQuantityPerson(), domainObject.getPhotos(), domainObject.getProviderType(),reviews);
	}

	@Override
	public PlaceEntity transformApiToDomain(Place apiObject) {
		LocationEntity locationEntity = locationTransformer.transformAndValidateApiToDomain(apiObject.getLocation());
		List<ProviderReviewEntity> reviewEntities = reviewTransformerList.transformApiToDomain(apiObject.getReviews());
		return new PlaceEntity(apiObject.getBusinessName(), apiObject.getDescription(), locationEntity, apiObject.getEmail(), apiObject.getCellPhone(), apiObject.getPhone(), apiObject.getPrice(), apiObject.getEstimatedPrice(), apiObject.getM2(), apiObject.getEstimatedQuantityTables(), apiObject.getEstimatedQuantityPerson(), apiObject.getPicture(), apiObject.getProviderType(),reviewEntities);
	}

	
}
