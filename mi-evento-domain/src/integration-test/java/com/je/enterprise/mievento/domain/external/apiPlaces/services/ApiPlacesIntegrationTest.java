package com.je.enterprise.mievento.domain.external.apiPlaces.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.google.common.primitives.Bytes;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.DetailPlace;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.SearchPlaces;

public class ApiPlacesIntegrationTest {

	ApiPlacesServicies apiPlacesServicies;
	
	@Before
	public void setUp(){
		apiPlacesServicies = new ApiPlacesServicies(new RestTemplate());
	}
	
	
	@Test
	public void all_ok(){
		
		SearchPlaces searchPlaces = apiPlacesServicies.getPlaces("-34.6076388,-58.4463000", "casamiento|salon");
		
		assertNotNull(searchPlaces);
		assertFalse(searchPlaces.getPlaces().isEmpty());
		assertNotNull(searchPlaces.getPlaces().get(0).getId());
		assertNotNull(searchPlaces.getPlaces().get(0).getReference());
		
		DetailPlace detailPlace = apiPlacesServicies.getDetailPlace(searchPlaces.getPlaces().get(0).getReference());
		
		assertNotNull(detailPlace);
		assertNotNull(detailPlace.getName());
		assertFalse(detailPlace.getPhotos().isEmpty());
		assertNotNull(detailPlace.getPhotos().get(0).getReference());
		
		Bytes bytes = apiPlacesServicies.getPhoto(detailPlace.getPhotos().get(0).getReference());
		
		assertNotNull(bytes);
		
	}
	
}
