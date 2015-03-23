package com.je.enterprise.mievento.domain.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.je.enterprise.mievento.api.dto.event.EventType;
import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.api.dto.location.ProvinceCode;
import com.je.enterprise.mievento.api.dto.provider.ProviderType;
import com.je.enterprise.mievento.domain.MongoClientUtilsTest;
import com.je.enterprise.mievento.domain.dao.impl.UserDAO;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.entity.common.event.GuestEntity;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.common.event.TaskEntity;
import com.je.enterprise.mievento.domain.entity.common.event.UserEntity;
import com.je.enterprise.mievento.domain.entity.location.CommercialLocationEntity;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;
import com.je.enterprise.mievento.domain.entity.location.StreetAddressEntity;
import com.je.enterprise.mievento.domain.entity.place.PlaceEntity;
import com.je.enterprise.mievento.domain.entity.wedding.PersonEntity;
import com.je.enterprise.mievento.domain.entity.wedding.PresentEntity;
import com.je.enterprise.mievento.domain.entity.wedding.EventWithPlaceAndPresentEntity;
import com.je.enterprise.mievento.domain.service.helper.CRUDHelper;
import com.je.enterprise.mievento.domain.service.impl.UserService;

public class AllEntitiesServiceIntegrationTest {

	private static MongoClientUtilsTest mongoClientUtilsTest = new MongoClientUtilsTest();
	private UserService userService;
	
	@BeforeClass
	public static void setUpBeforeLoadClass() throws Exception{
		mongoClientUtilsTest.startMongoDB();
	}
	
	@AfterClass
	public static void tearDownAfterClass(){
		mongoClientUtilsTest.stopMongoDB();
	}
	
	
	@Before
	public void setUp() throws Exception{
		mongoClientUtilsTest.getCleanMongoDB();
		UserDAO userDAO = new UserDAO(mongoClientUtilsTest.getDataStore());
		this.userService = new UserService(new CRUDHelper<UserEntity, ObjectId>(userDAO),null);
	}
	
	@Test
	public void save_user_ok(){

		this.userService.signUp("javimetal2014","a@a.com", "pass");
		UserEntity user = createUserWithEvents();
		this.userService.update(user);
		
		List<UserEntity> users = this.userService.getAll();
		
		assertFalse(users.isEmpty());
		assertEquals(1,users.size());
		
		UserEntity userFromDB = users.get(0);
		
		assertEquals("a@a.com",userFromDB.getEmail());
		assertEquals("pass",userFromDB.getPassword());
	}


	@Test
	public void save_userWithEmptyEvents_ok(){

		this.userService.signUp("javimetal2014","z@a.com", "pass");
		List<UserEntity> users = this.userService.getAll();
		
		assertFalse(users.isEmpty());
		assertEquals(1,users.size());
		
		UserEntity userFromDB = users.get(0);
		
		assertEquals("z@a.com",userFromDB.getEmail());
		assertEquals("pass",userFromDB.getPassword());
		assertTrue(userFromDB.getActivate());
		assertNotNull(userFromDB.getEvents());
		assertTrue(userFromDB.getEvents().isEmpty());
	}

	
	
	private UserEntity createUserWithEvents() {
		
		UserEntity user = new UserEntity("javimetal2014","a@a.com", "pass", true);
		user.setEvents(Lists.<EventEntity>newArrayList());
		
		EventWithPlaceAndPresentEntity wedding = new EventWithPlaceAndPresentEntity();
		wedding.setName("Mi Segunda Boda");
		wedding.setInitialDate(DateTime.now().plusWeeks(20).toDate());
		wedding.setFinalDate(DateTime.now().plusWeeks(21).toDate());
		wedding.setBudget(BigDecimal.TEN);
		
		LocationEntity locationHusband = new LocationEntity(CountryCode.AR,ProvinceCode.BA,"CABA",new StreetAddressEntity("callea", BigDecimal.ONE, null, "Palermo"));
		
		LocationEntity locationWedding = new LocationEntity(CountryCode.AR,ProvinceCode.BA,"CABA",new StreetAddressEntity("calleZ", BigDecimal.ONE, null, "Recoleta"));
		wedding.setPlace(new PlaceEntity("id1", "Salon Pueyrredon", "El mejor salon de todos loco.Rompermos todo", locationWedding, "salonP@gmail.com", "156545787", "01144578954", BigDecimal.ONE, BigDecimal.TEN, BigDecimal.valueOf(250), BigDecimal.valueOf(19), BigDecimal.valueOf(75),Arrays.asList("http://picture2"),ProviderType.WEDDING_HALL,null));
		wedding.setGuests(Lists.<GuestEntity>newArrayList(new GuestEntity("Guest","1","g1@gmail.com",locationHusband),new GuestEntity("Guest","2","g2@gmail.com",locationHusband)));
		
		wedding.setPresents(Lists.<PresentEntity>newArrayList(new PresentEntity("Vajilla", false,new CommercialLocationEntity("Falabella",CountryCode.AR,ProvinceCode.BA,"CABA",new StreetAddressEntity("callePresent", BigDecimal.ONE, null, "Recoleta")))));
		wedding.setProviders(Lists.<ProviderEntity>newArrayList());
		
		LocationEntity locationFotografo = new LocationEntity(CountryCode.AR,ProvinceCode.BA,"CABA",new StreetAddressEntity("calleZ", BigDecimal.ONE, null, "Recoleta"));
		wedding.getProviders().add(new ProviderEntity("id2", "Fotografo Carlitox", "Carlitox HOOO", locationFotografo, "Fc@gmail.com", "15548798","454879865", BigDecimal.ONE, BigDecimal.TEN,Arrays.asList("http://image1"),ProviderType.PHOTOGRAPHER,null));
		
		user.getEvents().add(wedding);
		
		
		CommercialLocationEntity locationEntregaTPFinal = new CommercialLocationEntity("Facultad Ingenieria",CountryCode.AR,ProvinceCode.BA,"CABA",new StreetAddressEntity("Paseo Coloon", BigDecimal.valueOf(750), "esquina Independencia", "San Telmo"));
		List<GuestEntity> guestsTP = Lists.<GuestEntity>newArrayList(new GuestEntity("mama","etche","mama@gmail.com",locationHusband),new GuestEntity("Abu","etche","abu@gmail.com",locationHusband));
		List<TaskEntity> tasksTP = Arrays.asList(new TaskEntity("Conseguir libreta",DateTime.now().toDate(),DateTime.now().plusDays(10).toDate()),new TaskEntity("comprar traje",DateTime.now().plusDays(5).toDate(),DateTime.now().plusDays(15).toDate()));
		EventEntity miJura = new EventEntity("Jura Ingeniero",DateTime.now().plusWeeks(10).toDate(),DateTime.now().plusWeeks(10).toDate(),guestsTP,tasksTP,Lists.newArrayList(new ProviderEntity("id3", "Barman batman", "Carlitox HOOO", locationFotografo, "Fc@gmail.com", "15548798","454879865", BigDecimal.ONE, BigDecimal.TEN,Arrays.asList("http://image1"),ProviderType.PHOTOGRAPHER,null)),EventType.COMMON_EVENT);
		
		user.getEvents().add(miJura);
		
		return user;
	}
	
	
	
}
