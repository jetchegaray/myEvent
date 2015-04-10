package com.je.enterprise.mievento.domain.transformer.impl;

import java.util.List;

import com.je.enterprise.mievento.api.dto.country.City;
import com.je.enterprise.mievento.api.dto.country.State;
import com.je.enterprise.mievento.domain.entity.geo.CityEntity;
import com.je.enterprise.mievento.domain.entity.geo.StateEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;
import com.je.enterprise.mievento.domain.transformer.TransformerList;


public class StateGeoTransformer extends
		Transformer<StateEntity, State> {

	private TransformerList<CityEntity, City> cityTransformerList;
	
	public StateGeoTransformer(TransformerList<CityEntity, City> cityTransformerList) {
		this.cityTransformerList = cityTransformerList;
	}

	@Override
	protected State transformDomainToApi(StateEntity domainObject) {
		List<City> cities = this.cityTransformerList.transformDomainToApi(domainObject.getCities());
		return new State(domainObject.getName(),cities);
	}

	@Override
	protected StateEntity transformApiToDomain(State apiObject) {
		// TODO Auto-generated method stub
		return null;
	}

}
