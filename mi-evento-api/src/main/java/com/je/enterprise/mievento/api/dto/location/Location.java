package com.je.enterprise.mievento.api.dto.location;



public class Location {
	
	private CountryCode countryCode;
	private ProvinceCode province;
	private String city;
	private StreetAddress streetAddress;
	
	public Location() {
	}
	
	public Location(CountryCode countryCode, ProvinceCode province,String city,
			StreetAddress streetAddress) {
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

}
