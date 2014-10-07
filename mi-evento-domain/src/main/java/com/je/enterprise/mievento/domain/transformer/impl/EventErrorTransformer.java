package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.error.EventError;
import com.je.enterprise.mievento.domain.exception.HttpEventException;
import com.je.enterprise.mievento.domain.transformer.Transformer;

@Component
public class EventErrorTransformer extends Transformer<HttpEventException, EventError> {

	@Override
	protected EventError transformDomainToApi(HttpEventException httpEventException) {
		Integer status = httpEventException.getHttpStatus();
		Long code = httpEventException.getCode();
		String description = httpEventException.getDescription();
		String moreInfo = httpEventException.getMoreInfoURL();
		return new EventError(code,status,description,moreInfo);
	}

	@Override
	protected HttpEventException transformApiToDomain(EventError apiObject) {
		// TODO Nothing to do.
		return null;
	}

	
}
