package com.je.enterprise.mievento.domain.entity.location;

import org.mongodb.morphia.annotations.Embedded;

import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.api.dto.location.ProvinceCode;

public class LocationEntity {

	@Embedded
	protected CountryCode countryCode;
	protected ProvinceCode province;
	protected String city;
	@Embedded
	protected StreetAddressEntity streetAddress;
	private String lat;
	private String lng;
	
	public LocationEntity() {
	}
	
	public LocationEntity(CountryCode countryCode, ProvinceCode province,String city,
			StreetAddressEntity streetAddress) {
		this.countryCode = countryCode;
		this.province = province;
		this.city = city;
		this.streetAddress = streetAddress;
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

}
