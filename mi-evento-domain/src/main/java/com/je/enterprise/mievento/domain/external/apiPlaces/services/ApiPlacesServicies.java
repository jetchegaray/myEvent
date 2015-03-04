package com.je.enterprise.mievento.domain.external.apiPlaces.services;

import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
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

import com.google.common.primitives.Bytes;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.DetailPlace;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.SearchPlace;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.StatusResponse;

@Component
public class ApiPlacesServicies {

	private static final Logger logger = Logger.getLogger(ApiPlacesServicies.class);

	private static final String API_KEY = "AIzaSyBg2-QcPo9pOLKnRXuN2eCn2xNi8oCwaac";
	private static final String RADIUS = "50000";
	private static final String SENSOR = BooleanUtils.toStringTrueFalse(Boolean.FALSE);
	private static final Integer PHOTO_MAX_WIDTH = 1600;
	private static final Integer PHOTO_MAX_HEIGHT = 1600;
	

	private RestTemplate restTemplate;

	@Autowired
	public ApiPlacesServicies(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	
	public List<SearchPlace> getPlaces(String latAndLong, String keyWords) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://maps.googleapis.com/maps/api/place/nearbysearch/json?")
				.queryParam("key", API_KEY)
				.queryParam("location", latAndLong)
				.queryParam("radius", RADIUS)
				.queryParam("sensor", SENSOR)
				.queryParam("name", keyWords);

		HttpEntity<?> entity = new HttpEntity<>(this.getHeaders());
		ResponseEntity<ResponseContainerObjects<SearchPlace>> response = null;
		
		try {
			response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, new ParameterizedTypeReference<ResponseContainerObjects<SearchPlace>>() {});
	
			if (response.getBody().equals(HttpEntity.EMPTY)) {
				logger.info("Status Response Rest Template : "+response.getBody().getStatus());
				return null;
			}
			ResponseContainerObjects<SearchPlace> places = response.getBody();
			
			if (! places.getStatus().equals(StatusResponse.OK)){
				logger.info("Status Response Api Places Search: "+places.getStatus());
			}
			return places.getData();

		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	
	public DetailPlace getDetailPlace(String referencePlace) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://maps.googleapis.com/maps/api/place/details/json?")
				.queryParam("key", API_KEY)
				.queryParam("sensor", SENSOR)
				.queryParam("reference", referencePlace);

		HttpEntity<?> entity = new HttpEntity<>(this.getHeaders());
		ResponseEntity<ResponseContainerObject<DetailPlace>> response = null;
		
		try {
			response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity,new ParameterizedTypeReference<ResponseContainerObject<DetailPlace>>() {});
			ResponseContainerObject<DetailPlace> place = response.getBody();
			
			if (! place.getStatus().equals(StatusResponse.OK)){
				logger.info("Status Response Api Places Detail : "+place.getStatus());
			}
			
			return place.getData();
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	
	
	
	public Bytes getPhoto(String referencePhoto) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://maps.googleapis.com/maps/api/place/photo/json?")
				.queryParam("key", API_KEY)
				.queryParam("sensor", SENSOR)
				.queryParam("photoreference", referencePhoto)
				.queryParam("maxheight", PHOTO_MAX_HEIGHT)
				.queryParam("maxwidth", PHOTO_MAX_WIDTH);

		HttpEntity<?> entity = new HttpEntity<>(this.getHeaders());
		HttpEntity<Bytes> response = null;
		
		try {
			response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, Bytes.class);
			
			return response.getBody();
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
}
