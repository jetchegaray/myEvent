package com.je.enterprise.mievento.api.dto.provider;

import java.math.BigDecimal;
import java.util.List;

import com.je.enterprise.mievento.api.dto.location.Location;

public class Provider {

	private String businessName;
	private String description;
	private Location location;
	private String email;
	private String cellPhone;
	private String phone;
	private BigDecimal price;
	private BigDecimal estimatedPrice;
	private String picture;
	private ProviderType providerType;


	public Provider(String businessName, String description,
			Location location, String email, String cellPhone, String phone,
			BigDecimal price, BigDecimal estimatedPrice,String picture, ProviderType providerType) {
		this.businessName = businessName;
		this.description = description;
		this.location = location;
		this.email = email;
		this.cellPhone = cellPhone;
		this.phone = phone;
		this.price = price;
		this.estimatedPrice = estimatedPrice;
		this.picture = picture;
		this.providerType = providerType;
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}



	public ProviderType getProviderType() {
		return providerType;
	}



	public void setProviderType(ProviderType providerType) {
		this.providerType = providerType;
	}

}
