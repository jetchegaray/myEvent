package com.je.enterprise.mievento.domain.exception;

public class HttpEventException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Long code;
	private Integer httpStatus;
	private String description;
	private String moreInfoURL;

	
	public HttpEventException(Long code, Integer httpStatus,
			String description) {
		this.code = code;
		this.httpStatus = httpStatus;
		this.description = description;
		this.moreInfoURL = "/error/"+code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMoreInfoURL() {
		return moreInfoURL;
	}

	public void setMoreInfoURL(String moreInfoURL) {
		this.moreInfoURL = moreInfoURL;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public Long getCode() {
		return code;
	}

}
