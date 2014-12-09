package com.je.enterprise.mievento.domain.external.apiPlaces.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.google.common.primitives.Bytes;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.DetailPlace;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.Geometry;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.SearchPlace;

public class ApiPlacesIntegrationTest {

	ApiPlacesServicies apiPlacesServicies;
	ApiGeoServicies apiGeoServicies;
	
	@Before
	public void setUp(){
		RestTemplate template = new RestTemplate();
		apiPlacesServicies = new ApiPlacesServicies(template);
		apiGeoServicies = new ApiGeoServicies(template);
	}
	
	
	@Test
	public void allplaces_ok(){
		
		List<SearchPlace> searchPlaces = apiPlacesServicies.getPlaces("-34.6076388,-58.4463000", "casamiento|salon");
		
		assertNotNull(searchPlaces);
		assertFalse(searchPlaces.isEmpty());
		assertNotNull(searchPlaces.get(0).getId());
		assertNotNull(searchPlaces.get(0).getReference());
		
		DetailPlace detailPlace = apiPlacesServicies.getDetailPlace(searchPlaces.get(0).getReference());
		
		assertNotNull(detailPlace);
		assertNotNull(detailPlace.getName());
		assertFalse(detailPlace.getPhotos().isEmpty());
		assertNotNull(detailPlace.getPhotos().get(0).getReference());
		
		Bytes bytes = apiPlacesServicies.getPhoto(detailPlace.getPhotos().get(0).getReference());
		
		assertNotNull(bytes);
	}
	
	@Test
	public void allgeo_ok(){
		Geometry geometryCatamarca = apiGeoServicies.getGeoPosition("CT+ARGENTINA");
		
		assertNotNull(geometryCatamarca);
		assertEquals("-27.0763912,-66.9988011",geometryCatamarca.getLocation().toString());
		
		Geometry geometryChubut = apiGeoServicies.getGeoPosition("CH+ARGENTINA");
		
		assertNotNull(geometryChubut);
		assertEquals("-43.6846192,-69.2745537",geometryChubut.getLocation().toString());
	}
	
}
