package com.je.enterprise.mievento.domain.transformer.impl;

import java.util.List;

import com.je.enterprise.mievento.api.dto.country.Country;
import com.je.enterprise.mievento.api.dto.country.State;
import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.domain.entity.geo.CountryEntity;
import com.je.enterprise.mievento.domain.entity.geo.StateEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;
import com.je.enterprise.mievento.domain.transformer.TransformerList;


public class CountryGeoTransformer extends
		Transformer<CountryEntity, Country> {

	private TransformerList<StateEntity, State> stateTransformerList;
	
	public CountryGeoTransformer(TransformerList<StateEntity, State> stateTransformerList) {
		this.stateTransformerList = stateTransformerList;
	}

	@Override
	protected Country transformDomainToApi(CountryEntity domainObject) {
		List<State> states = this.stateTransformerList.transformDomainToApi(domainObject.getStates());
		return new Country(domainObject.getName(),CountryCode.getByName(domainObject.getName()), states);
	}

	@Override
	protected CountryEntity transformApiToDomain(Country apiObject) {
		// TODO Auto-generated method stub
		return null;
	}

}
