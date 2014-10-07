package com.je.enterprise.mievento.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.je.enterprise.mievento.domain.exception.HttpEventException;

public class BasicController {
	
	@ExceptionHandler(HttpEventException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleExceptions(Exception ex) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.writeValueAsString(ex));
			return mapper.writeValueAsString(ex.getMessage());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
