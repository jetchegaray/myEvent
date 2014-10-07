package com.je.enterprise.mievento.domain.exception;


public class NotExistanceProvidersException extends HttpEventException {

	private static final long serialVersionUID = 1L;

	public NotExistanceProvidersException() {
		super(HttpEventExceptionCode.PROVIDERS_DONT_EXISTS,HttpEventExceptionHttpStatus.INTERNAL_SERVER_ERROR, "Provider don't exists in Data Base");
	}
}
