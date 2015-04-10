package com.je.enterprise.mievento.api.dto.location;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
	
	private CountryCode countryCode;
	private ProvinceCode province;
	private String city;
	private StreetAddress streetAddress;
	private String  lat;
	private String lng;
	
	public Location() {
	}
	
	public Location(CountryCode countryCode, ProvinceCode province,
			String city, StreetAddress streetAddress, String lat, String lng) {
		this.countryCode = countryCode;
		this.province = province;
		this.city = city;
		this.streetAddress = streetAddress;
		this.lat = lat;
		this.lng = lng;
	}

	public CountryCode getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
	}

	public ProvinceCode getProvince() {
		return province;
	}

	public void setProvince(ProvinceCode province) {
		this.province = province;
	}

	public StreetAddress getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(StreetAddress streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(" Country : ").append(this.countryCode);
		builder.append(" Province : ").append(province).append(" City : ").append(this.city);
		return builder.toString();
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}


	
}
