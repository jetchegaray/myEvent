package com.je.enterprise.mievento.domain.external.apiPlaces.process;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Component
@Deprecated
public class CitiesInfoCache {

	private GeoLocationByCityCountryHandler geoLocationByCityCountryHandler;
	private LoadingCache<String, String> cache;
	private static final Logger logger = Logger.getLogger(CitiesInfoCache.class);
 
	
	@Autowired
	public CitiesInfoCache(GeoLocationByCityCountryHandler geoLocationByCityCountryHandler) {
		this.geoLocationByCityCountryHandler = geoLocationByCityCountryHandler;
	}

	@PostConstruct
	public void init(){
		cache = CacheBuilder.newBuilder()
		       .expireAfterAccess(10, TimeUnit.HOURS)
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
    	return geoLocationByCityCountryHandler.getGeoLocationByCity();
     }

}
