package com.je.enterprise.mievento.domain.exception.customize;

import com.je.enterprise.mievento.domain.exception.HttpEventException;
import com.je.enterprise.mievento.domain.exception.HttpEventExceptionCode;
import com.je.enterprise.mievento.domain.exception.HttpEventExceptionHttpStatus;

public class InvalidCredentialException extends HttpEventException {

	private static final long serialVersionUID = 2L;

	public InvalidCredentialException() {
		super(HttpEventExceptionCode.INVALID_CREDENTIAL_USER,HttpEventExceptionHttpStatus.BAD_REQUEST ,"El Usuario no existe.Registrese !");
	}

}
