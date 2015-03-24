package com.je.enterprise.mievento.domain.external.apiPlaces.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.je.enterprise.mievento.domain.external.apiPlaces.entities.DetailPlace;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.SearchPlace;

public class ApiPlacesIntegrationTest {

	ApiPlacesServicies apiPlacesServicies;
	
	@Before
	public void setUp(){
		RestTemplate template = new RestTemplate();
		apiPlacesServicies = new ApiPlacesServicies(template);
	}
	
	
	@Test
	public void allplaces_ok(){
		
		ResponseContainerObjects<SearchPlace> response = apiPlacesServicies.getPlaces("-34.6076388,-58.4463000", "casamiento|salon");
		List<SearchPlace> searchPlaces = response.getData();
		assertNotNull(searchPlaces);
		assertFalse(searchPlaces.isEmpty());
		assertNotNull(searchPlaces.get(0).getId());
		assertNotNull(searchPlaces.get(0).getReference());
		
		DetailPlace detailPlace = apiPlacesServicies.getDetailPlace(searchPlaces.get(0).getReference());
		
		assertNotNull(detailPlace);
		assertNotNull(detailPlace.getName());
		assertFalse(detailPlace.getPhotos().isEmpty());
		assertNotNull(detailPlace.getPhotos().get(0).getReference());		
	}
	
	@Test
	public void testPhotos_ok(){
		String location	 = apiPlacesServicies.getPhoto("CnRnAAAAl7QHBJ68ZkxSWvnOQhZFlHAqR1fkhjZL9zMfFsE7KdsKYwbZ9w7M1jIWVwSDKY3VkZR2LEHHxgykpDcn39xs06lzM2ICQt9vomsUvdppyCs3JCXaVhF_fXEqSBNS0xbcVquqx49GNyHo4GtFAtahshIQSgt7GCNpsWN9_T2J9gWAeBoUjG2gjnKCV6KudgIAS0iJUdOEFaI");
		
		assertEquals("https://lh3.googleusercontent.com/-7S9uvywIrS0/VNqyKffjwjI/AAAAAAAAAAc/cWfUJNbUumI/s1600-w315-h317/photo.jpg",location);

	}
	
	
}
