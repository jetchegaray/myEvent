package com.je.enterprise.mievento.domain.exception;

public class UserNotExistException extends HttpEventException {
	
	private static final long serialVersionUID = 1L;

	public UserNotExistException() {
		super(HttpEventExceptionCode.USER_NOT_EXISTS, "El usuario no existe.");
	}
	
	
}
