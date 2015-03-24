package com.je.enterprise.mievento.domain.service.impl;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.domain.entity.geo.CityEntity;
import com.je.enterprise.mievento.domain.entity.geo.CountryEntity;
import com.je.enterprise.mievento.domain.entity.geo.StateEntity;
import com.je.enterprise.mievento.domain.exception.customize.NotExistanceCountriesException;
import com.je.enterprise.mievento.domain.external.apiGeo.services.CountryHelper;
import com.je.enterprise.mievento.domain.service.helper.CRUDHelper;

@Service
public class CountryService {

	private CRUDHelper<CountryEntity, ObjectId> crudHelper;
	private static final Logger logger = Logger.getLogger(CountryService.class);

	@Autowired
	public CountryService(CRUDHelper<CountryEntity, ObjectId> crudHelperCountry) {
		this.crudHelper = crudHelperCountry;
	}
	
	public List<CountryEntity> getAll(){
		
		List<CountryEntity> countries = this.crudHelper.getAll();	
		validate(countries);
		return countries; 
	}
	
	private void validate(List<CountryEntity> providers) {
		try{
			Validate.notNull(providers);
		}catch(NullPointerException ex){
			throw new NotExistanceCountriesException();
		}
	}
	
	
	public Set<CityEntity> getAllCitiesInCountry(CountryCode countryCode){
		Set<CityEntity> cities = Sets.newLinkedHashSet();
		List<CountryEntity> countries = this.getAll();
		CountryEntity selectedCountry = null;
		
		for (CountryEntity countryEntity : countries) {
			if (countryCode.getName().equals(countryEntity.getName())){
				selectedCountry = countryEntity;
			}
		}
		
		for (StateEntity stateEntity : selectedCountry.getStates()) {
			cities.addAll(stateEntity.getCities());
		}
		return cities;
	}
	
	
	
	public CountryCode getRandomCountry(){
		CountryHelper countryHelper = new CountryHelper();
		Set<CountryEntity> countryCodes = countryHelper.getCountryGeoId().keySet();
		int seed = ThreadLocalRandom.current().nextInt(countryCodes.size());
		return CountryCode.getByName(Lists.newArrayList(countryCodes).get(seed).getName());
	}

}
