package com.je.enterprise.mievento.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.je.enterprise.mievento.api.dto.country.Country;
import com.je.enterprise.mievento.domain.entity.geo.CountryEntity;
import com.je.enterprise.mievento.domain.service.impl.CountryService;
import com.je.enterprise.mievento.domain.transformer.TransformerList;

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
	}
	
	
}
