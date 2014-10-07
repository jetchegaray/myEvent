package com.je.enterprise.mievento.service.controller;


import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Preconditions;
import com.je.enterprise.mievento.api.dto.error.EventError;
import com.je.enterprise.mievento.api.dto.user.User;
import com.je.enterprise.mievento.domain.exception.HttpEventException;
import com.je.enterprise.mievento.domain.exception.customize.UserDoesNotExistException;
import com.je.enterprise.mievento.domain.service.impl.UserService;
import com.je.enterprise.mievento.domain.transformer.impl.EventErrorTransformer;
import com.je.enterprise.mievento.domain.transformer.impl.UserTransformer;

@Controller
@RequestMapping(value= "/user")
public class UserController{
	
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserTransformer userTransformer;
	@Autowired
	private EventErrorTransformer eventErrorTransformer;
	
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
		public User login(@RequestBody User user) throws HttpEventException{
			return userTransformer.transformDomainToApi(userService.login(user.getEmail(),user.getPassword()));
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public void signUp(@RequestBody User user) throws HttpEventException{
		userService.signUp(user.getEmail(), user.getPassword());
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE)
	public void logout(String token){
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/email",method = RequestMethod.PUT)
	public void forgottenPassword(@RequestBody User user) throws HttpEventException{
		this.userService.sendMail(user.getEmail());
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public void update(@RequestBody User user) throws HttpEventException{
	
		try{
			 
			Preconditions.checkNotNull(user);
			this.userService.update(this.userTransformer.transformApiToDomain(user));
			
		}catch(Exception ex){
			throw new UserDoesNotExistException();
		}		
	}
	
	
	
	@ExceptionHandler(HttpEventException.class)
	@ResponseBody
	public EventError handleException(HttpEventException exception, HttpServletResponse response) {
		
		EventError error = this.eventErrorTransformer.transformAndValidateDomainToApi(exception);
       return error;
	}
	
}
