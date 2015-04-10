package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.country.City;
import com.je.enterprise.mievento.domain.entity.geo.CityEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;

@Component
public class CityGeoTransformer extends
		Transformer<CityEntity, City> {
	
	@Override
	protected City transformDomainToApi(CityEntity domainObject) {
		return new City(domainObject.getName());
	}

	@Override
	protected CityEntity transformApiToDomain(City apiObject) {
		return null;
	}

	

}
