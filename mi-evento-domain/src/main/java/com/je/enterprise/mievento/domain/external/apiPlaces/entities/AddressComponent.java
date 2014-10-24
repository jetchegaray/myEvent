package com.je.enterprise.mievento.domain.external.apiPlaces.entities;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class AddressComponent {
	
	@JsonProperty("long_name")
	String longName;
	@JsonProperty("short_name")
	String shortName;
	@JsonProperty("types")
	List<String> adressTypes;
	
	public AddressComponent() {
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public List<String> getAdressTypes() {
		return adressTypes;
	}

	public void setAdressTypes(List<String> adressTypes) {
		this.adressTypes = adressTypes;
	}

	
}
