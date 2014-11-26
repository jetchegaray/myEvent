package com.je.enterprise.mievento.domain.external.apiPlaces.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class ConnectorPlaces {

	private static final Logger logger = Logger.getLogger(ConnectorPlaces.class);
	
	private RestTemplate restTemplate;
	
	@Autowired
	public ConnectorPlaces(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	
	public Object getConnector(UriComponentsBuilder parameters, Class<?> clazz){
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<?> entity = new HttpEntity<>(headers);

		HttpEntity<?> response = restTemplate.exchange(parameters.build().toUri(), HttpMethod.GET, entity, clazz);
		
		if (response.getBody().equals(HttpEntity.EMPTY)){
			logger.info(String.format("Response Empty when call connector with class %s",clazz));
			return null;
		}
		
		return response.getBody();
	}
	
}
