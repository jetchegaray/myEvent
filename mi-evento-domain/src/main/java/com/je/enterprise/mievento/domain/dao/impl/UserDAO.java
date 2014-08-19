package com.je.enterprise.mievento.domain.dao.impl;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.je.enterprise.mievento.domain.dao.GenericDAO;
import com.je.enterprise.mievento.domain.entity.UserEntity;

@Repository
public class UserDAO extends GenericDAO<UserEntity, ObjectId> {

	@Autowired
	public UserDAO(Datastore ds) {
		super(UserEntity.class, ds);
	}

	public UserEntity findByMail(String mail) {
		Query<UserEntity> query = this.createQuery().disableValidation();
		
		query.and(query.criteria("mail").equal(mail));
		
		return this.findOne(query);
	}

}
