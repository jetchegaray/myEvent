package com.je.enterprise.mievento.domain.service.filters;

import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.query.Query;

import com.je.enterprise.mievento.api.dto.provider.ProviderType;
import com.je.enterprise.mievento.domain.dao.impl.ProviderDAO;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;

public class LocationTypeNameFilterProvider implements CriteriaFilterProvider{
	
	private LocationEntity location;
	private ProviderType type;
	private String name;
	
	public LocationTypeNameFilterProvider(String name, ProviderType type, LocationEntity location) {
		this.location = location;
		this.type = type;
		this.name = name;
	}

	@Override
	public Query<ProviderEntity> buildQueryCriteria(ProviderDAO providerDAO){
	
		Query<ProviderEntity> query = providerDAO.createQuery().disableValidation().enableSnapshotMode();
		
		if (StringUtils.isNotBlank(this.name)){
			query.and(query.criteria("businessName").containsIgnoreCase(this.name));
		}
		if (this.type != null){
			query.and(query.criteria("providerType").equal(type));
		}
		if (this.location != null){
			if (location.getCountryCode() != null){
				query.and(query.criteria("location.countryCode").equal(location.getCountryCode()));
			}
			if (StringUtils.isNotBlank(location.getProvince())){
				query.and(query.criteria("location.province").equal(location.getProvince()));
			}
//			if (StringUtils.isNotBlank(location.getCity())){
//				query.and(query.criteria("location.city").containsIgnoreCase(location.getCity()));
//			}
			
//			if (location.getStreetAddress() != null){
//				if (StringUtils.isNotBlank(location.getStreetAddress().getNeighborhood())){
//					query.and(query.criteria("location.streetAddress.neighborhood").containsIgnoreCase(location.getStreetAddress().getNeighborhood()));
//				}
//			}
		}
		return query;
	}

}
