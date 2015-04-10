package com.je.enterprise.mievento.domain.exception.customize;

import com.je.enterprise.mievento.domain.exception.HttpEventException;
import com.je.enterprise.mievento.domain.exception.HttpEventExceptionCode;
import com.je.enterprise.mievento.domain.exception.HttpEventExceptionHttpStatus;

public class FailedSendInvitationException extends HttpEventException {

	private static final long serialVersionUID = 1L;
	
	public FailedSendInvitationException(String email) {
		super(HttpEventExceptionCode.FAIL_MAIL,HttpEventExceptionHttpStatus.INTERNAL_SERVER_ERROR, "Fallo el mail al enviarse a la direccion "+email+".");
	}

}
