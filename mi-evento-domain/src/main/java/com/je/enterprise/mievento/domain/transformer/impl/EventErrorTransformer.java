package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.error.TraderError;
import com.je.enterprise.mievento.domain.exception.HttpEventException;
import com.je.enterprise.mievento.domain.transformer.Transformer;

@Component
public class EventErrorTransformer extends Transformer<HttpEventException, TraderError> {

	@Override
	protected TraderError transformDomainToApi(HttpEventException httpEventException) {
		Integer status = httpEventException.getHttpStatus();
		Long code = httpEventException.getCode();
		String description = httpEventException.getDescription();
		String moreInfo = httpEventException.getMoreInfoURL();
		return new TraderError(code,status,description,moreInfo);
	}

	@Override
	protected HttpEventException transformApiToDomain(TraderError apiObject) {
		// TODO Nothing to do.
		return null;
	}

	
}
