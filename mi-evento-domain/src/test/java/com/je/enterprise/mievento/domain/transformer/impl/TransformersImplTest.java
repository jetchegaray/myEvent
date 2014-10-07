package com.je.enterprise.mievento.domain.transformer.impl;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.event.Person;
import com.je.enterprise.mievento.api.dto.location.CommercialLocation;
import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.api.dto.location.Location;
import com.je.enterprise.mievento.api.dto.location.StreetAddress;
import com.je.enterprise.mievento.api.dto.user.User;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.entity.common.event.PersonEntity;
import com.je.enterprise.mievento.domain.entity.common.event.UserEntity;
import com.je.enterprise.mievento.domain.entity.location.CommercialLocationEntity;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;
import com.je.enterprise.mievento.domain.entity.location.StreetAddressEntity;
import com.je.enterprise.mievento.domain.transformer.TransformerList;

public class TransformersImplTest {

	private UserTransformer userTransformer;
	private LocationTransformer locationTransformer;
	private CommercialLocationTransformer commercialLocationTransformer;
	private PersonTransformer personTransformer;
	private EventTransformer eventTransformer;
	
	@Before
	public void setUp(){
		
		StreetAddressTransformer streetAddressTransformer = new StreetAddressTransformer();
		this.commercialLocationTransformer = new CommercialLocationTransformer(streetAddressTransformer);
		this.locationTransformer = new LocationTransformer(streetAddressTransformer);
		this.personTransformer = new PersonTransformer(locationTransformer);
		
		TransformerList<PersonEntity, Person> personTransformerList = new TransformerList<PersonEntity, Person>(personTransformer);
		this.eventTransformer = new EventTransformer(commercialLocationTransformer, personTransformerList);
		
		userTransformer = new UserTransformer(new TransformerList<EventEntity, Event>(eventTransformer));
	}
	

	@Test
	public void locationAndAdress_ok(){
		StreetAddress address = new StreetAddress();
		Location location = new Location();
		
		Assert.assertNull(this.locationTransformer.transformAndValidateApiToDomain(null));
		
		location.setCountryCode(CountryCode.AR);
		location.setStreetAddress(null);
		Assert.assertNotNull(this.locationTransformer.transformAndValidateApiToDomain(location));
		
		address.setNumber(BigDecimal.ONE);
		location.setStreetAddress(address);
		Assert.assertNotNull(this.locationTransformer.transformAndValidateApiToDomain(location));	
		
		StreetAddressEntity addressEntity = new StreetAddressEntity();
		LocationEntity locationEntity = new LocationEntity();
		
		Assert.assertNull(this.locationTransformer.transformAndValidateDomainToApi(null));
		
		locationEntity.setCountryCode(CountryCode.AR);
		locationEntity.setStreetAddress(null);
		Assert.assertNotNull(this.locationTransformer.transformAndValidateDomainToApi(locationEntity));
		
		addressEntity.setNumber(BigDecimal.ONE);
		locationEntity.setStreetAddress(addressEntity);
		Assert.assertNotNull(this.locationTransformer.transformAndValidateDomainToApi(locationEntity));	
	}
	
	
	@Test
	public void commerciallocation_ok(){
		StreetAddress address = new StreetAddress();
		CommercialLocation commercialLocation = new CommercialLocation();
		
		Assert.assertNull(this.commercialLocationTransformer.transformAndValidateApiToDomain(null));
		
		commercialLocation.setCountryCode(CountryCode.AR);
		commercialLocation.setStreetAddress(null);
		Assert.assertNotNull(this.commercialLocationTransformer.transformAndValidateApiToDomain(commercialLocation));
		
		address.setNumber(BigDecimal.ONE);
		commercialLocation.setStreetAddress(address);
		Assert.assertNotNull(this.commercialLocationTransformer.transformAndValidateApiToDomain(commercialLocation));	
		
		StreetAddressEntity addressEntity = new StreetAddressEntity();
		CommercialLocationEntity commercialLocationEntity = new CommercialLocationEntity();
		
		Assert.assertNull(this.commercialLocationTransformer.transformAndValidateDomainToApi(null));
		
		commercialLocationEntity.setCountryCode(CountryCode.AR);
		commercialLocationEntity.setStreetAddress(null);
		Assert.assertNotNull(this.commercialLocationTransformer.transformAndValidateDomainToApi(commercialLocationEntity));
		
		addressEntity.setNumber(BigDecimal.ONE);
		commercialLocationEntity.setStreetAddress(addressEntity);
		Assert.assertNotNull(this.commercialLocationTransformer.transformAndValidateDomainToApi(commercialLocationEntity));	
	}
	
	
	
