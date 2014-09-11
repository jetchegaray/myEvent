package com.je.enterprise.mievento.domain.entity.location;

import org.mongodb.morphia.annotations.Embedded;

import com.je.enterprise.mievento.api.dto.CountryCode;

public class LocationEntity {

	@Embedded
	private CountryCode countryCode;
	private String province;
	@Embedded
	private StreetAddressEntity streetAddress;
	
	public LocationEntity() {
	}
	
	public LocationEntity(CountryCode countryCode, String province,
			StreetAddressEntity streetAddress) {
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

	public StreetAddressEntity getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(StreetAddressEntity streetAddress) {
		this.streetAddress = streetAddress;
	}

}
