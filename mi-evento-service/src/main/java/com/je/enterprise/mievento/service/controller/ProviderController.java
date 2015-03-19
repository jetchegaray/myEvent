package com.je.enterprise.mievento.service.controller;


import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.je.enterprise.mievento.api.dto.location.Location;
import com.je.enterprise.mievento.api.dto.provider.Provider;
import com.je.enterprise.mievento.api.dto.provider.ProviderType;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;
import com.je.enterprise.mievento.domain.exception.customize.LocationNotValidToSearchException;
import com.je.enterprise.mievento.domain.service.filters.CheaperFilterProvider;
import com.je.enterprise.mievento.domain.service.filters.LocationAndTypeFilterProvider;
import com.je.enterprise.mievento.domain.service.filters.LocationFilterProvider;
import com.je.enterprise.mievento.domain.service.filters.TypeFilterProvider;
import com.je.enterprise.mievento.domain.service.impl.ProviderService;
import com.je.enterprise.mievento.domain.transformer.TransformerList;
import com.je.enterprise.mievento.domain.transformer.impl.LocationTransformer;
import com.je.enterprise.mievento.domain.transformer.impl.ProviderTransformer;
import com.je.enterprise.mievento.service.request.ProviderTypesRequest;
import com.je.enterprise.mievento.service.request.SearchLocationTypeRequest;

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
	@Autowired
	private LocationTransformer locationTransformer;
	
	
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
	@RequestMapping(value={"/byLocation"},method = RequestMethod.POST)
	public List<Provider> getByLocation(@RequestBody Location location){
		logger.info(String.format("params, location %s",location));
		
		try {
			Validate.notNull(location.getCountryCode());
			Validate.notNull(location.getProvince());
		} catch (Exception e) {
			logger.error(String.format("Location not valid to search %s ",location));
			throw new LocationNotValidToSearchException();
		}
		
		LocationEntity locationEntity = this.locationTransformer.transformApiToDomain(location);
		List<Provider> providers = providerTransformerList.transformDomainToApi(providerService.getBy(new LocationFilterProvider(locationEntity)));
		return providers;
	}
	
	
	@ResponseBody
	@RequestMapping(value={"/byLocationAndType"},method = RequestMethod.POST)
	public List<Provider> getByLocationAndType(@RequestBody SearchLocationTypeRequest searchLocationTypeRequest){
		logger.info(String.format("params, byLocationAndType %s",searchLocationTypeRequest));
		
		LocationEntity locationEntity = this.locationTransformer.transformAndValidateApiToDomain(searchLocationTypeRequest.getLocation());
		List<Provider> providers = providerTransformerList.transformDomainToApi(providerService.getBy(
				new LocationAndTypeFilterProvider(searchLocationTypeRequest.getProviderType(),locationEntity)));
		return providers;
	}
	
	
	@ResponseBody
	@RequestMapping(value={"/moreCheaperByCategory"},method = RequestMethod.POST)
	public List<Provider> mostCheaperByCategory(@RequestBody ProviderTypesRequest types){
		logger.info(String.format("params, list of types %s",types));
		
		//TODO improve the query.
		List<Provider> providers = Lists.<Provider>newArrayList();
		for (ProviderType type : types.getTypes()) {
			Provider provider = providerTransformer.transformDomainToApi(providerService.getMostOfAllBy(new CheaperFilterProvider(type)));
			providers.add(provider);
		}
		return providers;
	}
	
	
	@ResponseBody
	@RequestMapping(value={"/updateReviews"},method = RequestMethod.POST)
	public void updateReviews(@RequestBody Provider providerUpdated){
		logger.info(String.format("new review provider %s",providerUpdated.getBusinessName()));
		providerService.updated(this.providerTransformer.transformApiToDomain(providerUpdated));
	}
	
	
	
	
}
