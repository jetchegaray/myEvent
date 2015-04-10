package com.je.enterprise.mievento.domain.exception.customize;

import com.je.enterprise.mievento.domain.exception.HttpEventException;
import com.je.enterprise.mievento.domain.exception.HttpEventExceptionCode;
import com.je.enterprise.mievento.domain.exception.HttpEventExceptionHttpStatus;

public class LocationNotValidToSearchException extends HttpEventException {
	
	private static final long serialVersionUID = 1L;

	public LocationNotValidToSearchException() {
		super(HttpEventExceptionCode.INVALID_LOCATION, HttpEventExceptionHttpStatus.BAD_REQUEST,"La Ubicacion debe ser valida. Ninguno de los componentes debe estar vacio.");
	}
	
	
}
