package com.je.enterprise.mievento.service.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.je.enterprise.mievento.api.dto.error.EventError;
import com.je.enterprise.mievento.domain.exception.HttpEventException;
import com.je.enterprise.mievento.domain.transformer.impl.EventErrorTransformer;

@ControllerAdvice
public class BasicController {
	
	@Autowired
	private EventErrorTransformer eventErrorTransformer;
	
	
	@ExceptionHandler(HttpEventException.class)
	@ResponseStatus (HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public EventError handleException(HttpEventException exception, HttpServletResponse response) {
		EventError error = this.eventErrorTransformer.transformAndValidateDomainToApi(exception);
		return error;
	}	
	
	

}
