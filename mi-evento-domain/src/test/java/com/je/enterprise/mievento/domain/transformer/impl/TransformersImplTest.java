package com.je.enterprise.mievento.domain.transformer.impl;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.event.Guest;
import com.je.enterprise.mievento.api.dto.event.GuestStatusType;
import com.je.enterprise.mievento.api.dto.event.InvitationStatus;
import com.je.enterprise.mievento.api.dto.event.Task;
import com.je.enterprise.mievento.api.dto.location.CommercialLocation;
import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.api.dto.location.Location;
import com.je.enterprise.mievento.api.dto.location.StreetAddress;
import com.je.enterprise.mievento.api.dto.provider.Provider;
import com.je.enterprise.mievento.api.dto.user.User;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.entity.common.event.GuestEntity;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.common.event.TaskEntity;
import com.je.enterprise.mievento.domain.entity.common.event.UserEntity;
import com.je.enterprise.mievento.domain.entity.location.CommercialLocationEntity;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;
import com.je.enterprise.mievento.domain.entity.location.StreetAddressEntity;
import com.je.enterprise.mievento.domain.transformer.TransformerList;

public class TransformersImplTest {

	private UserTransformer userTransformer;
	private LocationTransformer locationTransformer;
	private CommercialLocationTransformer commercialLocationTransformer;
	private GuestTransformer guestTransformer;
	private EventTransformer eventTransformer;
	private TaskTransformer taskTransformer;
	private ProviderTransformer providerTransformer;
	
	@Before
	public void setUp(){
		
		StreetAddressTransformer streetAddressTransformer = new StreetAddressTransformer();
		this.commercialLocationTransformer = new CommercialLocationTransformer(streetAddressTransformer);
		this.locationTransformer = new LocationTransformer(streetAddressTransformer);
		this.guestTransformer = new GuestTransformer(locationTransformer,new InvitationStatusTransformer());
		TransformerList<TaskEntity, Task> taskTransformerList = new TransformerList<TaskEntity, Task>(taskTransformer);
		TransformerList<GuestEntity, Guest> guestTransformerList = new TransformerList<GuestEntity, Guest>(guestTransformer);
		TransformerList<ProviderEntity, Provider> providerTransformerList = new TransformerList<ProviderEntity, Provider>(providerTransformer);
		
		this.eventTransformer = new EventTransformer(commercialLocationTransformer, guestTransformerList,taskTransformerList,providerTransformerList);
		
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
	public void guests_ok(){
		Location location = new Location();
		Guest guest = new Guest();
		
		Assert.assertNull(this.guestTransformer.transformAndValidateApiToDomain(null));
		
		guest.setEmail("kshdf@mail.com");
		guest.setInvitationStatus(new InvitationStatus(GuestStatusType.CONFIRMED, DateTime.now().toDate()));
		Assert.assertNotNull(this.guestTransformer.transformAndValidateApiToDomain(guest));
		
		guest.setLocation(location);
		Assert.assertNotNull(this.guestTransformer.transformAndValidateApiToDomain(guest));	
		
		LocationEntity locationEntity = new LocationEntity();
		GuestEntity guestEntity = new GuestEntity();
		
		Assert.assertNull(this.guestTransformer.transformAndValidateDomainToApi(null));
		
		guestEntity.setEmail("kshdf@mail.com");
		guest.setInvitationStatus(new InvitationStatus(GuestStatusType.WAIT_TILL_DAY, DateTime.now().plusDays(7).toDate()));
		Assert.assertNotNull(this.guestTransformer.transformAndValidateDomainToApi(guestEntity));
		
		guestEntity.setLocation(locationEntity);
		Assert.assertNotNull(this.guestTransformer.transformAndValidateDomainToApi(guestEntity));	
		
	}
	
	
	
	
	@Test
	public void event_ok(){
		Event event =  new Event();
		Guest guest = new Guest();
		CommercialLocation commercialLocation = new CommercialLocation();
		
		Assert.assertNull(this.eventTransformer.transformAndValidateApiToDomain(null));
		
		event.setName("evento");
		Assert.assertNotNull(this.eventTransformer.transformAndValidateApiToDomain(event));
		
		event.setGuests(Lists.<Guest>newArrayList(guest));
		Assert.assertNotNull(this.eventTransformer.transformAndValidateApiToDomain(event));
		
		
		EventEntity eventEntity =  new EventEntity();
		GuestEntity guestEntity = new GuestEntity();
		CommercialLocationEntity commercialLocationEntity = new CommercialLocationEntity();
		
		Assert.assertNull(this.eventTransformer.transformAndValidateDomainToApi(null));
		
		eventEntity.setName("evento");
		Assert.assertNotNull(this.eventTransformer.transformAndValidateDomainToApi(eventEntity));
		
		eventEntity.setGuests(Lists.<GuestEntity>newArrayList(guestEntity));
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
