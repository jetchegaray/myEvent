package com.je.enterprise.mievento.domain.external.apiPlaces.entities;

import org.codehaus.jackson.annotate.JsonProperty;

public class GeoLocation {

	@JsonProperty("lat")
	Double latitude;
	@JsonProperty("lng")
	Double longitude;
	
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
	
	
}
