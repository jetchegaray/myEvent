package com.je.enterprise.mievento.domain.transformer;

import com.google.common.base.Optional;



public abstract class Transformer<D , A> {

	protected abstract A transformDomainToApi(D domainObject);	
	
	protected abstract D transformApiToDomain(A apiObject);	
	
	public A transformAndValidateDomainToApi(D domainObject){
		Optional<D> optional = Optional.fromNullable(domainObject);
		if (optional.isPresent()){
			return transformDomainToApi(domainObject);
		}
		return null;
	}
	
	public D transformAndValidateApiToDomain(A apiObject){
		Optional<A> optional = Optional.fromNullable(apiObject);
		if (optional.isPresent()){
			return transformApiToDomain(apiObject);
		}
		return null;
	}
}
