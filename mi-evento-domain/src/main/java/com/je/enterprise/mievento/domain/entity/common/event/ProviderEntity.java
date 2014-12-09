package com.je.enterprise.mievento.domain.entity.common.event;

import java.math.BigDecimal;
import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

import com.je.enterprise.mievento.api.dto.provider.ProviderType;
import com.je.enterprise.mievento.domain.dao.BaseEntity;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;

@Entity("providers")
public class ProviderEntity extends BaseEntity {

	private String businessName;
	private String description;
	@Embedded
	private LocationEntity location;
	private String email;
	private String cellPhone;
	private String phone;
	private BigDecimal price;
	private BigDecimal estimatedPrice;
	private List<String> photos;
	private ProviderType providerType;

	
	public ProviderEntity() {
	}

	public ProviderEntity(String businessName, String description,
			LocationEntity location, String email, String cellPhone, String phone,
			BigDecimal price, BigDecimal estimatedPrice,List<String> photos,ProviderType providerType) {
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
		return photos;
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

}
