package com.je.enterprise.mievento.domain.service.filters;

import org.mongodb.morphia.query.Query;

import com.je.enterprise.mievento.api.dto.provider.ProviderType;
import com.je.enterprise.mievento.domain.dao.impl.ProviderDAO;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;

public class CheaperFilterProvider implements CriteriaFilterProvider{
	
	private ProviderType type;
	
	public CheaperFilterProvider(ProviderType type) {
		this.type = type;
	}

	@Override
	public Query<ProviderEntity> buildQueryCriteria(ProviderDAO providerDAO){
		Query<ProviderEntity> query = providerDAO.createQuery().disableValidation();
		
		query.and(query.criteria("providerType").equal(type));
		query.order("price");
		
		return query;
	}

}
