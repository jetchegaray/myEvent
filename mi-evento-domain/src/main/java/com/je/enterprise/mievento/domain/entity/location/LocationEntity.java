package com.je.enterprise.mievento.domain.entity.location;

import org.mongodb.morphia.annotations.Embedded;

import com.je.enterprise.mievento.api.dto.location.CountryCode;

public class LocationEntity {

	@Embedded
	protected CountryCode countryCode;
	protected String province;
	protected String city;
	@Embedded
	protected StreetAddressEntity streetAddress;
	private String lat;
	private String lng;
	
	public LocationEntity() {
	}

	public LocationEntity(CountryCode countryCode, String province,
			String city, StreetAddressEntity streetAddress, String lat,
			String lng) {
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public StreetAddressEntity getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(StreetAddressEntity streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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
	
	@Override
	public String toString() {
		final String separator = " "; 
		StringBuilder builder = new StringBuilder();
		builder.append(this.streetAddress.getStreet()).append(separator);
		builder.append(this.streetAddress.getNumber()).append(separator);
		builder.append(this.streetAddress.getNeighborhood()).append(separator);
		builder.append(this.streetAddress.getAdditionalInfo()).append(separator);
		builder.append(this.province).append(separator);
		builder.append(this.city).append(".");
		
		return builder.toString();
	}
}
