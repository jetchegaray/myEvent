package com.je.enterprise.mievento.domain.external.countries.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class CountryStatesServicies {

	private static final Logger logger = Logger.getLogger(CountryStatesServicies.class);

	private static final String  URL = "http://services.groupkt.com/country/get/all"; 

	private RestTemplate restTemplate;

	@Autowired
	public CountryStatesServicies(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	
	public List<CountryExternalWrapper> getCountries(String latAndLong, String keyWords) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL);

		HttpEntity<?> entity = new HttpEntity<>(this.getHeaders());
		ResponseEntity<ResultExternalWrapper> response = null;
		
		try {
			response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, new ParameterizedTypeReference<ResultExternalWrapper>() {});
	
			if (response.getBody().equals(HttpEntity.EMPTY)) {
				logger.info("Status Response Rest Template : "+response.getStatusCode());
				return null;
			}
			List<CountryExternalWrapper> countries = response.getBody().getCountries();
			
			if (countries == null){
				logger.info("Status Response country Search is null ");
			}
			return countries;

		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}
	
	
	public class ResultExternalWrapper{
		
		@JsonProperty("result")
		List<CountryExternalWrapper> countries;
		
		public ResultExternalWrapper() {
		}

		public List<CountryExternalWrapper> getCountries() {
			return countries;
		}

		public void setCountries(List<CountryExternalWrapper> countries) {
			this.countries = countries;
		}
	}
	
	public class CountryExternalWrapper{
		
		private String name;
		@JsonProperty("alpha2_code")
		private String alpha2Code;
		@JsonProperty("alpha3_code")
		private String alpha3Code;
		
		public CountryExternalWrapper() {
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAlpha2Code() {
			return alpha2Code;
		}

		public void setAlpha2Code(String alpha2Code) {
			this.alpha2Code = alpha2Code;
		}

		public String getAlpha3Code() {
			return alpha3Code;
		}

		public void setAlpha3Code(String alpha3Code) {
			this.alpha3Code = alpha3Code;
		}
		
	}
}
