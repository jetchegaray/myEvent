package com.je.enterprise.mievento.domain.exception;

public class HttpEventException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String description;
	private Integer code;

	public HttpEventException(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
