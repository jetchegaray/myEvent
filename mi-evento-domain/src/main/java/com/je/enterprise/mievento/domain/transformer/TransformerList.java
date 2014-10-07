package com.je.enterprise.mievento.domain.transformer;

import java.util.List;

import com.google.common.collect.Lists;

public class TransformerList<D, A> {

	private Transformer<D , A>  transformer;
	
	public TransformerList(Transformer<D , A>  transformer) {
		this.transformer = transformer;
	}
	
	
	public List<A> transformDomainToApi(List<D> domainObjects){
		List<A> apiObjects = Lists.newArrayList();
		if (domainObjects == null){
			return apiObjects;
		}
		for (D d : domainObjects) {
			apiObjects.add(transformer.transformDomainToApi(d));
		}
		return apiObjects;
	}
	
	public List<D> transformApiToDomain(List<A> apiObjects){
		List<D> domainObjects = Lists.newArrayList();
		if (apiObjects == null){
			return domainObjects;
		}
		for (A a : apiObjects) {
			domainObjects.add(transformer.transformApiToDomain(a));
		}
		return domainObjects;
	}


	

}
