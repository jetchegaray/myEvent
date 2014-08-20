package com.je.enterprise.mievento.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Welcome")
public class HomeController {

	@RequestMapping(method = RequestMethod.GET)
	public String welcome(ModelMap model){
		model.addAttribute("message", "Mi evento up");
		return "index";
	}
}
