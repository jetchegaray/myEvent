package com.je.enterprise.mievento.domain.dao.impl;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.je.enterprise.mievento.domain.dao.GenericDAO;
import com.je.enterprise.mievento.domain.entity.geo.BlackListCityEntity;

@Repository
public class BlackListCityDAO extends GenericDAO<BlackListCityEntity, ObjectId> {

	@Autowired
	public BlackListCityDAO(Datastore ds) {
		super(BlackListCityEntity.class, ds);
	}

}
