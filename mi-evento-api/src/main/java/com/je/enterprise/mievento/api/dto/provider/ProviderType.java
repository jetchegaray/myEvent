package com.je.enterprise.mievento.api.dto.provider;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Lists;

public enum ProviderType {

	ALQUILER("ALQUILER"),
	AUTOESCUELA("AUTOESCUELA"),
	AUXILIO("AUXILIO"),
	CONCESIONARIA("CONCESIONARIA"),
	ES_SERVICIO("Estacion servicio"),
	ESTACIONAMIENTO("ESTACIONAMIENTO"),
	ESTETICA("ESTETICA automotor"),
	GESTORIA("GESTORIA"),
	MERCHAND("MERCHANDasing"),
	PARABRISAS_CRISTALES("PARABRISAS_CRISTALES"),
	REPUESTOS("REPUESTOS"),
	REVISACION("REVISACION"),
	SEGUROS("SEGUROS"),
	VTV("VTV"),
	TALLER_MECANICO("TALLER_MECANICO");
	
	private String name;
	
	private ProviderType(String name) {
		this.name = name;
	}
	
	
	@JsonValue
	public String getName() {
		return name;
	}
		
	
	public static List<String> stringValues(List<ProviderType> providerTypes){
		List<String> stringValues = Lists.<String>newArrayList();
		for(ProviderType type : providerTypes){
			stringValues.add(type.getName());
		}
		return stringValues;
	}
	
	@JsonCreator
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
	
	
	public static List<ProviderType> getPlaceTypes(){
		return Arrays.asList();
	}

	
}
