package com.je.enterprise.mievento.domain.service.filters;

import org.mongodb.morphia.query.Query;

import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.domain.dao.impl.ProviderDAO;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;

public class CitiesFilterProvider implements CriteriaFilterProvider{
	
	private CountryCode country;
	
	public CitiesFilterProvider(CountryCode country) {
		this.country = country;
	}

	@Override
	public Query<ProviderEntity> buildQueryCriteria(ProviderDAO providerDAO){
	
		Query<ProviderEntity> query = providerDAO.createQuery().disableValidation().enableSnapshotMode();
		query.criteria("location.countryCode").equal(this.country);
		return query;
	}

}
