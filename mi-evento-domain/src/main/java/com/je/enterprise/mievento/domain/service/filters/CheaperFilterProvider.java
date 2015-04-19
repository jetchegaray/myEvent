package com.je.enterprise.mievento.domain.service.filters;

import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.query.Query;

import com.je.enterprise.mievento.api.dto.location.Location;
import com.je.enterprise.mievento.api.dto.provider.ProviderType;
import com.je.enterprise.mievento.domain.dao.impl.ProviderDAO;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;

public class CheaperFilterProvider implements CriteriaFilterProvider{
	
	private ProviderType type;
	private Location location;
	
	public CheaperFilterProvider(ProviderType type,Location location) {
		this.type = type;
		this.location = location;
	}

	@Override
	public Query<ProviderEntity> buildQueryCriteria(ProviderDAO providerDAO){
		Query<ProviderEntity> query = providerDAO.createQuery().disableValidation();
		
		query.and(query.criteria("providerType").equal(type));
		query.and(query.criteria("estimatedPrice").notEqual("0"));
		
		if (this.location != null){
			if (location.getCountryCode() != null){
				query.and(query.criteria("location.countryCode").equal(location.getCountryCode()));
			}
			if (StringUtils.isNotBlank(location.getProvince())){
				query.and(query.criteria("location.province").equal(location.getProvince()));
			}
			if (StringUtils.isNotBlank(location.getCity())){
				query.and(query.criteria("location.city").containsIgnoreCase(location.getCity()));
			}
			
			if (location.getStreetAddress() != null){
				if (StringUtils.isNotBlank(location.getStreetAddress().getNeighborhood())){
					query.and(query.criteria("location.streetAddress.neighborhood").containsIgnoreCase(location.getStreetAddress().getNeighborhood()));
				}
			}
		}
		query.order("estimatedPrice");
		
		
		return query;
	}

}
