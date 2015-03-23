package com.je.enterprise.mievento.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.je.enterprise.mievento.api.dto.country.Country;
import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.domain.entity.geo.CountryEntity;
import com.je.enterprise.mievento.domain.service.impl.CountryService;
import com.je.enterprise.mievento.domain.transformer.TransformerList;
import com.je.enterprise.mievento.service.response.CountryResponse;

@Controller
@RequestMapping(value = "/country")
public class CountryController {

	@Autowired
	private TransformerList<CountryEntity, Country> countryTransformerList;
	@Autowired
	CountryService countryService;
	
	
	@ResponseBody
	@RequestMapping(value={"/all"},method = RequestMethod.GET)
	public List<Country> getAll(){
		return countryTransformerList.transformDomainToApi(countryService.getAll());
//		Map<CountryCode,Country> countriesByCode = Maps.newLinkedHashMap();
//		
//		for (Country country : countries) {
//			countriesByCode.put(CountryCode.getByName(country.getName()), country);
//		}
//		
//		return new CountryResponse(countriesByCode);
	}
	
	
}
