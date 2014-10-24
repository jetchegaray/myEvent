package com.je.enterprise.mievento.domain.external.apiPlaces.entities;

public enum TypesAddressComponent {

	NUMBER("street_number"), STREET("route"), NEIGHBORHOOD("neighborhood"), 
	STATE_OR_PROVINCE("locality"), CITY("administrative_area_level_1"), 
	COUNTRY("country"), POSTAL_CODE("postal_code");
	
	private TypesAddressComponent(String name) {
		this.name = name;
	}
	
	private String name;

	public String getName() {
		return name;
	}
	
}
