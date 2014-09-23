package com.je.enterprise.mievento.service.controller;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.je.enterprise.mievento.api.dto.Provider;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderType;
import com.je.enterprise.mievento.domain.service.filters.TypeFilterProvider;
import com.je.enterprise.mievento.domain.service.impl.ProviderService;
import com.je.enterprise.mievento.domain.transformer.impl.ProviderDomainToApiTransformer;

@Controller
@RequestMapping(value= "/provider")
public class ProviderController {
	
	
	private static final Logger logger = Logger.getLogger(ProviderController.class);
	
	@Autowired
	private ProviderService providerService;
	
	@Autowired
	private ProviderDomainToApiTransformer providerDomainToApiTransformer;
	
	@ResponseBody
	@RequestMapping(value={"/all"},method = RequestMethod.GET)
	public List<Provider> getAll(){
		return providerDomainToApiTransformer.transform(providerService.getAll());
	}
	
	@ResponseBody
	@RequestMapping(value={"/types"},method = RequestMethod.GET)
	public List<String> getAllTypes(){
		return ProviderType.stringValues();
	}
	
	@ResponseBody
	@RequestMapping(value={"/byType/{type}"},method = RequestMethod.GET)
	public List<Provider> getByType(@PathVariable("type") String type){
		ProviderType providerType = ProviderType.getByName(type);
		List<Provider> providers = providerDomainToApiTransformer.transform(providerService.getBy(new TypeFilterProvider(providerType)));
		return providers;
	}
	
	
}
