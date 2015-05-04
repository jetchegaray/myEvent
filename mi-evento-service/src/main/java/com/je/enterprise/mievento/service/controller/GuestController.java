package com.je.enterprise.mievento.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.event.GuestStatusType;
import com.je.enterprise.mievento.api.dto.wrapper.InvitationDTO;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.exception.HttpEventException;
import com.je.enterprise.mievento.domain.service.impl.EventService;
import com.je.enterprise.mievento.domain.transformer.impl.events.TransformerEventList;

@Controller
@RequestMapping(value = "/guest")
public class GuestController {

	@Autowired
	private TransformerEventList eventTransformerList;
	@Autowired
	private EventService eventService;
	
	@ResponseBody
	@RequestMapping(value={"/statusTypes"},method = RequestMethod.GET)
	public List<String> getAllStatusTypes(){
		return GuestStatusType.stringValues();
	}
	
	@ResponseBody
	@RequestMapping(value = "/sendInvitation",method = RequestMethod.PUT)
	public void sendInvitation(@RequestBody InvitationDTO invitationDTO) throws HttpEventException{
		Event event = invitationDTO.getEvent();
		List<EventEntity> eventEntity = eventTransformerList.transformApiToDomain(com.google.common.collect.Lists.newArrayList(event));
		eventService.inviteTo(eventEntity.get(0), invitationDTO.getUserEmail(), invitationDTO.getGuestEmail());
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/confirmInvitation/{key}",method = RequestMethod.GET)
	public ModelAndView confirmInvitation(@RequestParam String key) throws HttpEventException{
		this.eventService.updateInvitation(key, GuestStatusType.CONFIRMED);
		 return new ModelAndView("index");
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/rejectInvitation/{key}",method = RequestMethod.GET)
	public ModelAndView rejectInvitation(@RequestParam String key) throws HttpEventException{
		this.eventService.updateInvitation(key, GuestStatusType.REJECTED);
		return new ModelAndView("index");
	}
	
	
	
}
