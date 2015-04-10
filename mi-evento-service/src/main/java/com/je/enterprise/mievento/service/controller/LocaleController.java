package com.je.enterprise.mievento.service.controller;

import java.util.Locale;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.je.enterprise.mievento.service.utils.SerializableResourceBundleMessageSource;

@Controller
@RequestMapping("/messageBundle")
public class LocaleController {
	
	@Resource
    SerializableResourceBundleMessageSource messageBundle;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Properties list(@RequestParam String lang) {
	    return 	messageBundle.getAllProperties(new Locale(lang));
	}
	
}
