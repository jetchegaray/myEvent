package com.je.enterprise.mievento.domain.external.apiPlaces.process;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.domain.entity.geo.CityEntity;

public class CitiesWithNoResultTest {

	
	private CitiesBlackList CitiesWithNoResult; 
	
	@Before
	public void setUp(){
		this.CitiesWithNoResult = new CitiesBlackList();
		CitiesWithNoResult.load();
		
		CitiesWithNoResult.setCityNoResult(CountryCode.AR, new CityEntity("Lomas del mirador", StringUtils.EMPTY, StringUtils.EMPTY));
		CitiesWithNoResult.setCityNoResult(CountryCode.AR, new CityEntity("General Villegas", StringUtils.EMPTY, StringUtils.EMPTY));
		CitiesWithNoResult.setCityNoResult(CountryCode.AR, new CityEntity("General Pinto", StringUtils.EMPTY, StringUtils.EMPTY));
		CitiesWithNoResult.setCityNoResult(CountryCode.AR, new CityEntity("General Granada", StringUtils.EMPTY, StringUtils.EMPTY));
	}
	
	@Test
	public void getKeywords_ok() throws IOException{

				
	}
	
}
