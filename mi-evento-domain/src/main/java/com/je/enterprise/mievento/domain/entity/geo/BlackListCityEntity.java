package com.je.enterprise.mievento.domain.entity.geo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.mongodb.morphia.annotations.Entity;

import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.domain.dao.BaseEntity;

@Entity("blackListCities")
public class BlackListCityEntity extends BaseEntity{

	private String cityName;
	private CountryCode countryCode;

	public BlackListCityEntity() {
	}
	
	
	public BlackListCityEntity(String cityName, CountryCode countryCode) {
		super();
		this.cityName = cityName;
		this.countryCode = countryCode;
	}



	public String getCityName() {
		return cityName;
	}



	public void setCityName(String cityName) {
		this.cityName = cityName;
	}



	public CountryCode getCountryCode() {
		return countryCode;
	}



	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
	}


	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
