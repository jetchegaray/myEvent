package com.je.enterprise.mievento.api.dto.location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProvinceCode {
	BA("Buenos Aires"),CF("Buenos Aires"),CT("Catamarca"),CC("Chaco"),CH("Chubut"),CD("Córdoba"),CR("Corrientes"),
	ER("Entre Ríos"),FO("Formosa"),JY("Jujuy"),LP("La Pampa"),LR("La Rioja"),MZ("Mendoza"),
	MN("Misiones"),NQ("Neuquén"),RN("Río Negro"),SA("Salta"),SJ("San Juan"),SL("San Luis"),
	SC("Santa Cruz"),SF("Santa Fe"),SE("Santiago del Estero"),TF("Tierra del Fuego"),TM("Tucumán");
	
	private ProvinceCode(String name) {
		this.name = name;
	}
	
	private String name;

	@JsonValue
	public String getName() {
		return name;
	}
	
	@JsonCreator
	public static ProvinceCode getByName(String name){
		for(ProvinceCode country : values()){
			if(country.getName().equals(name)){
				return country;
			}
		}
		return null;
	}
	
	
}
