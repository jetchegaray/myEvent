package com.je.enterprise.mievento.domain.transformer;

import java.util.List;

import com.google.common.collect.Lists;
import com.je.enterprise.mievento.domain.dao.BaseEntity;


public abstract class DomainToApiTransformer<D extends BaseEntity, A> {

	abstract public A transform(D domainObject);
	
	public List<A> transform(List<D> domainObjects){
		List<A> apiObjects = Lists.newArrayList();
		for (D d : domainObjects) {
			apiObjects.add(transform(d));
		}
		return apiObjects;
	}
	
}
