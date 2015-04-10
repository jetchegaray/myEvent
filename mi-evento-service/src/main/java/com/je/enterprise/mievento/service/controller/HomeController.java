package com.je.enterprise.mievento.service.controller;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private static final Logger logger = Logger.getLogger(HomeController.class);
	 
	@RequestMapping(value= "/",method = RequestMethod.GET)
	public ModelAndView welcome(Locale locale){
		 logger.info(String.format("Welcome home! The client locale is %s.", locale));
		return new ModelAndView("index");
	}
}
