package com.je.enterprise.mievento.domain.exception;

public class UserDoesNotExistException extends HttpEventException {
	
	private static final long serialVersionUID = 1L;

	public UserDoesNotExistException() {
		super(HttpEventExceptionCode.USER_NOT_EXISTS, HttpEventExceptionHttpStatus.BAD_REQUEST,"El usuario no existe.");
	}
	
	
}
