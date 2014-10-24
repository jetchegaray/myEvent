package com.je.enterprise.mievento.domain.external.apiPlaces.entities;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class DetailPlace {

	String id;
	String reference;
	String name;
	
	@JsonProperty("address_components")
	List<AddressComponent> address;
	
	@JsonProperty("formatted_phone_number")
	String phone;
	
	@JsonProperty("international_phone_number")
	String internationalPhone;
	
	List<Photo> photos;
	List<String> types;	
	
	Geometry geometry;
	Double rating;
	List<Review> reviews;
	
	public DetailPlace() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AddressComponent> getAddress() {
		return address;
	}

	public void setAddress(List<AddressComponent> address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getInternationalPhone() {
		return internationalPhone;
	}

	public void setInternationalPhone(String internationalPhone) {
		this.internationalPhone = internationalPhone;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	
}