	@Test
	public void person_ok(){
		Location location = new Location();
		Person person = new Person();
		
		Assert.assertNull(this.personTransformer.transformAndValidateApiToDomain(null));
		
		person.setEmail("kshdf@mail.com");
		Assert.assertNotNull(this.personTransformer.transformAndValidateApiToDomain(person));
		
		person.setLocation(location);
		Assert.assertNotNull(this.personTransformer.transformAndValidateApiToDomain(person));	
		
		LocationEntity locationEntity = new LocationEntity();
		PersonEntity personEntity = new PersonEntity();
		
		Assert.assertNull(this.personTransformer.transformAndValidateDomainToApi(null));
		
		personEntity.setEmail("kshdf@mail.com");
		Assert.assertNotNull(this.personTransformer.transformAndValidateDomainToApi(personEntity));
		
		personEntity.setLocation(locationEntity);
		Assert.assertNotNull(this.personTransformer.transformAndValidateDomainToApi(personEntity));	
		
	}
	
	
	
	
	@Test
	public void event_ok(){
		Event event =  new Event();
		Person person = new Person();
		CommercialLocation commercialLocation = new CommercialLocation();
		
		Assert.assertNull(this.eventTransformer.transformAndValidateApiToDomain(null));
		
		event.setName("evento");
		Assert.assertNotNull(this.eventTransformer.transformAndValidateApiToDomain(event));
		
		commercialLocation.setCity("Cordoba");
		event.setEventLocation(commercialLocation);
		Assert.assertNotNull(this.eventTransformer.transformAndValidateApiToDomain(event));	
		
		event.setGuests(Lists.<Person>newArrayList(person));
		Assert.assertNotNull(this.eventTransformer.transformAndValidateApiToDomain(event));
		
		
		EventEntity eventEntity =  new EventEntity();
		PersonEntity personEntity = new PersonEntity();
		CommercialLocationEntity commercialLocationEntity = new CommercialLocationEntity();
		
		Assert.assertNull(this.eventTransformer.transformAndValidateDomainToApi(null));
		
		eventEntity.setName("evento");
		Assert.assertNotNull(this.eventTransformer.transformAndValidateDomainToApi(eventEntity));
		
		commercialLocation.setCity("Cordoba");
		eventEntity.setEventLocation(commercialLocationEntity);
		Assert.assertNotNull(this.eventTransformer.transformAndValidateDomainToApi(eventEntity));	
		
		eventEntity.setGuests(Lists.<PersonEntity>newArrayList(personEntity));
		Assert.assertNotNull(this.eventTransformer.transformAndValidateDomainToApi(eventEntity));
		
	}
	
	
	@Test
	public void user_ok(){
		User user = new User();
		Event event = new Event();
		
		Assert.assertNull(this.userTransformer.transformAndValidateApiToDomain(null));
		
		user.setActivate(Boolean.FALSE);
		Assert.assertNotNull(this.userTransformer.transformAndValidateApiToDomain(user));
		
		user.setEvents(Lists.<Event>newArrayList(event));
		Assert.assertNotNull(this.userTransformer.transformAndValidateApiToDomain(user));	
		
		UserEntity userEntity = new UserEntity();
		EventEntity eventEntity = new EventEntity();
		
		Assert.assertNull(this.userTransformer.transformAndValidateDomainToApi(null));
		
		userEntity.setActivate(Boolean.FALSE);
		Assert.assertNotNull(this.userTransformer.transformAndValidateDomainToApi(userEntity));
		
		userEntity.setEvents(Lists.<EventEntity>newArrayList(eventEntity));
		Assert.assertNotNull(this.userTransformer.transformAndValidateDomainToApi(userEntity));
	}
}
