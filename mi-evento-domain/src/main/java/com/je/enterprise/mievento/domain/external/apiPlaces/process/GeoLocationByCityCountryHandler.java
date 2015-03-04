package com.je.enterprise.mievento.domain.external.apiPlaces.process;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.domain.utils.LoadFile;
import com.je.enterprise.mievento.domain.utils.PropertiesHelper;

@Component
public class GeoLocationByCityCountryHandler implements LoadFile{

	Properties properties;
	private CitiesByCountryHandler citiesByCountryHandler;
	
	@Autowired
	public GeoLocationByCityCountryHandler(CitiesByCountryHandler citiesByCountryHandler) {
		this.citiesByCountryHandler = citiesByCountryHandler;
	}
	
	@PostConstruct
	public void load(){
		PropertiesHelper helper = new PropertiesHelper("com/je/enterprise/mievento/domain/apiPlaces/geolocations.properties");
		this.properties = helper.load();
	}
	
	
	public Map<String, String> getGeoLocationByCity(){
		Map<CountryCode, List<String>> citiesCountryAbv = citiesByCountryHandler.getCitiesByCountry();
		Map<String, String> latlngByCity = Maps.<String, String>newHashMap();
    	 
    	 for (CountryCode countryCode : CountryCode.values())  {
    		 for (String city : citiesCountryAbv.get(countryCode)) {
    			 String lngLatValues = String.valueOf(properties.get(city+"+"+countryCode.getName()));
    			 
				 latlngByCity.put(city, lngLatValues);
    		 }
		}
    	return latlngByCity;		
	}
	
	
}
