package com.je.enterprise.mievento.domain.entity.common.event;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;

import com.google.common.collect.Lists;
import com.je.enterprise.mievento.api.dto.provider.ProviderType;
import com.je.enterprise.mievento.domain.dao.BaseEntity;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;

@Entity("providers")
public class ProviderEntity extends BaseEntity {

	@Indexed(unique = true)
	private String businessId;
	
	private String businessName;
	private String description;
	@Embedded
	private LocationEntity location;
	private String email;
	private String cellPhone;
	@Deprecated
	private String phone;
	private BigDecimal price;
	private BigDecimal estimatedPrice;
	private List<String> photos;
	private ProviderType providerType;
	private List<ProviderReviewEntity> reviews;
	
	public ProviderEntity() {
	}

	public ProviderEntity(String businessId, String businessName, String description,
			LocationEntity location, String email, String cellPhone, String phone,
			BigDecimal price, BigDecimal estimatedPrice,List<String> photos,ProviderType providerType,List<ProviderReviewEntity> reviews) {
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

	@Deprecated
	public String getPhone() {
		return phone;
	}

	@Deprecated
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocationEntity getLocation() {
		return location;
	}

	public void setLocation(LocationEntity location) {
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

	public List<String> getPhotos() {
		if (photos != null){
			return photos;
		}
		return Collections.emptyList();
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

	public ProviderType getProviderType() {
		return providerType;
	}

	public void setProviderType(ProviderType providerType) {
		this.providerType = providerType;
	}

	public List<ProviderReviewEntity> getReviews() {
		if (reviews != null){
			return reviews;
		}
		return Lists.newArrayList();
	}

	public void setReviews(List<ProviderReviewEntity> reviews) {
		this.reviews = reviews;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

}
