package com.je.enterprise.mievento.domain.transformer;

import java.util.List;

import com.google.common.collect.Lists;
import com.je.enterprise.mievento.domain.dao.BaseEntity;


public abstract class ApiToDomainTransformer<D extends BaseEntity, A> {

	abstract public D transform(A apiObject);
	
	public List<D> transform(List<A> apiObjects){
		List<D> domainObjects = Lists.newArrayList();
		for (A a : apiObjects) {
			domainObjects.add(transform(a));
		}
		return domainObjects;
	}
	
}
