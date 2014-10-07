package com.je.enterprise.mievento.domain.exception;

public class InvalidCredentialException extends HttpEventException {

	private static final long serialVersionUID = 2L;

	public InvalidCredentialException() {
		super(HttpEventExceptionCode.INVALID_CREDENTIAL,HttpEventExceptionHttpStatus.BAD_REQUEST ,"El Usuario no existe.Registrese !");
	}

}
