package com.je.enterprise.mievento.domain.external.apiGeo.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GEOServicies {

	private static final Logger logger = Logger.getLogger(GEOServicies.class);

	private static final String  URL_children = "http://api.geonames.org/childrenJSON?"; 
	private static final String USERNAME = "javimetal";
	private static final String ID_PARAM = "geonameId";
	private static final String USER_PARAM = "username";
	
	
	private RestTemplate restTemplate;

	@Autowired
	public GEOServicies(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	
	public List<GEOExternalWrapper> getChildrenById(String geoId) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_children).
				queryParam(ID_PARAM, geoId).
				queryParam(USER_PARAM, USERNAME);

		HttpEntity<?> entity = new HttpEntity<>(this.getHeaders());
		ResponseEntity<ResultExternalWrapper> response = null;
		
		try {
			response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, new ParameterizedTypeReference<ResultExternalWrapper>() {});
			
			if (response.getBody().equals(HttpEntity.EMPTY)) {
				logger.info("Status Response Rest Template GEO : "+response.getStatusCode());
				return null;
			}
			List<GEOExternalWrapper> result = response.getBody().getResults();
			
			if (result == null){
				logger.info("Status Response GEO Search is null ");
			}
			return result;

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
