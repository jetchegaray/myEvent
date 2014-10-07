package com.je.enterprise.mievento.domain.transformer;



public interface Transformer<D , A> {

	A transformDomainToApi(D domainObject);	
	
	D transformApiToDomain(A apiObject);	
	
}
