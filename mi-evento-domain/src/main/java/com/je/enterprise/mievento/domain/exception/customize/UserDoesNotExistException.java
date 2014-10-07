package com.je.enterprise.mievento.domain.exception.customize;

import com.je.enterprise.mievento.domain.exception.HttpEventException;
import com.je.enterprise.mievento.domain.exception.HttpEventExceptionCode;
import com.je.enterprise.mievento.domain.exception.HttpEventExceptionHttpStatus;

public class UserDoesNotExistException extends HttpEventException {
	
	private static final long serialVersionUID = 1L;

	public UserDoesNotExistException() {
		super(HttpEventExceptionCode.USER_NOT_EXISTS, HttpEventExceptionHttpStatus.BAD_REQUEST,"El usuario no existe.");
	}
	
	
}
