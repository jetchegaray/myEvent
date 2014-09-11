package com.je.enterprise.mievento.domain.dao.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.je.enterprise.mievento.domain.dao.GenericDAO;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.service.filters.CriteriaFilterProvider;

@Repository
public class ProviderDAO extends GenericDAO<ProviderEntity, ObjectId> {

	@Autowired
	public ProviderDAO(Datastore ds) {
		super(ProviderEntity.class, ds);
	}

	public List<ProviderEntity> findBy(CriteriaFilterProvider criteriaFilterProvider) {
		return this.find(criteriaFilterProvider.buildQueryCriteria(this)).asList();
	}

}
