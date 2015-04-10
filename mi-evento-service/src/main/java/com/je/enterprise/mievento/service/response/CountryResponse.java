package com.je.enterprise.mievento.service.response;

import java.util.Map;

import com.je.enterprise.mievento.api.dto.country.Country;
import com.je.enterprise.mievento.api.dto.location.CountryCode;

public class CountryResponse {

	private Map<CountryCode, Country> countries;
	
	public CountryResponse() {
	}
	
	public CountryResponse(Map<CountryCode, Country> countries) {
		this.countries = countries;
	}


	public Map<CountryCode, Country> getCountries() {
		return countries;
	}

	public void setCountries(Map<CountryCode, Country> countries) {
		this.countries = countries;
	}
	
}
