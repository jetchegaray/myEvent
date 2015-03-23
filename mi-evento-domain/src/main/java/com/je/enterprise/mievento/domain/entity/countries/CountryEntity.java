package com.je.enterprise.mievento.domain.entity.countries;

import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;

import com.je.enterprise.mievento.domain.dao.BaseEntity;
import com.je.enterprise.mievento.domain.external.countries.services.CountryStatesServicies.CountryExternalWrapper;

@Entity("countries")
public class CountryEntity extends BaseEntity{

	private String name;
	@Indexed(unique = true)
	private String countryCodeISOTwoLetter;
	private String countryCodeISOThreeLetter;
	private String imageFlag;
	
	@Embedded
	private List<StateEntity> states;
	
		
	public CountryEntity(String countryCodeISOTwoLetter,
			String countryCodeISOThreeLetter, String name, String imageFlag) {
		this.countryCodeISOTwoLetter = countryCodeISOTwoLetter;
		this.countryCodeISOThreeLetter = countryCodeISOThreeLetter;
		this.name = name;
		this.imageFlag = imageFlag;
	}
	
	public CountryEntity(CountryExternalWrapper countryExternalWrapper){
		this(countryExternalWrapper.getAlpha2Code(), countryExternalWrapper.getAlpha3Code(), countryExternalWrapper.getName(), StringUtils.EMPTY);
	}

	public CountryEntity() {
	}

	public String getCountryCodeISOTwoLetter() {
		return countryCodeISOTwoLetter;
	}

	public void setCountryCodeISOTwoLetter(String countryCodeISOTwoLetter) {
		this.countryCodeISOTwoLetter = countryCodeISOTwoLetter;
	}

	public String getCountryCodeISOThreeLetter() {
		return countryCodeISOThreeLetter;
	}

	public void setCountryCodeISOThreeLetter(String countryCodeISOThreeLetter) {
		this.countryCodeISOThreeLetter = countryCodeISOThreeLetter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageFlag() {
		return imageFlag;
	}

	public void setImageFlag(String imageFlag) {
		this.imageFlag = imageFlag;
	}
	
	
	public static void main(String[] args) {
		String[] countries = Locale.getISOCountries();
		for (String string : countries) {
			System.out.println(new Locale("", string).getDisplayCountry());
		}
	
		
	}
	
}
