package com.je.enterprise.mievento.domain.exception.customize;

import com.je.enterprise.mievento.domain.exception.HttpEventException;
import com.je.enterprise.mievento.domain.exception.HttpEventExceptionCode;
import com.je.enterprise.mievento.domain.exception.HttpEventExceptionHttpStatus;


public class NotExistanceProvidersException extends HttpEventException {

	private static final long serialVersionUID = 1L;

	public NotExistanceProvidersException() {
		super(HttpEventExceptionCode.PROVIDERS_DONT_EXISTS,HttpEventExceptionHttpStatus.INTERNAL_SERVER_ERROR, "Provider don't exists in Data Base");
	}
}
