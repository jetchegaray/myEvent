package com.je.enterprise.mievento.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.je.enterprise.mievento.domain.transformer.impl.EventTransformer;

@Controller
public class EventController {

	@Autowired
	private EventTransformer eventDomainToApiTransformer;
	
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public void getAllNameEventByUser(String email){
		
		
	}
	
	public void getEvent(String name){
	}
	
}
