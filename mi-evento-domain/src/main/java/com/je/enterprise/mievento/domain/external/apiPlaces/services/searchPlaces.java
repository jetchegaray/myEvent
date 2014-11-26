package com.je.enterprise.mievento.domain.external.apiPlaces.services;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.je.enterprise.mievento.domain.external.apiPlaces.entities.SearchPlace;

@Component
public class searchPlaces {

	private static final Logger logger = Logger.getLogger(searchPlaces.class);
	
	private static final String API_KEY = "AIzaSyBg2-QcPo9pOLKnRXuN2eCn2xNi8oCwaac";
	private static final String RADIUS = "50000";
	private static final String SENSOR = BooleanUtils.toStringTrueFalse(Boolean.FALSE);
	
	private RestTemplate restTemplate;
	
	@Autowired
	public searchPlaces(RestTemplate restTemplate) {
		this.restTemplate =  restTemplate;
	}
	
	public SearchPlace getPlaces(String latAndLong, String keyWords){
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://maps.googleapis.com/maps/api/place/nearbysearch/json?key={apiKey}&location={latAndLong}&radius={radius}&sensor={sensor}&name={name}")
				.queryParam("apiKey", API_KEY)
		        .queryParam("latAndLong", latAndLong)
		        .queryParam("radius", RADIUS)
		        .queryParam("sensor", SENSOR)
		        .queryParam("name", keyWords);
		      
		HttpEntity<?> entity = new HttpEntity<>(this.getHeaders());

		HttpEntity<SearchPlace> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, SearchPlace.class);

		if (response.getBody().equals(HttpEntity.EMPTY)){
			logger.info(String.format("Response Empty when call connector searchPlaces"));
			return null;
		}
		
		return response.getBody();
	}

	
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}
}
