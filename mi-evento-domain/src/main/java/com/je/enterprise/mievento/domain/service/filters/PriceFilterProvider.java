package com.je.enterprise.mievento.domain.service.filters;

import java.math.BigDecimal;

import org.mongodb.morphia.query.Query;

import com.je.enterprise.mievento.api.dto.provider.ProviderType;
import com.je.enterprise.mievento.domain.dao.impl.ProviderDAO;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;

public class PriceFilterProvider implements CriteriaFilterProvider{
	
	private ProviderType type;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;
	
	public PriceFilterProvider(ProviderType type, BigDecimal minPrice, BigDecimal maxPrice) {
		this.type = type;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	@Override
	public Query<ProviderEntity> buildQueryCriteria(ProviderDAO providerDAO){
		Query<ProviderEntity> query = providerDAO.createQuery().disableValidation().enableSnapshotMode();
		
		query.and(query.criteria("providerType").equal(type))
		.and(query.criteria("price").lessThan(maxPrice))
		.and(query.criteria("price").greaterThan(minPrice));
		
		return query;
	}

}
