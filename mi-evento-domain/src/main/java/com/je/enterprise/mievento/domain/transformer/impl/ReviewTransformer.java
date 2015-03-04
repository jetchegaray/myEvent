package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.provider.Review;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderReviewEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;

@Component
public class ReviewTransformer extends
Transformer<ProviderReviewEntity, Review>{

	@Override
	protected Review transformDomainToApi(ProviderReviewEntity domainObject) {
		return new Review(domainObject.getUserName(), domainObject.getMessage(), domainObject.getRating());
	}

	@Override
	protected ProviderReviewEntity transformApiToDomain(Review apiObject) {
		return new ProviderReviewEntity(apiObject.getUserName(), apiObject.getMessage(), apiObject.getRating());
	}

}
