package com.je.enterprise.mievento.domain.external.apiPlaces.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailPlace {

	private String id;
	private String reference;
	private String name;
	
	@JsonProperty("address_components")
	private List<AddressComponent> address;
	
	@JsonProperty("formatted_phone_number")
	private String phone;
	
	@JsonProperty("international_phone_number")
	private String internationalPhone;
	
	private List<Photo> photos;
	private List<String> types;	
	
	private Geometry geometry;
	private Double rating;
	private List<Review> reviews;
	
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
	
	public List<String> getPhotoReferences(){
		return Lists.transform(this.photos, new Function<Photo, String>() {
			@Override
			public String apply(Photo input) {
				return input.getReference();
			}
		});
	}
	
	
}
