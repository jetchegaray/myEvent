package com.je.enterprise.mievento.api.dto.provider;

import java.util.List;

import com.google.common.collect.Lists;

public enum ProviderType {

	CATERING("Catering"),
	WEDDING_HALL("Salon de Bodas"),
	PHOTOGRAPHER("fotografos"),
	VIDEO("Videos"),
	DJ("Dj"),
	BIRTHDAY_HALL("Salon Para Cumpleaños o Fiestas"),
	BAR_MITZVAH_HALL("Salon Bar Mitzvah"),
	DRESSES_SUITS("dresses and suits"),
	RINGS("Alianzas o Anillos De Compromiso"),
	CAKES("Tortas");
	
	private String name;
	
	private ProviderType() {
	}
	
	private ProviderType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public static List<String> stringValues(){
		List<String> stringValues = Lists.<String>newArrayList();
		for(ProviderType type : values()){
			stringValues.add(type.getName());
		}
		return stringValues;
	}
	
	public static ProviderType getByName(String name){
		for(ProviderType type : values()){
			if(type.getName().equals(name)){
				return type;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return getName();
	}

	
}
