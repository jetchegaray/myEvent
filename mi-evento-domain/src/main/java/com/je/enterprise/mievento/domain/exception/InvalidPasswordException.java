package com.je.enterprise.mievento.domain.exception;

public class InvalidPasswordException extends HttpEventException {

	private static final long serialVersionUID = 1L;
	
	public InvalidPasswordException() {
		super(HttpEventExceptionCode.INVALID_CREDENTIAL,HttpEventExceptionHttpStatus.UNAUTHORIZED, "El password ingresado no es correcto. Intente de nuevo !");
	}

}
