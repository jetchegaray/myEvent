package com.je.enterprise.mievento.api.dto;


public class Location {
	
	private CountryCode countryCode;
	private String province;
	private StreetAddress streetAddress;
	
	public Location() {
	}
	
	public Location(CountryCode countryCode, String province,
			StreetAddress streetAddress) {
		this.countryCode = countryCode;
		this.province = province;
		this.streetAddress = streetAddress;
	}

	public CountryCode getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public StreetAddress getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(StreetAddress streetAddress) {
		this.streetAddress = streetAddress;
	}

}
