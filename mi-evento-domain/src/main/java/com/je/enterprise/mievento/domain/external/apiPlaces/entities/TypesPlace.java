package com.je.enterprise.mievento.domain.external.apiPlaces.entities;

public enum TypesPlace {

	ESTABLISHMENT("establishment"), FOOD("food");
	
	private TypesPlace(String name) {
		this.name = name;
	}
	
	private String name;

	public String getName() {
		return name;
	}
	
}
