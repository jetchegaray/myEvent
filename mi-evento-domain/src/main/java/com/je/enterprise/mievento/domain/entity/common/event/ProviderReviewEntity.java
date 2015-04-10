package com.je.enterprise.mievento.domain.entity.common.event;

import java.math.BigDecimal;

public class ProviderReviewEntity {

	private String userName;
	private String message;
	private BigDecimal rating;
	
	public ProviderReviewEntity(String userName, String message,BigDecimal rating) {
		this.userName = userName;
		this.message = message;
		this.rating = rating;
	}
	
	public ProviderReviewEntity() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BigDecimal getRating() {
		return rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}
	
	
}
