package com.je.enterprise.mievento.domain.external.apiGeo.services;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultExternalWrapper {
		
	@JsonProperty("geonames")
	List<GEOExternalWrapper> results;
	
	
	public ResultExternalWrapper() {
	}

	public List<GEOExternalWrapper> getResults() {
		return results;
	}

	public void setResults(List<GEOExternalWrapper> results) {
		this.results = results;
	}
	
}
