package com.je.enterprise.mievento.domain.exception.customize;

import com.je.enterprise.mievento.domain.exception.HttpEventException;
import com.je.enterprise.mievento.domain.exception.HttpEventExceptionCode;
import com.je.enterprise.mievento.domain.exception.HttpEventExceptionHttpStatus;

public class InvalidPasswordException extends HttpEventException {

	private static final long serialVersionUID = 1L;
	
	public InvalidPasswordException() {
		super(HttpEventExceptionCode.INVALID_CREDENTIAL,HttpEventExceptionHttpStatus.UNAUTHORIZED, "El password ingresado no es correcto. Intente de nuevo !");
	}

}
