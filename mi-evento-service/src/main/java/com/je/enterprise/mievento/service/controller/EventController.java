package com.je.enterprise.mievento.service.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.je.enterprise.mievento.api.dto.event.EventType;
import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.api.dto.location.ProvinceCode;
import com.je.enterprise.mievento.domain.service.impl.EventService;
import com.je.enterprise.mievento.domain.transformer.impl.EventTransformer;

@Controller
@RequestMapping(value = "/event")
public class EventController {

	@Autowired
	EventTransformer eventTransformer;
	@Autowired
	EventService eventService;
	
	@ResponseBody
	@RequestMapping(value={"/eventTypes"},method = RequestMethod.GET)
	public List<EventType> getAllStatusTypes(){
		return Arrays.asList(EventType.values());
	}
	
	@ResponseBody
	@RequestMapping(value={"/countries"},method = RequestMethod.GET)
	public List<CountryCode> getAllCountries(){
		return Arrays.asList(CountryCode.values());
	}
	
	@ResponseBody
	@RequestMapping(value={"/provinces"},method = RequestMethod.GET)
	public List<ProvinceCode> getAllProvinces(){
		return Arrays.asList(ProvinceCode.values());
	}
}
