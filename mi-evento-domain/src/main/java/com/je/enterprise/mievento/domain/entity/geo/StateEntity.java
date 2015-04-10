package com.je.enterprise.mievento.domain.entity.geo;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;

public class StateEntity {

	private String name;
	private String latitude;
	private String longuitud;
	@Embedded
	private List<CityEntity> cities;

	
	public StateEntity(String name, String latitude, String longuitud,
			List<CityEntity> cities) {
		this.name = name;
		this.latitude = latitude;
		this.longuitud = longuitud;
		this.cities = cities;
	}

	public StateEntity() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLonguitud() {
		return longuitud;
	}

	public void setLonguitud(String longuitud) {
		this.longuitud = longuitud;
	}

	public List<CityEntity> getCities() {
		return cities;
	}

	public void setCities(List<CityEntity> cities) {
		this.cities = cities;
	}
}
