package com.je.enterprise.mievento.domain.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.bson.types.ObjectId;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.je.enterprise.mievento.domain.MongoClientUtilsTest;
import com.je.enterprise.mievento.domain.dao.impl.UserDAO;
import com.je.enterprise.mievento.domain.entity.common.event.UserEntity;
import com.je.enterprise.mievento.domain.service.helper.CRUDHelper;
import com.je.enterprise.mievento.domain.service.impl.UserService;

public class UserServiceIntegrationTest {

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
	public void save(){
		this.userService.signUp("javimetal2014", "a@gmail.com", "pass1");
		this.userService.signUp("javimetal2014", "b@gmail.com", "pass2");
		
		List<UserEntity> users = this.userService.getAll();
		
		assertFalse(users.isEmpty());
		assertEquals(2,users.size());
		assertEquals("a@gmail.com",users.get(0).getEmail());
		assertEquals("b@gmail.com",users.get(1).getEmail());
	}
	
	
	@Test
	public void findByMail(){
		
		this.userService.signUp("javimetal2014", "a@gmail.com", "pass1");
		this.userService.signUp("javimetal2014", "b@gmail.com", "pass2");
		
		UserEntity user_postStore1= this.userService.findByMail("a@gmail.com");
		assertEquals("a@gmail.com",user_postStore1.getEmail());
		assertEquals("pass1",user_postStore1.getPassword());
		assertTrue(user_postStore1.getActivate());
		
		UserEntity user_postStore2= this.userService.findByMail("b@gmail.com");
		assertEquals("b@gmail.com",user_postStore2.getEmail());
		assertEquals("pass2",user_postStore2.getPassword());
		assertTrue(user_postStore2.getActivate());
	}
	

	
}
