package com.je.enterprise.mievento.api.dto.provider;

import java.math.BigDecimal;

public class Review {
	
	private String userName;
	private String message;
	private BigDecimal rating;
	
	public Review(String userName, String message,BigDecimal rating) {
		this.userName = userName;
		this.message = message;
		this.rating = rating;
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
