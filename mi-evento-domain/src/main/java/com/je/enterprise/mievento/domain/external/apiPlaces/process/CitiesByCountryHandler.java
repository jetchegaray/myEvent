package com.je.enterprise.mievento.domain.external.apiPlaces.process;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.domain.utils.LoadFile;
import com.je.enterprise.mievento.domain.utils.PropertiesHelper;

@Component
@Deprecated
public class CitiesByCountryHandler implements LoadFile{

	Properties properties;
	
	@PostConstruct
	public void load(){
		PropertiesHelper helper = new PropertiesHelper("com/je/enterprise/mievento/domain/apiPlaces/citiesByCountry.properties");
		this.properties = helper.load();
	}
	
	
	public Map<CountryCode, List<String>> getCitiesByCountry(){
		
		Map<CountryCode, List<String>> citiesByCountry = Maps.<CountryCode, List<String>>newHashMap();
		for (CountryCode countryCode : CountryCode.values()) {
		
			String value = String.valueOf(properties.get(countryCode.getName()));
			
			String[] citiesAbrv = value.split(",");
			
			citiesByCountry.put(countryCode, Arrays.asList(citiesAbrv));
		}
		return citiesByCountry;
	}
	
	
}
