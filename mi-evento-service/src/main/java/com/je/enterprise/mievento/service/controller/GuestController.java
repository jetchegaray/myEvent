package com.je.enterprise.mievento.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.event.StatusType;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.exception.HttpEventException;
import com.je.enterprise.mievento.domain.service.impl.EventService;
import com.je.enterprise.mievento.domain.transformer.impl.EventTransformer;

@Controller
@RequestMapping(value = "/guest")
public class GuestController {

	@Autowired
	EventTransformer eventTransformer;
	@Autowired
	EventService eventService;
	
	@ResponseBody
	@RequestMapping(value={"/statusTypes"},method = RequestMethod.GET)
	public List<String> getAllStatusTypes(){
		return StatusType.stringValues();
	}
	
	@ResponseBody
	@RequestMapping(value = "/sendInvitation",method = RequestMethod.PUT)
	public void sendInvitation(@RequestBody Event event,@RequestBody String userEmail,@RequestBody String guestEmail) throws HttpEventException{
		EventEntity eventEntity = eventTransformer.transformAndValidateApiToDomain(event);
		eventService.inviteToOf(eventEntity, userEmail, guestEmail);
	}
	
}
