package com.je.enterprise.mievento.domain.entity.common;

import org.mongodb.morphia.annotations.Embedded;

public class Location {

	@Embedded
	private CountryCode countryCode;
	private String province;
	@Embedded
	private StreetAddress streetAddress;

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
