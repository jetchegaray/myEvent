package com.je.enterprise.mievento.service.controller;


import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beust.jcommander.internal.Lists;
import com.je.enterprise.mievento.api.dto.provider.Provider;
import com.je.enterprise.mievento.api.dto.provider.ProviderType;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.service.filters.CheaperFilterProvider;
import com.je.enterprise.mievento.domain.service.filters.TypeFilterProvider;
import com.je.enterprise.mievento.domain.service.impl.ProviderService;
import com.je.enterprise.mievento.domain.transformer.TransformerList;
import com.je.enterprise.mievento.domain.transformer.impl.ProviderTransformer;

@Controller
@RequestMapping(value= "/provider")
public class ProviderController {
	
	
	private static final Logger logger = Logger.getLogger(ProviderController.class);
	
	@Autowired
	private ProviderService providerService;
	
	@Autowired
	private TransformerList<ProviderEntity, Provider> providerTransformerList;
	@Autowired
	private ProviderTransformer providerTransformer;
	
	
	@ResponseBody
	@RequestMapping(value={"/all"},method = RequestMethod.GET)
	public List<Provider> getAll(){
		return providerTransformerList.transformDomainToApi(providerService.getAll());
	}
	
	@ResponseBody
	@RequestMapping(value={"/types"},method = RequestMethod.GET)
	public List<ProviderType> getAllTypes(){
		return Arrays.asList(ProviderType.values());
	}
	
	@ResponseBody
	@RequestMapping(value={"/placeTypes"},method = RequestMethod.GET)
	public List<ProviderType> getPlaceTypes(){
		return ProviderType.getPlaceTypes();
	}
	
	@ResponseBody
	@RequestMapping(value={"/byType/{type}"},method = RequestMethod.GET)
	public List<Provider> getByType(@PathVariable("type") String type){
		ProviderType providerType = ProviderType.getByName(type);
		List<Provider> providers = providerTransformerList.transformDomainToApi(providerService.getBy(new TypeFilterProvider(providerType)));
		return providers;
	}
	
	
	@ResponseBody
	@RequestMapping(value={"/moreCheaperByCategory/{type}"},method = RequestMethod.GET)
	public List<Provider> mostCheaperByCategory(List<ProviderType> providerTypes){
		//TODO improve the query.
		List<Provider> providers = Lists.<Provider>newArrayList();
		for (ProviderType providerType : providerTypes) {
			Provider provider = providerTransformer.transformDomainToApi(providerService.getMostOfAllBy(new CheaperFilterProvider(providerType)));
			providers.add(provider);
		}
		return providers;
	}
	
	
}
