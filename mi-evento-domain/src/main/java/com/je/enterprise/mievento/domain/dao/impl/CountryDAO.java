package com.je.enterprise.mievento.domain.dao.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.je.enterprise.mievento.domain.dao.GenericDAO;
import com.je.enterprise.mievento.domain.entity.geo.CountryEntity;
import com.mongodb.WriteConcern;

@Repository
public class CountryDAO extends GenericDAO<CountryEntity, ObjectId> {

	@Autowired
	public CountryDAO(Datastore ds) {
		super(CountryEntity.class, ds);
	}

	public void save(List<CountryEntity> countriesToSave) {
		for (CountryEntity countryEntity : countriesToSave) {
			this.save(countryEntity,WriteConcern.FSYNC_SAFE);
		}
	}


}
