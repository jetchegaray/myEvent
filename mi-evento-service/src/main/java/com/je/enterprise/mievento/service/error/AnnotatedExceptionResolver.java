package com.je.enterprise.mievento.service.error;

import org.springframework.stereotype.Component;


@Component
public class AnnotatedExceptionResolver extends org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver{

	public AnnotatedExceptionResolver(){
		this.setOrder(HIGHEST_PRECEDENCE);
	}
}
