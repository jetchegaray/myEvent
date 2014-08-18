package com.je.enterprise.mievento.domain.service;

import org.bson.types.ObjectId;
import org.junit.Before;

import com.je.enterprise.mievento.domain.MongoClientUtilsTest;
import com.je.enterprise.mievento.domain.dao.impl.UserDAO;
import com.je.enterprise.mievento.domain.entity.UserEntity;
import com.je.enterprise.mievento.domain.service.helper.CRUDHelper;
import com.je.enterprise.mievento.domain.service.impl.UserService;

public class UserServiceIntegrationTest {

	MongoClientUtilsTest mongoClientUtilsTest;
	UserService userService;
	UserDAO userDAO;
	
	@Before
	public void setUp() throws Exception{
		mongoClientUtilsTest.getCleanMongoDB();
		this.userDAO = new UserDAO(mongoClientUtilsTest.getDataStore());
		this.userService = new UserService(new CRUDHelper<UserEntity, ObjectId>(userDAO));
	}
	
	

	
}
