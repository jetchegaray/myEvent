package com.je.enterprise.mievento.domain.exception.customize;

import com.je.enterprise.mievento.domain.exception.HttpEventException;
import com.je.enterprise.mievento.domain.exception.HttpEventExceptionCode;
import com.je.enterprise.mievento.domain.exception.HttpEventExceptionHttpStatus;

public class UserAlredyExistsException extends HttpEventException {

	private static final long serialVersionUID = 1L;

	public UserAlredyExistsException() {
		super(HttpEventExceptionCode.USER_ALREDY_EXISTS, HttpEventExceptionHttpStatus.BAD_REQUEST,"El mail ingresado ya pertenece a otro usuario.");
	}
	
}
