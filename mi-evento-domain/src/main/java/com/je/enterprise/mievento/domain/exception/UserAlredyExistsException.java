package com.je.enterprise.mievento.domain.exception;

public class UserAlredyExistsException extends HttpEventException {

	private static final long serialVersionUID = 1L;

	public UserAlredyExistsException() {
		super(HttpEventExceptionCode.USER_ALREDY_EXISTS, HttpEventExceptionHttpStatus.BAD_REQUEST,"El mail ingresado ya pertenece a otro usuario.");
	}
	
}
