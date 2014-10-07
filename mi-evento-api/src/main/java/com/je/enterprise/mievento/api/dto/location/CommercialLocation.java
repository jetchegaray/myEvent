package com.je.enterprise.mievento.api.dto.location;

import com.je.enterprise.mievento.api.dto.location.CountryCode;


public class CommercialLocation extends Location{

	private String placeName;

	public CommercialLocation() {
		super();
	}

	public CommercialLocation(String placeName,CountryCode countryCode, String province,String city,
			StreetAddress streetAddress) {
		super(countryCode, province,city, streetAddress);
		this.placeName = placeName;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	
}
