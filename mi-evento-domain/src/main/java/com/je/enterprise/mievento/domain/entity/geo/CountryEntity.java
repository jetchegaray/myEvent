package com.je.enterprise.mievento.domain.entity.geo;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;

import com.je.enterprise.mievento.domain.dao.BaseEntity;

@Entity("countries")
public class CountryEntity extends BaseEntity{

	private String name;
	@Indexed(unique = true)
	private String countryCodeISOTwoLetter;
	
	@Embedded
	private List<StateEntity> states;
	
		
	public CountryEntity(String countryCodeISOTwoLetter,String name) {
		this.countryCodeISOTwoLetter = countryCodeISOTwoLetter;
		this.name = name;
	}
	

	public CountryEntity() {
	}

	public String getCountryCodeISOTwoLetter() {
		return countryCodeISOTwoLetter;
	}

	public void setCountryCodeISOTwoLetter(String countryCodeISOTwoLetter) {
		this.countryCodeISOTwoLetter = countryCodeISOTwoLetter;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public List<StateEntity> getStates() {
		return states;
	}


	public void setStates(List<StateEntity> states) {
		this.states = states;
	}

	
}
