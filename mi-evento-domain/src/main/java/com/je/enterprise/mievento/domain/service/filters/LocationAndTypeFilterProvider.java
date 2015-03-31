package com.je.enterprise.mievento.domain.service.filters;

import org.mongodb.morphia.query.Query;

import com.je.enterprise.mievento.api.dto.provider.ProviderType;
import com.je.enterprise.mievento.domain.dao.impl.ProviderDAO;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;

public class LocationAndTypeFilterProvider implements CriteriaFilterProvider{
	
	private LocationEntity location;
	private ProviderType type;
	public static final Integer LIMIT  = 25;
	
	public LocationAndTypeFilterProvider(ProviderType type, LocationEntity location) {
		this.location = location;
		this.type = type;
	}

	@Override
	public Query<ProviderEntity> buildQueryCriteria(ProviderDAO providerDAO){
	
		Query<ProviderEntity> query = providerDAO.createQuery().disableValidation().enableSnapshotMode();
		
		if (this.type != null){
			query.and(query.criteria("providerType").equal(type));
		}
		if (this.location != null){
			if (location.getCountryCode() != null){
				query.and(query.criteria("location.countryCode").equal(location.getCountryCode()));
			}
			if (location.getProvince() != null){
				query.and(query.criteria("location.province").equal(location.getProvince()));
			}
			if (location.getCity() != null){
				query.and(query.criteria("location.city").containsIgnoreCase(location.getCity()));
			}
			
			if (location.getStreetAddress() != null){
				if (location.getStreetAddress().getNeighborhood() != null){
					query.and(query.criteria("location.streetAddress.neighborhood").containsIgnoreCase(location.getStreetAddress().getNeighborhood()));
				}
			}
		}
		return query;
	}

}
