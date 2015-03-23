package com.je.enterprise.mievento.domain.entity.geo;

public class CityEntity {

	private String name;
	private String latitude;
	private String longuitud;

	public CityEntity() {
	}
	
	public CityEntity(String name, String latitude, String longuitud) {
		this.name = name;
		this.latitude = latitude;
		this.longuitud = longuitud;
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
	
	public String getLatLongToSearch(){
		return new StringBuilder(this.latitude).append(",").append(this.longuitud).toString();
	}
}
