package com.je.enterprise.mievento.api.dto.provider;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.je.enterprise.mievento.api.dto.location.Location;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Provider {

	private String businessId;
	private String businessName;
	private String description;
	private Location location;
	private String email;
	private String cellPhone;
	private String phone;
	private BigDecimal price;
	private BigDecimal estimatedPrice;
	private List<String> photos;
	private ProviderType providerType;
	private List<Review> reviews;

	public Provider(String businessId,String businessName, String description,
			Location location, String email, String cellPhone, String phone,
			BigDecimal price, BigDecimal estimatedPrice,List<String> photos, ProviderType providerType,List<Review> reviews) {
		this.businessName = businessName;
		this.description = description;
		this.location = location;
		this.email = email;
		this.cellPhone = cellPhone;
		this.phone = phone;
		this.price = price;
		this.estimatedPrice = estimatedPrice;
		this.photos = photos;
		this.providerType = providerType;
		this.reviews = reviews;
		this.businessId = businessId;
	}

	public Provider() {
		super();
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getEstimatedPrice() {
		return estimatedPrice;
	}

	public void setEstimatedPrice(BigDecimal estimatedPrice) {
		this.estimatedPrice = estimatedPrice;
	}

	public List<String> getPicture() {
		return photos;
	}

	public void setPicture(List<String> photos) {
		this.photos = photos;
	}


	public ProviderType getProviderType() {
		return providerType;
	}


	public void setProviderType(ProviderType providerType) {
		this.providerType = providerType;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

}
