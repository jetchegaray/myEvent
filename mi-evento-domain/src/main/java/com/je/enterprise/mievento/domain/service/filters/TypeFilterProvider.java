package com.je.enterprise.mievento.domain.service.filters;

import org.mongodb.morphia.query.Query;

import com.je.enterprise.mievento.domain.dao.impl.ProviderDAO;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderType;

public class TypeFilterProvider implements CriteriaFilterProvider{
	
	private ProviderType type;
	
	public TypeFilterProvider(ProviderType type) {
		this.type = type;
	}

	@Override
	public Query<ProviderEntity> buildQueryCriteria(ProviderDAO providerDAO){
		Query<ProviderEntity> query = providerDAO.createQuery().disableValidation();
		
		query.and(query.criteria("providerType").equal(type));
		return query;
	}

}
