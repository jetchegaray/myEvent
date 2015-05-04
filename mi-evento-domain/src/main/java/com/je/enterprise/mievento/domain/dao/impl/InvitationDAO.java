package com.je.enterprise.mievento.domain.dao.impl;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.je.enterprise.mievento.domain.dao.GenericDAO;
import com.je.enterprise.mievento.domain.entity.invitations.InvitationEntity;

@Repository
public class InvitationDAO extends GenericDAO<InvitationEntity, ObjectId> {

	@Autowired
	public InvitationDAO(Datastore ds) {
		super(InvitationEntity.class, ds);
	}

	
	public InvitationEntity findByKey(String key) {
		
		Query<InvitationEntity> query = this.createQuery().enableSnapshotMode();
		query.criteria("key").equal(key);
		
		return this.find(query).get();
	}
}
