package com.je.enterprise.mievento.domain.entity.place;

import java.math.BigDecimal;
import java.util.List;

import com.je.enterprise.mievento.api.dto.provider.ProviderType;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderReviewEntity;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;

public class PlaceEntity extends ProviderEntity {

	private BigDecimal m2;
	private BigDecimal estimatedQuantityTables;
	private BigDecimal estimatedQuantityPerson;
	private ControlContextPlaceEntity contextPlaceEntity;
	
	public PlaceEntity() {
	}

	public PlaceEntity(String businessId, String businessName, String description, LocationEntity location,
			String email, String cellPhone, String phone, BigDecimal price,
			BigDecimal estimatedPrice, BigDecimal m2,
			BigDecimal estimatedQuantityTables,
			BigDecimal estimatedQuantityPerson,List<String> photos,ProviderType providerType,List<ProviderReviewEntity> reviews,ControlContextPlaceEntity contextPlaceEntity) {
		
		super(businessId, businessName, description, location, email, cellPhone, phone,
				price, estimatedPrice,photos,providerType,reviews);
		this.m2 = m2;
		this.estimatedQuantityTables = estimatedQuantityTables;
		this.estimatedQuantityPerson = estimatedQuantityPerson;
		this.contextPlaceEntity = contextPlaceEntity;
	}
	
	public PlaceEntity(ProviderEntity providerEntity) {
		this(providerEntity.getBusinessId(), providerEntity.getBusinessName(), providerEntity.getDescription(), providerEntity.getLocation(), providerEntity.getEmail(), providerEntity.getCellPhone(), providerEntity.getPhone(),
				providerEntity.getPrice(), providerEntity.getEstimatedPrice(), null, null, null, providerEntity.getPhotos(),providerEntity.getProviderType(), providerEntity.getReviews(),null);
	}

	public BigDecimal getM2() {
		return m2;
	}

	public void setM2(BigDecimal m2) {
		this.m2 = m2;
	}

	public BigDecimal getEstimatedQuantityTables() {
		return estimatedQuantityTables;
	}

	public void setEstimatedQuantityTables(BigDecimal estimatedQuantityTables) {
		this.estimatedQuantityTables = estimatedQuantityTables;
	}

	public BigDecimal getEstimatedQuantityPerson() {
		return estimatedQuantityPerson;
	}

	public void setEstimatedQuantityPerson(BigDecimal estimatedQuantityPerson) {
		this.estimatedQuantityPerson = estimatedQuantityPerson;
	}

	public ControlContextPlaceEntity getContextPlaceEntity() {
		return contextPlaceEntity;
	}

	public void setContextPlaceEntity(ControlContextPlaceEntity contextPlaceEntity) {
		this.contextPlaceEntity = contextPlaceEntity;
	}
}
