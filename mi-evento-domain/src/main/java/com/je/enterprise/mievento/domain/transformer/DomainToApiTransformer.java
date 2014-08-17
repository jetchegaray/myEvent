package com.je.enterprise.mievento.domain.transformer;

import com.je.enterprise.mievento.domain.dao.BaseEntity;


public interface DomainToApiTransformer<D extends BaseEntity, A> {

	A transform(D domainObject);
}
