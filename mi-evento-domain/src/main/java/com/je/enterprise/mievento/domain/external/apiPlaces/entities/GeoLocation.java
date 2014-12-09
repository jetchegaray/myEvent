package com.je.enterprise.mievento.domain.external.apiPlaces.entities;

import com.fasterxml.jackson.annotation.JsonProperty;


public class GeoLocation {

	@JsonProperty("lat")
	private Double latitude;
	@JsonProperty("lng")
	private Double longitude;
	
	public GeoLocation() {
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		return this.latitude+","+this.longitude;
	}
	
}
