package com.je.enterprise.mievento.domain.transformer.impl;

import org.junit.Before;

import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.event.Person;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.entity.common.event.PersonEntity;
import com.je.enterprise.mievento.domain.transformer.TransformerList;

public class TransformersImplTest {

	private UserTransformer userTransformer;
	
	@Before
	public void setUp(){
		
		StreetAddressTransformer streetAddressTransformer = new StreetAddressTransformer();
		CommercialLocationTransformer commercialLocationTransformer = new CommercialLocationTransformer(streetAddressTransformer);
		LocationTransformer locationTransformer = new LocationTransformer(streetAddressTransformer);
		PersonTransformer transformer = new PersonTransformer(locationTransformer);
		
		TransformerList<PersonEntity, Person> personTransformerList = new TransformerList<PersonEntity, Person>(transformer);
		EventTransformer eventTransformer = new EventTransformer(commercialLocationTransformer, personTransformerList);
		
		userTransformer = new UserTransformer(new TransformerList<EventEntity, Event>(eventTransformer));
	}
}
