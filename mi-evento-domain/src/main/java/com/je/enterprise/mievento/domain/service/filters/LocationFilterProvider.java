package com.je.enterprise.mievento.domain.service.filters;

import org.mongodb.morphia.query.Query;

import com.je.enterprise.mievento.domain.dao.impl.ProviderDAO;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;

public class LocationFilterProvider implements CriteriaFilterProvider{
	
	private LocationEntity location;
	
	public LocationFilterProvider(LocationEntity location) {
		this.location = location;
	}

	@Override
	public Query<ProviderEntity> buildQueryCriteria(ProviderDAO providerDAO){
	
		Query<ProviderEntity> query = providerDAO.createQuery().disableValidation().enableSnapshotMode();
		
		query.and(query.criteria("countryCode").equal(location.getCountryCode().getName()));
		query.and(query.criteria("province").equal(location.getProvince()));
		query.and(query.criteria("neighborhood").equal(location.getStreetAddress().getNeighborhood()));
		
		return query;
	}

}
