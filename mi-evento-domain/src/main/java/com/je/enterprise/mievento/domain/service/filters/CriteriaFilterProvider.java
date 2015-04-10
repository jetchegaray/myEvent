package com.je.enterprise.mievento.domain.service.filters;

import org.mongodb.morphia.query.Query;

import com.je.enterprise.mievento.domain.dao.impl.ProviderDAO;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;

public interface CriteriaFilterProvider {

	Query<ProviderEntity> buildQueryCriteria(ProviderDAO providerDAO);
	
}
