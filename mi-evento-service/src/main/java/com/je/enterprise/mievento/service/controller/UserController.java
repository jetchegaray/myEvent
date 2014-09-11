package com.je.enterprise.mievento.service.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.je.enterprise.mievento.api.dto.User;
import com.je.enterprise.mievento.domain.service.impl.UserService;

@Controller
@RequestMapping(value= "/user")
public class UserController {
	
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	public String login(@RequestBody User user){
		return userService.login(user.getEmail(),user.getPassword());
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public void signUp(@RequestBody User user){
		userService.signUp(user.getEmail(), user.getPassword());
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE)
	public void logout(String token){
		
	}
	
}
