package com.je.enterprise.mievento.api.dto.error;

public class EventError {

	private Long code;
	private Integer httpStatus;
	private String description;
	private String moreInfoURL;
	
	
	public EventError(Long code, Integer httpStatus, String description,String moreInfoURL) {
		this.code = code;
		this.httpStatus = httpStatus;
		this.description = description;
		this.moreInfoURL = moreInfoURL;
	}
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public Integer getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMoreInfoURL() {
		return moreInfoURL;
	}
	public void setMoreInfoURL(String moreInfoURL) {
		this.moreInfoURL = moreInfoURL;
	}

}
