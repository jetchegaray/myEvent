package com.je.enterprise.mievento.domain.external.apiPlaces.process;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.Geometry;
import com.je.enterprise.mievento.domain.external.apiPlaces.services.ApiGeoServicies;

@Component
public class CitiesInfoCache {

	CitiesByCountryHandler citiesByCountryHandler;
	ApiGeoServicies apiGeoServicies;
	LoadingCache<String, String> cache;
	
	@Autowired
	public CitiesInfoCache(CitiesByCountryHandler citiesByCountryHandler,ApiGeoServicies apiGeoServicies) {
		this.citiesByCountryHandler = citiesByCountryHandler;
		this.apiGeoServicies = apiGeoServicies;
	}

	@PostConstruct
	public void init(){
		cache = CacheBuilder.newBuilder()
		       .expireAfterAccess(10, TimeUnit.SECONDS)
		       .build(
		           new CacheLoader<String, String>() {
						@Override
						public String load(String key) throws Exception {
							return CitiesInfoCache.this.getData().get(key);
						}
		        });
	}
	
	public String getLatlngFromCityAbrv(String city){
		return this.cache.getUnchecked(city);
	}
		           
		           
     public Map<String, String> getData(){
    	 Map<CountryCode, List<String>> citiesCountryAbv = citiesByCountryHandler.getCitiesByCountry();
    	 Map<String, String> latlngByCity = Maps.<String, String>newHashMap();
    	 
    	 for (CountryCode countryCode : CountryCode.values())  {
    		 for (String city : citiesCountryAbv.get(countryCode)) {
    			 //na ex : CT+ARGENTINA
    			 Geometry geometry = apiGeoServicies.getGeoPosition(city+"+"+countryCode.getName());
    			 latlngByCity.put(city, geometry.getLocation().toString());
			 }
		}
    	return latlngByCity;
     }

}
