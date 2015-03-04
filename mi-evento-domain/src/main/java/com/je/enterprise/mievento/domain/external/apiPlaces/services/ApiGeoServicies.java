package com.je.enterprise.mievento.domain.external.apiPlaces.services;

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

import com.je.enterprise.mievento.domain.external.apiPlaces.entities.GeoDetails;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.Geometry;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.StatusResponse;

@Component
public class ApiGeoServicies {

	private static final Logger logger = Logger.getLogger(ApiGeoServicies.class);

	private static final String SENSOR = BooleanUtils.toStringTrueFalse(Boolean.FALSE);

	private RestTemplate restTemplate;

	@Autowired
	public ApiGeoServicies(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	//na ex : CT+ARGENTINA
	public Geometry getGeoPosition(String address) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://maps.googleapis.com/maps/api/geocode/json?")
				.queryParam("address", address)
				.queryParam("sensor", SENSOR);

		HttpEntity<?> entity = new HttpEntity<>(this.getHeaders());
		ResponseEntity<ResponseContainerObjects<GeoDetails>> response = null;
		
		try {
			response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, new ParameterizedTypeReference<ResponseContainerObjects<GeoDetails>>() {});
	
			if (response.getBody().equals(HttpEntity.EMPTY)) {
				logger.info("Status Response Rest Template : "+response.getBody().getStatus());
				return null;
			}
			ResponseContainerObjects<GeoDetails> geoPositions = response.getBody();
			
			if (! geoPositions.getStatus().equals(StatusResponse.OK)){
				logger.info("Status Response Geo Positions : "+geoPositions.getStatus());
			}
			return geoPositions.getData().get(0).getGeometry();

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
