package com.je.enterprise.mievento.domain.dao.impl;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.je.enterprise.mievento.domain.dao.GenericDAO;
import com.je.enterprise.mievento.domain.entity.UserEntity;

@Repository
public class UserDAO extends GenericDAO<UserEntity, ObjectId>{

	
	@Autowired
	public UserDAO(Datastore ds) {
		super(UserEntity.class, ds);
	}
	
	
}
