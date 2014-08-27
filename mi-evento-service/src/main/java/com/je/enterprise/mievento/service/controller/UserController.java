package com.je.enterprise.mievento.service.controller;


import javax.management.RuntimeErrorException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.je.enterprise.mievento.api.dto.User;
import com.je.enterprise.mievento.domain.entity.common.UserEntity;
import com.je.enterprise.mievento.domain.service.impl.UserService;
import com.je.enterprise.mievento.domain.transformer.ApiToDomainTransformer;
import com.je.enterprise.mievento.domain.transformer.impl.UserApiToDomainTransformer;

@Controller
public class UserController {
	
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserApiToDomainTransformer UserApiToDomainTransformer;
	
	@RequestMapping(value= "/login",method = RequestMethod.POST)
	public @ResponseBody void login(@RequestBody User user){
		
		UserEntity entity = UserApiToDomainTransformer.transform(user);
		
		userService.create(entity);
		logger.info("new User with mail"+entity.getMail());
	}
	
	
	@RequestMapping(value= "/signUp",method = RequestMethod.POST)
	public @ResponseBody void signUp(@RequestBody User user){
		
		if (!userService.exists(user.getMail())){

			UserEntity entity = UserApiToDomainTransformer.transform(user);
			userService.create(entity);
			logger.info("new User with mail"+entity.getMail());
		}else{
			throw new RuntimeException("El usuario existe");
		}
	}
	
	
}
