package com.je.enterprise.mievento.domain.external.apiPlaces.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchPlaces {

	private String status;
	@JsonProperty("results")
	private List<SearchPlace> places;

	public SearchPlaces() {
	}
	
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<SearchPlace> getPlaces() {
		return places;
	}

	public void setPlaces(List<SearchPlace> places) {
		this.places = places;
	}
	
}
